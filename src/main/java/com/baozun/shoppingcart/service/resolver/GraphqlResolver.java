package com.baozun.shoppingcart.service.resolver;

import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.controller.vo.response.SpuPageData;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.service.SpuService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GraphqlResolver implements GraphQLQueryResolver {

  private final SpuService spuService;

  public SpuPageData spuList(SpuQueryRequest parameter) {
    Page<Spu> spuList = spuService.findAll(parameter);
    //TODO
    return new SpuPageData(parameter.getPageNumber(), parameter.getPageSize(), spuList.getTotalElements(), spuList.getContent());
  }

}
