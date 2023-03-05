package com.baozun.webshoppingtrolley.AttributeConverter;

import com.alibaba.fastjson2.JSON;
import com.baozun.webshoppingtrolley.bean.Detail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class DetailConverter implements AttributeConverter<List<Detail>, String> {

  @Override
  public String convertToDatabaseColumn(List<Detail> attribute) {
    if (CollectionUtils.isEmpty(attribute)) {
      return "null";
    }
    return JSON.toJSONString(attribute);
  }


  @Override
  @SuppressWarnings("unchecked")
  public List<Detail> convertToEntityAttribute(String dbData) {
    if (StringUtils.equals("null", dbData)) {
      return new ArrayList<>();
    }
    return JSON.parseArray(dbData,Detail.class);
  }
}
