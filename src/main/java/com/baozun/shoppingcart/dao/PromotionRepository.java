package com.baozun.shoppingcart.dao;

import com.baozun.shoppingcart.dao.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> , JpaSpecificationExecutor<Promotion> {

}
