package com.baozun.webshoppingtrolley.service;

import static org.junit.jupiter.api.Assertions.*;

import com.baozun.webshoppingtrolley.bean.Spu;
import com.baozun.webshoppingtrolley.mapper.SpuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpuServiceTest {

  @Autowired
  private SpuService spuService;

  @Autowired
  private SpuPromotionMappingService spuPromotionMappingService;


  @Autowired
  private SpuRepository spuRepository;

  @Test
  void saveSpuAndPromotions() {
    /*Spu spu = new Spu();
    spu.setName("sdasd");
    spu.setCode("dsda11333333");
    spu.setPrice(10);
    spu.setDiscount(8);
    spu.setCategory("1");
    spuService.saveSpuAndPromotions(spu,3);*/

    spuRepository.deleteById(1);
  }
}