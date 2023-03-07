package com.baozun.shoppingcart.dao.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "spu_categories")
@Data
public class SpuCategories {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "parent_id")
  private Integer parentId;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id")
  private List<SpuCategories> childCategories;
}
