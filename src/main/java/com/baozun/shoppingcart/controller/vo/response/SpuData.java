package com.baozun.shoppingcart.controller.vo.response;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.Spu;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpuData {

  private Integer id;

  private String name;

  private Integer price;

  private Integer promotionPrice;

  private Integer count;

  private String spuCategoryName;

  private SpuData giftSpu;

  private List<Promotion> promotions;

  public static SpuData toSpuSpuResult(Spu spu, Integer count) {
    return new SpuData(spu.getId(), spu.getName(), spu.getBidPrice() * count, spu.getBidPrice() * count,
        count, spu.getSpuCategory().getName(),null, spu.getPromotions());
  }


}
