package com.baozun.webshoppingtrolley.AttributeConverter;

import com.baozun.webshoppingtrolley.bean.Detail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class DetailConverter implements AttributeConverter<List<Detail>, String> {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(List<Detail> attribute) {
    if (CollectionUtils.isEmpty(attribute)) {
      return "{}";
    }
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public List<Detail> convertToEntityAttribute(String dbData) {
    if (StringUtils.equals("{}", dbData)) {
      return Collections.emptyList();
    }

    TypeReference<List<Detail>> typeReference = new TypeReference<List<Detail>>() {
    };
    try {
      return objectMapper.readValue(dbData, typeReference);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
