package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import java.sql.Timestamp;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpuQueryRequest extends PageParameterRequest {

  private String name;
  @PositiveOrZero(message = "最小价格不可为负")
  private Integer minPrice = 0;
  @PositiveOrZero(message = "最小价格不可为负")
  private Integer maxPrice = 0;

  private String categoryName;

  private String promotionName;

  @Past(message = "不能大于当前年月")
  private Timestamp createTime;

  private SpuStatusEnum status;
}
