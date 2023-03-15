package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.Spu;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class SpuRequest {

  @NotNull(message = "商品编码不可为空")
  private String code;
  @NotNull(message = "商品名称不可为空")
  private String name;
  @PositiveOrZero(message = "价格最低为0元")
  private Integer price = 0;
  @PositiveOrZero(message = "优惠价最低为0元")
  private Integer bidPrice = 0;
  @NotNull(message = "商品种类不可为空")
  @PositiveOrZero(message = "类别id至少为0，不可为负")
  private Integer categoryId;

  public Spu toSpu() {
    Spu spu = new Spu();
    spu.setCode(this.code);
    spu.setName(this.name);
    spu.setPrice(this.price);
    spu.setBidPrice(this.bidPrice);
    spu.setCategoryId(this.categoryId);
    return spu;
  }
}
