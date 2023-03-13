package com.baozun.shoppingcart.dao.model;


import com.baozun.shoppingcart.controller.vo.request.SpuStatusEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "spu")
@Data
@NoArgsConstructor
@SQLDelete(sql = "update spu  set is_delete = 1  where id = ?")
@Where(clause = "is_delete = 0")
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

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private SpuStatusEnum status;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "is_delete")
  private boolean delete;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "spu_promotion_mapping",
      joinColumns = {@JoinColumn(name = "spu_id")},
      inverseJoinColumns = {@JoinColumn(name = "promotion_id")}
  )
  private List<Promotion> promotions;

  @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private SpuCategories spuCategory;


}
