package com.baozun.webshoppingtrolley.service;

import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.mapper.PromotionRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

  @Autowired
  private PromotionRepository promotionRepository;

  @Transactional
  public Promotion promotion(Integer id) {
    return promotionRepository.findById(id).orElse(null);
  }
}
