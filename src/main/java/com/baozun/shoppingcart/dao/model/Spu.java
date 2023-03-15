package com.baozun.shoppingcart.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
  private Integer price = 0;

  @Column(name = "bid_price")
  private Integer bidPrice = 0;

  @Column(name = "stock")
  private Integer stock = 0;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private SpuStatusEnum status = SpuStatusEnum.NEW;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "is_delete")
  private boolean delete;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "create_time",insertable = false,updatable = false)
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "spu_promotion_mapping",
      joinColumns = {@JoinColumn(name = "spu_id")},
      inverseJoinColumns = {@JoinColumn(name = "promotion_id")}
  )
  private List<Promotion> promotions;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private SpuCategory spuCategory;
}
