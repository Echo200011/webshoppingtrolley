package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.service.PromotionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionManagementController {

  private final PromotionService promotionService;

  //PromotionRequest
  @PostMapping
  public List<Promotion> savePromotionAll(@RequestBody List<Promotion> promotions) {
    return promotionService.save(promotions);
  }

  @GetMapping("/{id}")
  public Promotion findPromotionById(@PathVariable("id") Integer id) {
    return promotionService.findById(id);
  }
}
