package com.baozun.shoppingcart.dao;

import com.baozun.shoppingcart.dao.model.ShoppingCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {


  List<ShoppingCart> findByUserId(Integer userId);
}
