package com.baozun.shoppingcart.controller;

import com.baozun.shoppingcart.controller.vo.request.PageParameterRequest;
import com.baozun.shoppingcart.controller.vo.request.SpuRequest;
import com.baozun.shoppingcart.controller.vo.response.SpuResponse;
import com.baozun.shoppingcart.dao.model.Spu;
import com.baozun.shoppingcart.controller.vo.request.SpuQueryRequest;
import com.baozun.shoppingcart.service.SpuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spuList")
@RequiredArgsConstructor
public class SpuManagementController {

  private final SpuService spuService;

  @GetMapping
  public List<SpuResponse> findSpuAll(PageParameterRequest pageParameterRequest) {
    Page<Spu> spuList = spuService.findAll(pageParameterRequest);
    return SpuResponse.toSpuResponseList(spuList.getContent());
  }

  @GetMapping("/findSpuAllByParameter")
  public List<SpuResponse> findSpuAllByParameter(SpuQueryRequest parameter) {
    Page<Spu> spuList = spuService.findAllByParameter(parameter);
    return SpuResponse.toSpuResponseList(spuList.getContent());
  }

  @GetMapping("/{spuId}")
  public SpuResponse findSpuById(@PathVariable("spuId") Integer spuId) {
    return spuService.findById(spuId);
  }

  @PostMapping
  public SpuResponse saveSpu(@RequestBody SpuRequest spuRequest) {
    return spuService.saveSpu(spuRequest);
  }

  @DeleteMapping("/{spuId}")
  public void deleteSpuById(@PathVariable("spuId") Integer spuId) {
    spuService.deleteSpuById(spuId);
  }

  //单个修改
  @PutMapping
  public List<Spu> updateSpuAll(@RequestBody List<Spu> spuList) {
    return spuService.updateAll(spuList);
  }
}
