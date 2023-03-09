package com.baozun.shoppingcart.service.resolver;

import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.dao.model.SpuPageData;
import com.baozun.shoppingcart.dao.model.SpuQueryParameter;
import com.baozun.shoppingcart.service.SpuService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GraphqlResolver implements GraphQLQueryResolver {

  private final SpuService spuService;

  public SpuPageData spuList(Integer pageNumber, Integer pageSize, SpuQueryParameter parameter) {
    Page<Spu> page = spuService.findAll(parameter, pageNumber, pageSize);
    return new SpuPageData(pageNumber, pageSize, page.getTotalElements(), page.getContent());
  }

}
