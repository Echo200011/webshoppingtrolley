/*
package com.baozun.webshoppingtrolley.resolver;

import com.baozun.webshoppingtrolley.bean.Promotion;
import com.baozun.webshoppingtrolley.bean.Spu;
import com.baozun.webshoppingtrolley.service.PromotionService;
import com.baozun.webshoppingtrolley.service.SpuService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

  @Autowired
  private SpuService spuService;

  @Autowired
  private PromotionService promotionService;

  public Spu spu(Integer id) {
    return spuService.findById(id);
  }

  public List<Spu> spus() {
    return spuService.findAll();
  }

  public Promotion promotion(Integer id) {
    return promotionService.findById(id);
  }


}
*/
