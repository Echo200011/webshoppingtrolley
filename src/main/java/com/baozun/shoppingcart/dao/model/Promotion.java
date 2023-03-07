package com.baozun.shoppingcart.dao.model;

import com.baozun.shoppingcart.dao.model.converter.DetailConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "promotion")
public class Promotion {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private PromotionTypeEnum type;

  @Column(name = "description")
  private String description;

  @Convert(converter = DetailConverter.class)
  private AbstractPromotionDetail detail;

}
