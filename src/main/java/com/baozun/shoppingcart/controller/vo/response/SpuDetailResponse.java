package com.baozun.shoppingcart.controller.vo.response;


import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpuDetailResponse {

  private Integer id;

  private String code;

  private String name;

  private Integer price;

  private Integer discount;

  private String spuCategoryName;

  private Integer stock;

  private SpuStatusEnum status;

  private List<Promotion> promotions;

  public static SpuDetailResponse toSpuResponse(Spu spu) {
    return new SpuDetailResponse(spu.getId(), spu.getCode(), spu.getName(), spu.getPrice(), spu.getBidPrice(),
        spu.getSpuCategory().getName(), spu.getStock(), spu.getStatus(), spu.getPromotions());
  }
}
