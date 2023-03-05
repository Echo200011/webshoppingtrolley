package com.baozun.webshoppingtrolley.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class Detail implements Serializable {

  private String condition;

  private Integer value;
}
