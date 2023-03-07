package com.baozun.shoppingcart.dao.model;


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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spu")
@Data
@NoArgsConstructor
public class Spu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Integer price;

  @Column(name = "discount")
  private Integer discount;

  @Column(name = "category_id")
  private Integer categoryId;

  @OneToOne
  @JoinColumn(name = "id")
  private SpuCategories spuCategory;

  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "spu_promotion_mapping", joinColumns = {
      @JoinColumn(name = "spu_id")
  }, inverseJoinColumns = {@JoinColumn(name = "promotion_id")})
  private List<Promotion> promotions;

}
