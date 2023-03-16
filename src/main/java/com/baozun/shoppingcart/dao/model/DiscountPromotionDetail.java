package com.baozun.shoppingcart.dao.model;


import com.baozun.shoppingcart.controller.vo.response.SpuData;
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
  public void calculatePrice(List<SpuData> spuData) {
    long sum = spuData.stream().mapToInt(SpuData::getPromotionPrice).sum();
    if (sum < (full * 1000)) {
      return;
    }
    long total = sum - (sum / (full*1000) * (discount*1000));
    spuData.forEach(
        spuResult -> spuResult.setPromotionPrice((setBidPrice(spuResult.getPromotionPrice(), sum, total)))
    );
  }

  private int setBidPrice(int bidPrice, long sum, long total) {
    return (int) ((total * bidPrice) / sum);
  }
}
