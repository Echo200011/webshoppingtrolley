package com.baozun.webshoppingtrolley.bean;

import com.baozun.webshoppingtrolley.AttributeConverter.DetailConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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

  @Column(name = "promotion_name")
  private String name;

  @Convert(converter = DetailConverter.class)
  @Column(name = "promotion_detail")
  private Detail detail;
}
