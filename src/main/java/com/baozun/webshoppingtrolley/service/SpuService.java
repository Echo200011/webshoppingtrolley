package com.baozun.webshoppingtrolley.service;

import com.baozun.webshoppingtrolley.bean.Spu;
import com.baozun.webshoppingtrolley.mapper.SpuRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuService {

  @Autowired
  private SpuRepository spuRepository;

  @Transactional
  public List<Spu> findAll() {
    return spuRepository.findAll();
  }

  @Transactional
  public Spu findById(Integer id) {
    return spuRepository.findById(id).orElse(null);
  }
}
