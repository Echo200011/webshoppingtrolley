package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.controller.vo.request.PromotionQueryRequest;
import com.baozun.shoppingcart.controller.vo.request.PromotionRequest;
import com.baozun.shoppingcart.controller.vo.request.UpdatePromotionRequest;
import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.PromotionRepository;
import com.baozun.shoppingcart.exception.AppException;
import com.baozun.shoppingcart.exception.AppExceptionCodeMsg;
import java.util.ArrayList;
import java.util.List;
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
public class PromotionService {

  private final PromotionRepository promotionRepository;

  public Promotion findById(Integer promotionId) {
    return findPromotion(promotionId);
  }

  public Page<Promotion> findAll(PromotionQueryRequest parameter) {
    Page<Promotion> promotions = findPromotions(parameter);
    if (CollectionUtils.isEmpty(promotions.getContent())){
      throw new AppException(AppExceptionCodeMsg.PROMOTION_NOT_EXIST);
    }
    return findPromotions(parameter);
  }

  public void deletePromotionById(Integer spuId) {
    if (spuId < 0) {
      throw new AppException(AppExceptionCodeMsg.PROMOTION_NOT_EXIST);
    }
    promotionRepository.deleteById(spuId);
  }

  public Promotion savePromotion(PromotionRequest promotionRequest) {
    return promotionRepository.save(promotionRequest.toPromotion());
  }

  public Promotion updatePromotion(UpdatePromotionRequest updatePromotionRequest) {
    Promotion promotion = findPromotion(updatePromotionRequest.getId());
    return promotionRepository.save(updatePromotionRequest.toPromotion(promotion));
  }

  private Promotion findPromotion(Integer promotionId) {
    return promotionRepository
        .findById(promotionId)
        .orElseThrow(() -> new AppException(AppExceptionCodeMsg.PROMOTION_NOT_EXIST));
  }

  private Page<Promotion> findPromotions(PromotionQueryRequest parameter) {
    Pageable pageable = PageRequest.of(parameter.getPageNumber(), parameter.getPageSize());
    return promotionRepository.findAll((Specification<Promotion>) (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (ObjectUtils.isNotEmpty(parameter.getPromotionName())) {
        Predicate predicate = cb.equal(root.get("name"), parameter.getPromotionName());
        predicates.add(predicate);
      }
      if (ObjectUtils.isNotEmpty(parameter.getType())) {
        Predicate predicate = cb.equal(root.get("type"), parameter.getType());
        predicates.add(predicate);
      }
      if (ObjectUtils.isNotEmpty(parameter.getCreateTime())) {
        Predicate predicate = cb.equal(root.get("createTime"), parameter.getCreateTime());
        predicates.add(predicate);
      }
      predicates.forEach(predicate -> query.where(cb.and(predicate)));
      return query.getRestriction();
    }, pageable);
  }
}
