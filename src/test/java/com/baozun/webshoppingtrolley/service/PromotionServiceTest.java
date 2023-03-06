package com.baozun.webshoppingtrolley.service;


import com.baozun.webshoppingtrolley.bean.Detail;
import com.baozun.webshoppingtrolley.bean.Promotion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromotionServiceTest {

  @Autowired
  private PromotionService promotionService;

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
  }

  @Test
  void testSave() throws JsonProcessingException {
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
    promotion.setName("sdasd");
    ObjectMapper objectMapper = new ObjectMapper();
    String test;
    try {
       test = objectMapper.writeValueAsString(promotion.getDetails());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    TypeReference<List<Detail>> typeReference = new TypeReference<List<Detail>>() {
    };
   List<Detail> details = objectMapper.readValue(test,typeReference);
    List<Promotion> promotion1 = promotionService.save(promotion);
  }

  @Test
  void delete() {
  }
}