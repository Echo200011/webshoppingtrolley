package com.baozun.shoppingcart.dao;


import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpuRepository extends JpaRepository<Spu, Integer> {

  @Query(value = "select t1.*,t2.name,t4.name from spu as t1 "
      + "join spu_categories as t2 "
      + "on t1.category_id=t2.id "
      + "left join spu_promotion_mapping as t3 "
      + "on t1.id=t3.spu_id "
      + "left join promotion as t4 "
      + "on t3.promotion_id=t4.id"
      + " where if(:#{#parameter.name} !='',t1.name like :#{#parameter.name},1=1) "
      + "and if(:#{#parameter.categoryName} !='',t2.name like  :#{#parameter.name},1=1) "
      + "and if(:#{#parameter.createTime}!='',t1.create_time like '%:#{#parameter.createTime}%',1=1 )"
      + "and if(:#{#parameter.status}!='',t1.status = ':#{#parameter.status}',1=1 ) "
      + "and if(:#{#parameter.promotionName} !='',t4.name like :#{#parameter.name},1=1) "
      + "and if(:#{#parameter.maxPrice}!=0,t1.price between :#{#parameter.minPrice} and :#{#parameter.maxPrice} ,1=1)", nativeQuery = true)
  Page<Spu> findAll(@Param("parameter") SpuQueryRequest parameter, Pageable pageable);


}
