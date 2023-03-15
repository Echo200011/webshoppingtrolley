package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime createTime;

  private SpuStatusEnum status;
}
