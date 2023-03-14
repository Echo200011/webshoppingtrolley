package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.controller.vo.request.PageParameterRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.dao.model.SpuCategory;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.dao.SpuCategoryRepository;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.exception.AppException;
import com.baozun.shoppingcart.exception.AppExceptionCodeMsg;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpuService {

  private final SpuRepository spuRepository;

  private final SpuCategoryRepository spuCategoryRepository;

  public Page<Spu> findAll(PageParameterRequest pageParameterRequest) {
    Pageable pageable = PageRequest.of(pageParameterRequest.getPageNumber(), pageParameterRequest.getPageSize());
    return spuRepository.findAll(pageable);
  }

  public Page<Spu> findAllByParameter(SpuQueryRequest parameter) {
    if (
        ObjectUtils.allNotNull(parameter.getMinPrice(), parameter.getMaxPrice())
            && parameter.getMinPrice() > parameter.getMaxPrice()
    ) {
      throw new AppException(AppExceptionCodeMsg.PRICE_RANGE_ERROR);
    }
    Pageable pageable = PageRequest.of(parameter.getPageNumber(), parameter.getPageSize());
    return spuRepository.findAll(parameter, pageable);
  }

  public Spu findById(Integer spuId) {
    return findSpu(spuId);
  }

  @Transactional
  public Spu saveSpu(SpuRequest spuRequest) {
    if (spuRepository.findByCode(spuRequest.getCode()) > 0) {
      throw new AppException(AppExceptionCodeMsg.SPU_IS_EXIST);
    }
    SpuCategory spuCategory = spuCategoryRepository
        .findById(spuRequest.getCategoryId())
        .orElseThrow(() -> new AppException(AppExceptionCodeMsg.CATEGORY_NOT_EXIST));
    return spuRepository.save(spuRequest.toSpu());
  }

  @Transactional
  public Spu updateSpu(UpdateSpuRequest updateSpu) {
    Spu spu = findSpu(updateSpu.getId());
    if (!ObjectUtils.isEmpty(updateSpu.getCategoryId())) {
      spuCategoryRepository
          .findById(updateSpu.getId())
          .orElseThrow(() -> new AppException(AppExceptionCodeMsg.CATEGORY_NOT_EXIST));
    }
    if (
        !ObjectUtils.isEmpty(updateSpu.getPrice())
            && !ObjectUtils.isEmpty(updateSpu.getDiscount())
            && updateSpu.getPrice() < updateSpu.getDiscount()
    ) {
      throw new AppException(AppExceptionCodeMsg.PRICE_SETTING_ERROR);
    }
    if (
        ObjectUtils.isEmpty(updateSpu.getPrice())
            && !ObjectUtils.isEmpty(updateSpu.getDiscount())
            && spu.getPrice() < updateSpu.getDiscount()
    ) {
      throw new AppException(AppExceptionCodeMsg.PRICE_SETTING_ERROR);
    }
    return spuRepository.saveAndFlush(updateSpu.toSpu(spu));
  }

  @Transactional
  public void onShelves(Integer spuId) {
    Spu spu = findSpu(spuId);
    if (spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)) {
      throw new AppException(AppExceptionCodeMsg.SPU_ALREADY_ON_SHELVES);
    }
    if (spu.getStock() <= 0) {
      throw new AppException(AppExceptionCodeMsg.STOCK_MUST_HAVE);
    }
    if (spuRepository.onShelves(spuId) <= 0) {
      throw new AppException(AppExceptionCodeMsg.ON_SHELVES_FAILED);
    }
  }

  @Transactional
  public void soldOut(Integer spuId) {
    Spu spu = findSpu(spuId);
    if (spu.getStatus().equals(SpuStatusEnum.SOLD_OUT)) {
      throw new AppException(AppExceptionCodeMsg.SPU_ALREADY_SOLD_OUT);
    }
    if (!spu.getStatus().equals(SpuStatusEnum.ON_SHELVES)) {
      throw new AppException(AppExceptionCodeMsg.SPU_MUST_ON_SHELVES);
    }
    if (spuRepository.soldOut(spuId) <= 0) {
      throw new AppException(AppExceptionCodeMsg.SOLD_OUT_FAILED);
    }
  }

  @Transactional
  public void deleteSpuById(Integer spuId) {
    spuRepository.deleteById(spuId);
  }

  private Spu findSpu(Integer spuId) {
    return spuRepository.findById(spuId).orElseThrow(() -> new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST));
  }

}
