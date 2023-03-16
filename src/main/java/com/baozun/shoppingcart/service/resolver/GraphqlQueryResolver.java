package com.baozun.shoppingcart.service.resolver;

import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.controller.vo.response.ShoppingCartData;
import com.baozun.shoppingcart.controller.vo.response.SpuPageData;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.service.ShoppingCartService;
import com.baozun.shoppingcart.service.SpuService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GraphqlQueryResolver implements GraphQLQueryResolver {

  private final SpuService spuService;

  private final ShoppingCartService shoppingCartService;

  public SpuPageData spuList(SpuQueryRequest parameter) {
    Page<Spu> spuList = spuService.findAll(parameter);
    return new SpuPageData(parameter.getPageNumber(), parameter.getPageSize(), spuList.getTotalElements(),
        spuList.getContent());
  }

  public ShoppingCartData shoppingCart(Integer code) {
    return shoppingCartService.createOrder(code);
  }
}
