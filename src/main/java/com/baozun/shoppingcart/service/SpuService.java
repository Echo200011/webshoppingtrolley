package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.dao.SpuCategoryRepository;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.SpuRepository;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.exception.AppException;
import com.baozun.shoppingcart.exception.AppExceptionCodeMsg;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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

  public Spu findById(Integer spuId) {
    return findSpu(spuId);
  }

  public Page<Spu> findAll(SpuQueryRequest parameter) {
    if (parameter.getMinPrice() > parameter.getMaxPrice()) {
      throw new AppException(AppExceptionCodeMsg.PRICE_RANGE_ERROR);
    }
    Page<Spu> spuList = findSpuList(parameter);
    if (CollectionUtils.isEmpty(spuList.getContent())) {
      throw new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST);
    }
    return spuList;
  }

  public Spu saveSpu(SpuRequest spuRequest) {
    if (spuRepository.findByCode(spuRequest.getCode()) > 0) {
      throw new AppException(AppExceptionCodeMsg.SPU_IS_EXIST);
    }
    spuCategoryRepository
        .findById(spuRequest.getCategoryId())
        .orElseThrow(() -> new AppException(AppExceptionCodeMsg.CATEGORY_NOT_EXIST));
    return spuRepository.save(spuRequest.toSpu());
  }

  public Spu updateSpu(UpdateSpuRequest updateSpu) {
    Spu spu = findSpu(updateSpu.getId());
    if (!ObjectUtils.isEmpty(updateSpu.getCategoryId())) {
      spuCategoryRepository
          .findById(updateSpu.getId())
          .orElseThrow(() -> new AppException(AppExceptionCodeMsg.CATEGORY_NOT_EXIST));
    }
    if (
        !ObjectUtils.isEmpty(updateSpu.getPrice())
            && !ObjectUtils.isEmpty(updateSpu.getBidPrice())
            && updateSpu.getPrice() < updateSpu.getBidPrice()
    ) {
      throw new AppException(AppExceptionCodeMsg.PRICE_SETTING_ERROR);
    }
    if (
        ObjectUtils.isEmpty(updateSpu.getPrice())
            && !ObjectUtils.isEmpty(updateSpu.getBidPrice())
            && spu.getPrice() < updateSpu.getBidPrice()
    ) {
      throw new AppException(AppExceptionCodeMsg.PRICE_SETTING_ERROR);
    }
    return spuRepository.save(updateSpu.toSpu(spu));
  }

  public void onShelves(Integer spuId) {
    if (spuId < 0) {
      throw new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST);
    }
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

  public void soldOut(Integer spuId) {
    if (spuId < 0) {
      throw new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST);
    }
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

  public void deleteSpuById(Integer spuId) {
    if (spuId < 0) {
      throw new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST);
    }
    spuRepository.deleteById(spuId);
  }

  private Spu findSpu(Integer spuId) {
    return spuRepository.findById(spuId).orElseThrow(() -> new AppException(AppExceptionCodeMsg.SPU_NOT_EXIST));
  }

  private Page<Spu> findSpuList(SpuQueryRequest parameter) {
    Pageable pageable = PageRequest.of(parameter.getPageNumber(), parameter.getPageSize());
    return spuRepository.findAll((Specification<Spu>) (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (ObjectUtils.isNotEmpty(parameter.getName())) {
        Path<Object> name = root.get("name");
        Predicate findByName = cb.like(name.as(String.class), parameter.getName());
        predicates.add(findByName);
      }
      if (ObjectUtils.isNotEmpty(parameter.getStatus())) {
        Path<Object> status = root.get("status");
        Predicate findByStatus = cb.equal(status, parameter.getStatus());
        predicates.add(findByStatus);
      }
      if (ObjectUtils.isNotEmpty(parameter.getCategoryName())) {
        Join<Spu, SpuRepository> join = root.join("spuCategory", JoinType.INNER);
        Predicate findByCategoryName = cb.equal(join.get("name"), parameter.getCategoryName());
        predicates.add(findByCategoryName);
      }
      if (ObjectUtils.isNotEmpty(parameter.getPromotionName())) {
        Join<Spu, Promotion> join = root.join("promotions", JoinType.LEFT);
        Predicate findByPromotionName = cb.equal(join.get("name"), parameter.getPromotionName());
        predicates.add(findByPromotionName);
      }
      if (parameter.getMaxPrice() > 0) {
        Path<Object> discount = root.get("discount");
        cb.between(discount.as(Integer.class), parameter.getMinPrice(), parameter.getMaxPrice());
      }
      if (ObjectUtils.isNotEmpty(parameter.getCreateTime())) {
        Predicate createTime = cb.equal(root.get("createTime"), parameter.getCreateTime());
        predicates.add(createTime);
      }
      predicates.forEach(predicate -> query.where(cb.and(predicate)));
      return query.getRestriction();
    }, pageable);
  }
}
