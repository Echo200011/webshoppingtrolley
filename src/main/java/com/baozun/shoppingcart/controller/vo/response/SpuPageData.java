package com.baozun.shoppingcart.controller.vo.response;

import com.baozun.shoppingcart.dao.model.Spu;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data()
@AllArgsConstructor
@NoArgsConstructor
public class SpuPageData {

  private Integer pageNumber;

  private Integer pageSize;

  private long total;

  //TODO 修改为SpuResponse
  private List<Spu> spuList;

  public SpuPageData setTotal() {
    this.total = 1;
    return this;
  }
}
