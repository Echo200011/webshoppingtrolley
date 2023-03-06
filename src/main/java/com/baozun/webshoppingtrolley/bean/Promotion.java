package com.baozun.webshoppingtrolley.bean;

import com.baozun.webshoppingtrolley.AttributeConverter.DetailConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "promotion")
@NoArgsConstructor
public class Promotion {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Convert(converter = DetailConverter.class)
  @Column(name = "detail")
  private List<Detail> details = new ArrayList<>();

  public void setDetails(Detail... details) {
    if (details.length == 0) {
      return;
    }
    this.details.addAll(Arrays.asList(details));
  }

  public void setDetails(List<Detail> details) {
    this.details.addAll(details);
  }
}
