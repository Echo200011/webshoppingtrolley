package com.baozun.shoppingcart.dao.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
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
    @JsonSubTypes.Type(value = GiftPromotionDetail.class, name = "GIFT"),
    @JsonSubTypes.Type(value = BundlingPromotionDetail.class,name = "BUNDLING")
})
public abstract class AbstractPromotionDetail {

  private Integer level;
  private DetailTypeEnum type;

  public abstract void calculatePrice(List<Spu> spuList);
}
