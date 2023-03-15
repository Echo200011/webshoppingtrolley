package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.AbstractPromotionDetail;
import com.baozun.shoppingcart.dao.model.Promotion;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

@Data
public class UpdatePromotionRequest {

  @NotNull(message = "不可为空")
  private Integer id;

  private String name;

  private String description;

  private AbstractPromotionDetail detail;

  public Promotion toPromotion(Promotion promotion) {
    promotion.setId(this.id);
    if (ObjectUtils.isNotEmpty(this.name)) {
      promotion.setName(this.name);
    }
    if (ObjectUtils.isNotEmpty(this.description)) {
      promotion.setDescription(this.description);
    }
    if (ObjectUtils.isNotEmpty(this.detail)) {
      promotion.setDetail(this.detail);
    }
    return promotion;
  }
}
