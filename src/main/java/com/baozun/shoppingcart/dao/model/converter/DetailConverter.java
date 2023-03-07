package com.baozun.shoppingcart.dao.model.converter;

import com.baozun.shoppingcart.dao.model.AbstractPromotionDetail;
import com.baozun.shoppingcart.dao.model.PromotionTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.ObjectUtils;


public class DetailConverter implements AttributeConverter<AbstractPromotionDetail, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(AbstractPromotionDetail detail) {
    if (ObjectUtils.isEmpty(detail)) {
      return "{}";
    }
    try {
      return objectMapper.writeValueAsString(detail);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("promotion_detail json序列化错误", e);
    }
  }

  @Override
  public AbstractPromotionDetail convertToEntityAttribute(String dbData) {
    try {
      JsonNode jsonNode = objectMapper.readValue(dbData, JsonNode.class);
      PromotionTypeEnum promotionTypeEnum = PromotionTypeEnum.valueOf(jsonNode.get("type").asText());
      return objectMapper.readValue(String.valueOf(jsonNode),
          promotionTypeEnum.getClz());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
