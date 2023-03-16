package com.baozun.shoppingcart.service;


import com.baozun.shoppingcart.controller.vo.request.ShoppingCartRequest;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.controller.vo.response.ShoppingCartData;
import com.baozun.shoppingcart.controller.vo.response.SpuData;
import com.baozun.shoppingcart.dao.ShoppingCartRepository;
import com.baozun.shoppingcart.dao.model.ShoppingCartLine;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.baozun.shoppingcart.exception.AppException;
import com.baozun.shoppingcart.exception.AppExceptionCodeMsg;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

  private final ShoppingCartRepository shoppingCartRepository;
  private final SpuService spuService;

  @Transactional
  public Integer save(ShoppingCartRequest shoppingCartRequest) {
    Spu spu = spuService.findById(shoppingCartRequest.getSpuId());
    if (!spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)) {
      throw new AppException(AppExceptionCodeMsg.SPU_MUST_ON_SHELVES);
    }
    if (spu.getStock() <= 0) {
      throw new AppException(AppExceptionCodeMsg.STOCK_MUST_HAVE);
    }
    List<ShoppingCartLine> lines = shoppingCartRepository.findByCode(shoppingCartRequest.getCode());
    ShoppingCartLine shoppingCartLine = lines.stream()
        .filter(line -> line.getSpuId().equals(shoppingCartRequest.getSpuId())).findFirst().orElse(null);
    UpdateSpuRequest updateSpuRequest = new UpdateSpuRequest();
    updateSpuRequest.setStock(spu.getStock() - 1);
    updateSpuRequest.setId(shoppingCartRequest.getSpuId());
    spuService.updateSpu(updateSpuRequest);
    if (ObjectUtils.isNotEmpty(shoppingCartLine)) {
      shoppingCartLine.setCount(shoppingCartLine.getCount() + 1);
    }
    spuService.updateSpu(updateSpuRequest);
    ShoppingCartLine save = shoppingCartRepository.save(setShoppingCart(spu, shoppingCartRequest.getCode()));
    return save.getId();
  }
  @Transactional
  public ShoppingCartData createOrder(Integer code) {
    List<ShoppingCartLine> shoppingCartLine = shoppingCartRepository.findByCode(code);
    List<SpuData> spuData = shoppingCartLine.stream()
        .map(cart -> SpuData.toSpuSpuResult(cart.getSpu(), cart.getCount()))
        .collect(Collectors.toList());
    spuData.stream()
        .filter(spu -> ObjectUtils.isNotEmpty(spu.getPromotions()))
        .flatMap(
            spu -> spu.getPromotions()
                .stream()
                .map(promotion -> Pair.of(promotion.getDetail(), spu))
        )
        .collect(Collectors.groupingBy(Pair::getLeft, HashMap::new,
            Collectors.mapping(Pair::getRight, Collectors.toList()))
        )
        .entrySet()
        .stream()
        .sorted(Comparator.comparing(p -> p.getKey().getLevel()))
        .forEach(map -> map.getKey().calculatePrice(map.getValue()));
    spuData.stream()
            .filter(data -> ObjectUtils.isNotEmpty(data.getGiftSpu()))
            .forEach(data ->data.setGiftSpu(setGiftSpuData(data.getId())));
    return  new ShoppingCartData(code, spuData);
  }

  private SpuData setGiftSpuData(Integer spuId){
    Spu spu = spuService.findById(spuId);
    if (!spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)) {
     return null;
    }
    if (spu.getStock() <= 0) {
      return null;
    }
    UpdateSpuRequest updateSpuRequest = new UpdateSpuRequest();
    updateSpuRequest.setStock(spu.getStock() - 1);
    updateSpuRequest.setId(spuId);
    spuService.updateSpu(updateSpuRequest);
   return  SpuData.toSpuSpuResult(spu,1);
  }

  private ShoppingCartLine setShoppingCart(Spu spu, Integer code) {
    ShoppingCartLine shoppingCartLine = new ShoppingCartLine();
    shoppingCartLine.setSpuId(spu.getId());
    shoppingCartLine.setCode(code);
    return shoppingCartLine;
  }
}
