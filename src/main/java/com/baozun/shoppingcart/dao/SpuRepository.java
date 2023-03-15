package com.baozun.shoppingcart.dao;


import com.baozun.shoppingcart.dao.model.Spu;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SpuRepository extends JpaRepository<Spu, Integer> , JpaSpecificationExecutor<Spu> {

  @Query(value = "select count(code) from spu where code=?1", nativeQuery = true)
  Integer findByCode(String code);

  @Modifying
  @Transactional
  @Query(value = "update spu set status='ON_SHELVES'where id =?1  and stock > 0", nativeQuery = true)
  Integer onShelves(Integer spuId);

  @Modifying
  @Transactional
  @Query(value = "update spu set status='SOLD_OUT' where id =?1  and status = 'ON_SHELVES'", nativeQuery = true)
  Integer soldOut(Integer spuId);

}
