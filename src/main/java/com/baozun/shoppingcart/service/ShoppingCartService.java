package com.baozun.shoppingcart.service;


import com.baozun.shoppingcart.controller.vo.request.ShoppingCartRequest;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.dao.ShoppingCartRepository;
import com.baozun.shoppingcart.dao.model.AbstractPromotionDetail;
import com.baozun.shoppingcart.dao.model.DiscountPromotionDetail;
import com.baozun.shoppingcart.dao.model.ShoppingCart;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.baozun.shoppingcart.exception.AppException;
import com.baozun.shoppingcart.exception.AppExceptionCodeMsg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


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
    if (spu.getStock() < 0) {
      throw new AppException(AppExceptionCodeMsg.STOCK_MUST_HAVE);
    }
    /*Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(1);*/
    UpdateSpuRequest updateSpuRequest = new UpdateSpuRequest();
    updateSpuRequest.setStock(spu.getStock() - 1);
    spuService.updateSpu(updateSpuRequest);
    ShoppingCart save = shoppingCartRepository.save(setShoppingCart(spu, 0, shoppingCartRequest.getUserId()));
    return save.getId();
  }

  public void createOrder() {
    List<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserId(1);
    Map<AbstractPromotionDetail, List<Spu>> map = new HashMap<>();
    Map<AbstractPromotionDetail, List<Spu>> collect = shoppingCart.stream().map(ShoppingCart::getSpu)
        .collect(Collectors.groupingBy(spu -> new AbstractPromotionDetail() {
        }));
  }

  private ShoppingCart setShoppingCart(Spu spu, Integer count, Integer userId) {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setSpuId(spu.getId());
    shoppingCart.setPrice(spu.getPrice());
    shoppingCart.setCount(count + 1);
    shoppingCart.setUserId(userId);
    return shoppingCart;
  }
}
