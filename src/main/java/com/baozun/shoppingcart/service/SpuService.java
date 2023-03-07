package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuService {

  @Autowired
  private SpuRepository spuRepository;

  @Autowired
  private SpuPromotionMappingService spuPromotionMappingService;


  public List<Spu> findAll() {
    return spuRepository.findAll();
  }

  public Spu findById(Integer id) {
    return spuRepository.findById(id).orElse(null);
  }

  @Transactional
  public List<Spu> saveAll(List<Spu> spus) {
    return spuRepository.saveAll(spus);
  }

  @Transactional
  public Spu saveSpuAndPromotions(Spu spu, Integer... promotionsId) {
    if (ObjectUtils.isEmpty(spu)) {
      return null;
    }
    if (promotionsId.length == 0) {
      return spuRepository.save(spu);
    }
    try {
      Spu finalSpu = spuRepository.save(spu);
      Arrays.stream(promotionsId).forEach(pId -> spuPromotionMappingService.save(finalSpu.getId(), pId));
    } catch (Exception e) {
      throw new IllegalArgumentException("数据库存储错误", e);
    }
    return spu;
  }
}
