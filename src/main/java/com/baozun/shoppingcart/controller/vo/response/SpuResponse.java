package com.baozun.shoppingcart.controller.vo.response;

import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuCategories;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpuResponse {

  private Integer id;

  private String code;

  private String name;

  private Integer price;

  private Integer discount;

  private SpuCategories spuCategory;

  public static List<SpuResponse> toSpuResponseList(List<Spu> spuList) {
    return spuList.stream()
        .map(SpuResponse::toSpuResponse)
        .collect(Collectors.toList());
  }

  public static SpuResponse toSpuResponse(Spu spu) {
    return new SpuResponse(spu.getId(), spu.getCode(), spu.getName(), spu.getPrice(), spu.getDiscount(),
        spu.getSpuCategory());
  }
}
