package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.controller.vo.request.UpdateSpuRequest;
import com.baozun.shoppingcart.controller.vo.response.SpuDetailResponse;
import com.baozun.shoppingcart.controller.vo.response.SpuResponse;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.dao.model.SpuStatusEnum;
import com.baozun.shoppingcart.exception.Result;
import com.baozun.shoppingcart.service.SpuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spuList")
@RequiredArgsConstructor
public class SpuController {

  private final SpuService spuService;

  @ApiOperation(value = "查询所有商品", notes = "根据条件分页查询")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "minPrice", value = "最小价格", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "maxPrice", value = "最大价格", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "categoryName", value = "商品种类", dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "promotionName", value = "活动名称", dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间"),
      @ApiImplicitParam(paramType = "query", name = "status", value = "商品状态", dataTypeClass = SpuStatusEnum.class),
  })
  @GetMapping()
  public Result<Page<SpuResponse>> findSpuAll(@Valid SpuQueryRequest parameter) {
    Page<Spu> spuList = spuService.findAll(parameter);
    return Result.success(spuList.map(SpuResponse::toSpuResponse));
  }

  @ApiOperation(value = "查询商品", notes = "根据id查询")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "path", name = "spuId", value = "商品id", required = true, dataTypeClass = Integer.class)
  })
  @GetMapping("/{spuId}")
  public Result<SpuDetailResponse> findSpuById(@PathVariable("spuId") Integer spuId) {
    Spu spu = spuService.findById(spuId);
    SpuDetailResponse spuDetailResponse = SpuDetailResponse.toSpuResponse(spu);
    return Result.success(spuDetailResponse);
  }

  @ApiOperation(value = "新增商品", notes = "根据可传入属性新增")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "code", value = "商品编码", required = true, dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = true, dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "price", value = "原价", required = true, dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "discount", value = "标价", required = true, dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "categoryId", value = "种类id", required = true, dataTypeClass = Integer.class),
  })
  @PostMapping
  public Result<Integer> saveSpu(@Valid SpuRequest spuRequest) {
    Spu spu = spuService.saveSpu(spuRequest);
    return Result.success(spu.getId());
  }

  @ApiOperation(value = "更新商品", notes = "根据可修改属性更新商品")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "id", value = "更改的商品id", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", dataTypeClass = String.class),
      @ApiImplicitParam(paramType = "query", name = "price", value = "原价", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "discount", value = "标价", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "categoryId", value = "种类id", dataTypeClass = Integer.class),
      @ApiImplicitParam(paramType = "query", name = "stock", value = "库存", dataTypeClass = Integer.class)
  })
  @PatchMapping
  public Result<Integer> updateSpu(@Valid UpdateSpuRequest updateSpuRequest) {
    Spu spu = spuService.updateSpu(updateSpuRequest);
    return Result.success(spu.getId());
  }

  @PatchMapping("/onShelves/{spuId}")
  public Result<Integer> onShelves(@PathVariable("spuId") Integer spuId) {
    spuService.onShelves(spuId);
    return Result.success(spuId);
  }

  @PatchMapping("/soldOut/{spuId}")
  public Result<Integer> soldOut(@PathVariable("spuId") @Valid @PositiveOrZero Integer spuId) {
    spuService.soldOut(spuId);
    return Result.success(spuId);
  }


  @DeleteMapping("/{spuId}")
  public Result<Integer> deleteSpuById(@PathVariable("spuId") Integer spuId) {
    spuService.deleteSpuById(spuId);
    return Result.success(spuId);
  }

}
