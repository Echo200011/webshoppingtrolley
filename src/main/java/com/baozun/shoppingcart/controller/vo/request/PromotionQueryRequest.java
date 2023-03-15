package com.baozun.shoppingcart.controller.vo.request;

import com.baozun.shoppingcart.dao.model.DetailTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Past;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionQueryRequest extends PageParameterRequest {

  private DetailTypeEnum type;

  private String promotionName;

  @Past(message = "不能大于当前年月")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;
}
