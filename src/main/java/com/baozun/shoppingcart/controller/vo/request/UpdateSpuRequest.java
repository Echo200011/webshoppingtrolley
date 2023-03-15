package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.Spu;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

@Data
public class UpdateSpuRequest {

  @NotNull(message = "更新id不可为空")
  @PositiveOrZero(message = "id不可为负")
  private Integer id;
  private String name;
  @PositiveOrZero(message = "价格最低为0元")
  private Integer price;
  @PositiveOrZero(message = "优惠价最低为0元")
  private Integer bidPrice;
  @PositiveOrZero(message = "类别id至少为0，不可为负")
  private Integer categoryId;
  @PositiveOrZero(message = "库存数最少为0件")
  private Integer stock;


  public Spu toSpu(Spu spu) {
    if (!ObjectUtils.isEmpty(name)) {
      spu.setName(this.name);
    }
    if (!ObjectUtils.isEmpty(price)) {
      spu.setPrice(this.price);
    }
    if (!ObjectUtils.isEmpty(bidPrice)) {
      spu.setBidPrice(this.bidPrice);
    }
    if (!ObjectUtils.isEmpty(categoryId)) {
      spu.setCategoryId(this.categoryId);
    }
    if (!ObjectUtils.isEmpty(stock)) {
      spu.setStock(this.stock);
    }
    return spu;
  }
}
