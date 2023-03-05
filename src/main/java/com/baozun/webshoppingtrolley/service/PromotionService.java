package com.baozun.webshoppingtrolley.service;

import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.mapper.PromotionRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

  @Autowired
  private PromotionRepository promotionRepository;


  public Promotion findById(Integer id) {
    return promotionRepository.findById(id).orElse(null);
  }

  @Transactional
  public Promotion save(Promotion promotion) {
    return promotionRepository.saveAndFlush(promotion);
  }

  @Transactional
  public List<Promotion> saveAll(List<Promotion> promotions) {
    return promotionRepository.saveAllAndFlush(promotions);
  }


}
