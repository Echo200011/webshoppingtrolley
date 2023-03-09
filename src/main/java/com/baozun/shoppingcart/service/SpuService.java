package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import com.baozun.shoppingcart.dao.model.SpuQueryParameter;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpuService {

  private final SpuRepository spuRepository;

  private final SpuPromotionMappingService spuPromotionMappingService;


  public Page<Spu> findAll(Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return spuRepository.findAll(pageable);
  }

  public Page<Spu> findAll(SpuQueryParameter parameter, Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return spuRepository.findAll(parameter,pageable);
  }

  public Spu findById(Integer id) {
    return spuRepository.findById(id).orElse(null);
  }

  @Transactional
  public List<Spu> saveAll(List<Spu> spuList) {
    return spuRepository.saveAll(spuList);
  }

  @Transactional
  public Spu saveSpuAndPromotions(Spu spu, Integer... promotionsId) {
    if (ObjectUtils.isEmpty(spu)) {
      return null;
    }
    if (promotionsId.length == 0) {
      return spuRepository.save(spu);
    }
    try {
      Spu finalSpu = spuRepository.save(spu);
      Arrays.stream(promotionsId).forEach(pId -> spuPromotionMappingService.save(finalSpu.getId(), pId));
    } catch (Exception e) {
      throw new IllegalArgumentException("数据库存储错误", e);
    }
    return spu;
  }
}
