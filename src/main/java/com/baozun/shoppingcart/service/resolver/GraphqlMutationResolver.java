package com.baozun.shoppingcart.service.resolver;

import com.baozun.shoppingcart.controller.vo.request.ShoppingCartRequest;
import com.baozun.shoppingcart.service.ShoppingCartService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraphqlMutationResolver implements GraphQLMutationResolver {


  private final ShoppingCartService shoppingCartService;

  public Integer saveShoppingCartLine(ShoppingCartRequest shoppingCartRequest){
    return shoppingCartService.save(shoppingCartRequest);
  }
}
