package com.baozun.shoppingcart.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spu_promotion_mapping")
@Data
@NoArgsConstructor
public class SpuPromotionMapping {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "spu_id")
  private Integer spuId;

  @Column(name = "promotion_id")
  private Integer promotionId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "create_time", insertable = false, updatable = false)
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  public SpuPromotionMapping(Integer spuId, Integer promotionId) {
    this.spuId = spuId;
    this.promotionId = promotionId;
  }

}
