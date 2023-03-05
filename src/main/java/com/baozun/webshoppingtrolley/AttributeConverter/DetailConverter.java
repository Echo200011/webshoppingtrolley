package com.baozun.webshoppingtrolley.AttributeConverter;

import com.alibaba.fastjson2.JSONObject;
import com.baozun.webshoppingtrolley.bean.Detail;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class DetailConverter implements AttributeConverter<Detail,String> {

  @Override
  public String convertToDatabaseColumn(Detail attribute) {
    if (ObjectUtils.isEmpty(attribute)) {
     return  "";
    }
    return JSONObject.toJSONString(attribute);
  }

  @Override
  public Detail convertToEntityAttribute(String dbData) {
      if (StringUtils.isEmpty(dbData)){
        return new Detail();
      }
        return JSONObject.parseObject(dbData, Detail.class);
  }
}
