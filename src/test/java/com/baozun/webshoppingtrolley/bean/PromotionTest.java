package com.baozun.webshoppingtrolley.bean;


import com.baozun.webshoppingtrolley.mapper.PromotionRepository;
import com.baozun.webshoppingtrolley.service.SpuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromotionTest {

  @Autowired
  private PromotionRepository repository;
  @Autowired
  private SpuService spuService;

  @Test
  void test() {
  /*  Integer id = 1;

    Detail detail = new Detail();

    detail.setId(1);

    Promotion promotion = new Promotion();
    promotion.setDetail(detail);

    promotion.setName("sdasd");
    promotion.setId(4);
    repository.save(promotion);*/
    Spu spu = new Spu();
    spu.setName("sdasd");
    spu.setCode("dsda123213");
    spu.setPrice(10);
    spu.setDiscount(8);
    spu.setCategory("1");
  }
}