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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "spu_categories")
@Data
@SQLDelete(sql = "update spu_categories set is_delete = 1 where id = ?")
@Where(clause = "is_delete = 0")
public class SpuCategories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "parent_id")
  private Integer parentId;

  @Column(name = "is_delete")
  private boolean delete;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private List<SpuCategories> childCategories;
}
