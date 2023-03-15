package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.controller.vo.request.PromotionQueryRequest;
import com.baozun.shoppingcart.controller.vo.request.PromotionRequest;
import com.baozun.shoppingcart.controller.vo.request.UpdatePromotionRequest;
import com.baozun.shoppingcart.controller.vo.response.PromotionResponse;
import com.baozun.shoppingcart.dao.model.DetailTypeEnum;
import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.exception.Result;
import com.baozun.shoppingcart.service.PromotionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

  private final PromotionService promotionService;

  @ApiOperation(value = "查询所有活动", notes = "根据可传入属性查询")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "type", value = "活动类型", dataTypeClass = DetailTypeEnum.class),
      @ApiImplicitParam(paramType = "query", name = "promotionName", value = "活动名称", required = true, dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间")
  })
  @GetMapping
  public Result<Page<PromotionResponse>> findAll(@Valid PromotionQueryRequest promotionQueryRequest) {
    Page<Promotion> promotions = promotionService.findAll(promotionQueryRequest);
    return Result.success(promotions.map(PromotionResponse::toPromotionResponse));
  }

  @GetMapping("/{promotionId}")
  public Result<PromotionResponse> findPromotionById(@PathVariable("promotionId") Integer promotionId) {
    Promotion promotion = promotionService.findById(promotionId);
    return Result.success(PromotionResponse.toPromotionResponse(promotion));
  }

  @PostMapping
  public Result<Integer> savePromotion(@Valid @RequestBody PromotionRequest promotionRequest) {
    Promotion promotion = promotionService.savePromotion(promotionRequest);
    return Result.success(promotion.getId());
  }

  @PatchMapping
  public Result<Integer> updatePromotion(@Valid UpdatePromotionRequest updatePromotionRequest) {
    Promotion promotion = promotionService.updatePromotion(updatePromotionRequest);
    return Result.success(promotion.getId());
  }

  @DeleteMapping("/{promotionId}")
  public Result<Integer> deletePromotion(@PathVariable("promotionId") Integer promotionId) {
    promotionService.deletePromotionById(promotionId);
    return Result.success(promotionId);
  }


}
