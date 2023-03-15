package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.AbstractPromotionDetail;
import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.DetailTypeEnum;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromotionRequest {

  @NotNull(message = "不可为空")
  private String name;
  @NotNull(message = "不可为空")
  private DetailTypeEnum type;
  @NotNull(message = "不可为空")
  private String description;
  @NotNull(message = "不可为空")
  private AbstractPromotionDetail detail;

  public Promotion toPromotion() {
    Promotion promotion = new Promotion();
    promotion.setName(this.name);
    promotion.setType(this.type);
    promotion.setDescription(this.description);
    promotion.setDetail(this.detail);
    return promotion;
  }
}
