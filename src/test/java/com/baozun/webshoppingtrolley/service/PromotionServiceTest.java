package com.baozun.webshoppingtrolley.service;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baozun.webshoppingtrolley.bean.Detail;
import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.mapper.PromotionRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromotionServiceTest {

  @Autowired
  private PromotionRepository promotionService;

  @Test
  void save() {
    Promotion promotion = new Promotion();
    promotion.setName("dsad");
    Detail detail = new Detail();
    detail.setCondition("test");
    detail.setValue(1);
    Detail detail2 = new Detail();
    detail2.setCondition("test2");
    detail2.setValue(2);
    promotion.setDetails(detail);
    promotion.setDetails(detail2);

   Promotion promotion1 = promotionService.findById(21).get();


  }
}