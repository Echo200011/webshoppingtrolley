package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.Spu;
import lombok.Data;

@Data
public class SpuRequest {

  private String code;

  private String name;

  private Integer price;

  private Integer discount;

  private Integer categoryId;

  public Spu toSpu() {
    Spu spu = new Spu();
    spu.setCode(this.code);
    spu.setName(this.name);
    spu.setPrice(this.price);
    spu.setDiscount(this.discount);
    spu.setCategoryId(this.categoryId);
    return spu;
  }
}
