package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.Spu;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSpuRequest {

  @NotNull
  private Integer id;

  private String name;

  private Integer price;

  private Integer discount;

  private Integer categoryId;

  private Integer stock;


  public Spu toSpu() {
    Spu spu = new Spu();
    spu.setId(this.id);
    spu.setName(this.name);
    spu.setPrice(this.price);
    spu.setDiscount(this.discount);
    spu.setCategoryId(this.categoryId);
    spu.setStock(this.stock);
    return spu;
  }
}
