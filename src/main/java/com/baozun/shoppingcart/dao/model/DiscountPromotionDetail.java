package com.baozun.shoppingcart.dao.model;


import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DiscountPromotionDetail extends AbstractPromotionDetail {

  private Integer full;

  private Integer discount;

  public DiscountPromotionDetail() {
    setLevel(2);
    setType(DetailTypeEnum.DISCOUNT);
  }


  @Override
  public void calculatePrice(List<Spu> spuList) {
    int sum = spuList.stream().mapToInt(Spu::getBidPrice).sum();
    if (sum<full){
      return;
    }
    spuList.forEach(spu -> spu.setBidPrice((full-discount)*spu.getBidPrice()/full));
  }
}
