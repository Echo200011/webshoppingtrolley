package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuQueryParameter;
import com.baozun.shoppingcart.service.PromotionService;
import com.baozun.shoppingcart.service.SpuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

  private final PromotionService promotionService;

  private final SpuService spuService;

  @PostMapping("/promotion")
  public List<Promotion> savePromotion(@RequestBody List<Promotion> promotions) {
    return promotionService.save(promotions);
  }

  @GetMapping("/promotion")
  public Promotion findPromotionById(Integer id) {
    return promotionService.findById(id);
  }


/*  @GetMapping("/spu")
  public Spu findSpuById(Integer id) {
    return spuService.findById(id);
  }*/

  @GetMapping("/spu")
  public Page<Spu> findSpuAll(SpuQueryParameter parameter ,Integer pageNumber, Integer pageSize) {
    return spuService.findAll(parameter,pageNumber,pageSize);
  }



}
