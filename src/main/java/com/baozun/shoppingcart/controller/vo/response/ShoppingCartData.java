package com.baozun.shoppingcart.controller.vo.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartData {

  private Integer code;
  private List<SpuData> spuData;

}
