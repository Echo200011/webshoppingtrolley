package com.baozun.webshoppingtrolley.service;

import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.mapper.PromotionRepository;
import java.util.Arrays;
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
  public List<Promotion> save(Promotion... promotion) {
    if (promotion.length == 0) {
      return null;
    }
    return promotionRepository.saveAllAndFlush(Arrays.asList(promotion));
  }

  @Transactional
  public void delete(Integer... id) {
    if (id.length == 0) {
      return;
    }
    Arrays.stream(id).forEach(i -> promotionRepository.deleteById(i));
  }


}
