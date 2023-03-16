package com.baozun.shoppingcart.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_cart_line")
public class ShoppingCartLine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "spu_id")
  private Integer spuId;
  @Column(name = "code")
  private Integer code;
  @Column(name = "count")
  private Integer count = 1;

  @OneToOne
  @JoinColumn(name = "spu_id", insertable = false, updatable = false)
  private Spu spu;
}
