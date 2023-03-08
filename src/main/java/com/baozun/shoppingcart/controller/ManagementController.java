package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.service.PromotionService;
import com.baozun.shoppingcart.service.SpuService;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {

  @Autowired
  private PromotionService promotionService;

  @Autowired
  private SpuService spuService;

  @PostMapping("/discountPromotion")
  public List<Promotion> savePromotion(@RequestBody List<Promotion> promotions) {
    return promotionService.save(promotions);
  }

  @GetMapping("/promotion")
  public Promotion findPromotionById(Integer id) {
    return promotionService.findById(id);
  }

  @GetMapping("/spu")
  public Spu findSpuById(Integer id) {
    return spuService.findById(id);
  }

}
