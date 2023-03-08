package com.baozun.shoppingcart.service;


import com.baozun.shoppingcart.dao.model.SpuPromotionMapping;
import com.baozun.shoppingcart.dao.SpuPromotionMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpuPromotionMappingService {

  private final SpuPromotionMappingRepository spuPromotionMappingRepository;

  public SpuPromotionMapping save(Integer spuId, Integer promotionID) {
    try {
      return spuPromotionMappingRepository.save(new SpuPromotionMapping(spuId, promotionID));
    } catch (Exception e) {
      throw new IllegalArgumentException("数据库不存在spuId或promotionId", e);
    }
  }
}
