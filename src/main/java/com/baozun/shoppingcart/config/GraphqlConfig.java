package com.baozun.shoppingcart.config;

import com.baozun.shoppingcart.dao.model.BundlingPromotionDetail;
import com.baozun.shoppingcart.dao.model.DiscountPromotionDetail;
import com.baozun.shoppingcart.dao.model.GiftPromotionDetail;
import graphql.kickstart.tools.SchemaParserDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlConfig {

  @Bean
  public SchemaParserDictionary schemaParserDictionary() {
    return new SchemaParserDictionary()
        .add(DiscountPromotionDetail.class)
        .add(GiftPromotionDetail.class)
        .add(BundlingPromotionDetail.class);
  }
}
