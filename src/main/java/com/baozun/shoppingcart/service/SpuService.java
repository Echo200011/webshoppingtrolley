package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.controller.vo.request.PageParameterRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuStatusEnum;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.controller.vo.response.SpuResponse;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import java.util.List;
import java.util.Optional;
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

  public Page<Spu> findAll(PageParameterRequest pageParameterRequest) {
    Pageable pageable = PageRequest.of(pageParameterRequest.getPageNumber(), pageParameterRequest.getPageSize());
    return spuRepository.findAll(pageable);
  }

  public Page<Spu> findAllByParameter(SpuQueryRequest parameter) {
    Pageable pageable = PageRequest.of(parameter.getPageNumber(), parameter.getPageSize());
    return spuRepository.findAll(parameter, pageable);
  }

  public SpuResponse findById(Integer spuId) {
    Spu spu = spuRepository.findById(spuId).orElse(null);
    if (ObjectUtils.isEmpty(spu)) {
      return null;
    }
    return SpuResponse.toSpuResponse(spu);
  }

  @Transactional
  public SpuResponse saveSpu(SpuRequest spuRequest) {
    return SpuResponse.toSpuResponse(spuRepository.save(spuRequest.toSpu()));
  }

  public SpuResponse test(UpdateSpuRequest updateSpuRequest) {
    if (ObjectUtils.isEmpty(updateSpuRequest.getId())) {
      return null;
    }
    Spu spu = spuRepository.findById(updateSpuRequest.getId()).orElse(null);
    if (ObjectUtils.isEmpty(spu)) {
      return null;
    }
    if (ObjectUtils.isEmpty(updateSpuRequest.getStatus())) {
      //TODO
    }
    if (updateSpuRequest.getStatus().equals(SpuStatusEnum.ON_SHELVES) && spu.getStatus().equals(SpuStatusEnum.NEW)
        && spu.getInventory() < 0) {
      //TODO
    }
    if (updateSpuRequest.getStatus().equals(SpuStatusEnum.SOLD_OUT) && !(spu.getStatus()
        .equals(SpuStatusEnum.ON_SHELVES))) {
      //TODO
    }
    return null;
  }

  @Transactional
  public void deleteSpuById(Integer spuId) {
    spuRepository.deleteById(spuId);
  }

  @Transactional
  public List<Spu> updateAll(List<Spu> spuList) {
    return spuRepository.saveAllAndFlush(spuList);
  }

}
