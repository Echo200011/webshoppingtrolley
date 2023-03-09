package com.baozun.shoppingcart.dao.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpuPageData {

  private Integer pageNumber;

  private Integer pageSize;

  private long total;

  private List<Spu> spuList;
}
