package com.baozun.shoppingcart.controller.vo.response;

import com.baozun.shoppingcart.dao.model.DetailTypeEnum;
import com.baozun.shoppingcart.dao.model.Promotion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromotionResponse {

  private Integer id;

  private String name;

  private DetailTypeEnum type;

  private String description;

  public static PromotionResponse toPromotionResponse(Promotion promotion) {
    return new PromotionResponse(promotion.getId(), promotion.getName(), promotion.getType(),
        promotion.getDescription());
  }
}
