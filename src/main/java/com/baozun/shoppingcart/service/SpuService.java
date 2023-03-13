package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.controller.vo.request.PageParameterRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuStatusEnum;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.controller.vo.response.SpuResponse;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpuService {

  private final SpuRepository spuRepository;

  public Page<Spu> findAll(PageParameterRequest pageParameterRequest) {
    Pageable pageable = PageRequest.of(pageParameterRequest.getPageNumber(), pageParameterRequest.getPageSize());
    return spuRepository.findAll(pageable);
  }

  public Page<Spu> findAllByParameter(SpuQueryRequest parameter) {
    Pageable pageable = PageRequest.of(parameter.getPageNumber(), parameter.getPageSize());
    return spuRepository.findAll(parameter, pageable);
  }

  public SpuResponse findById(Integer spuId) {
    Spu spu = spuRepository.findById(spuId).orElse(null);
    if (ObjectUtils.isEmpty(spu)) {
      return null;
    }
    return SpuResponse.toSpuResponse(spu);
  }

  public SpuResponse saveSpu(SpuRequest spuRequest) {
    return SpuResponse.toSpuResponse(spuRepository.save(spuRequest.toSpu()));
  }

  @Transactional
  public SpuResponse updateSpu(UpdateSpuRequest updateSpuRequest) {
    Spu spu = spuRepository.saveAndFlush(updateSpuRequest.toSpu());
    return SpuResponse.toSpuResponse(spu);
  }

  @Transactional
  public void onShelves(Integer spuId) {
    Spu spu = spuRepository.findById(spuId).orElseThrow(() -> new IllegalArgumentException("商品不存在"));
    if (spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)){
      throw new IllegalArgumentException("商品已上架");
    }
    if (spu.getStock() <= 0) {
      throw new IllegalArgumentException("库存必须大于0");
    }
    if (spuRepository.onShelves(spuId) <= 0) {
      throw new IllegalArgumentException("上架失败");
    }
  }

  @Transactional
  public void soldOut(Integer spuId) {
    Spu spu = spuRepository.findById(spuId).orElseThrow(() -> new IllegalArgumentException("商品不存在"));
    if (spu.getStatus().equals(SpuStatusEnum.SOLD_OUT)){
      throw new IllegalArgumentException("商品已上架");
    }
    if (!spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)) {
      throw new IllegalArgumentException("商品必须先上架");
    }
    if (spuRepository.soldOut(spuId) <= 0) {
      throw new IllegalArgumentException("下架失败");
    }
  }


  @Transactional
  public void deleteSpuById(Integer spuId) {
    spuRepository.deleteById(spuId);
  }

}
