package com.baozun.shoppingcart.dao.model;

import com.baozun.shoppingcart.dao.model.converter.DetailConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "promotion")
@SQLDelete(sql = "update promotion set is_delete = 1 where id = ?")
@Where(clause = "is_delete = 0")
public class Promotion {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private DetailTypeEnum type;

  @Column(name = "description")
  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "create_time",insertable = false,updatable = false)
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @Convert(converter = DetailConverter.class)
  private AbstractPromotionDetail detail;

  @Column(name = "is_delete")
  private boolean delete;

}
