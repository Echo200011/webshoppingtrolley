package com.baozun.webshoppingtrolley.controller;

import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.service.PromotionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {
  @Autowired
  private PromotionService promotionService;

  @PostMapping("/promotion")
  public List<Promotion> save(Promotion promotion){
    return promotionService.save(promotion);
  }
}
