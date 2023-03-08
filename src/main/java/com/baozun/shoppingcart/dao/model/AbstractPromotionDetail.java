package com.baozun.shoppingcart.dao.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DiscountPromotionDetail.class, name = "DISCOUNT"),
    @JsonSubTypes.Type(value = GiftPromotionDetail.class, name = "GIFT")
})
public abstract class AbstractPromotionDetail {

  private PromotionTypeEnum type;
}
