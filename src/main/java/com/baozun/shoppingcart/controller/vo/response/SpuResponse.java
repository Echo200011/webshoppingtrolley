package com.baozun.shoppingcart.controller.vo.response;

import com.baozun.shoppingcart.dao.model.Spu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpuResponse {

  private Integer id;

  private String name;

  private Integer price;

  private Integer bidPrice;

  private String spuCategoryName;

  public static SpuResponse toSpuResponse(Spu spu) {
    return new SpuResponse(spu.getId(), spu.getName(), spu.getPrice(), spu.getBidPrice(),
        spu.getSpuCategory().getName());
  }
}
