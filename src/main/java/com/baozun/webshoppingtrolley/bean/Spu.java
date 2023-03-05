package com.baozun.webshoppingtrolley.bean;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "spu")
@Data
public class Spu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "spu_name")
  private String name;

  @Column(name = "spu_price")
  private Integer price;

  @Column(name = "spu_discount")
  private Integer discount;

  @Column(name = "spu_category")
  private String category;

  @Column(name = "spu_code")
  private String code;

  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "spu_promotion_mapping", joinColumns = {
      @JoinColumn(name = "spu_id")
  }, inverseJoinColumns = {@JoinColumn(name = "id")})
  private List<Promotion> promotions;

}
