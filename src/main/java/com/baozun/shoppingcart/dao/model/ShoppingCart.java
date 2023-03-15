package com.baozun.shoppingcart.dao.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_cart")
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "spu_id")
  private Integer spuId;
  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "price")
  private Integer price;
  @Column(name = "discount")
  private Integer discount = 0;
  @Column(name = "count")
  private Integer count = 0;

  @OneToOne
  @JoinColumn(name = "spu_id", insertable = false, updatable = false)
  private Spu spu;
}
