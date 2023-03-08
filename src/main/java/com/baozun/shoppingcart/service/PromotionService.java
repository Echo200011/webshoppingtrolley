package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.PromotionRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

  private final PromotionRepository promotionRepository;


  public Promotion findById(Integer id) {
    return promotionRepository.findById(id).orElse(null);
  }

  @Transactional
  public List<Promotion> save(List<Promotion> promotions) {
    return promotionRepository.saveAllAndFlush(promotions);
  }
}
