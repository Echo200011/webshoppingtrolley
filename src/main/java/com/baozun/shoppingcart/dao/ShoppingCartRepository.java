package com.baozun.shoppingcart.dao;

import com.baozun.shoppingcart.dao.model.ShoppingCartLine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartLine, Integer> {


  List<ShoppingCartLine> findByCode(Integer userId);
}
