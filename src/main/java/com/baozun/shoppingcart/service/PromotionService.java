package com.baozun.shoppingcart.service;

import com.baozun.shoppingcart.dao.model.Promotion;
import com.baozun.shoppingcart.dao.PromotionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private PromotionRepository promotionRepository;


  public Promotion findById(Integer id) {
    return promotionRepository.findById(id).orElse(null);
  }

  @Transactional
  public List<Promotion> save(List<Promotion> promotions) {
    return promotionRepository.saveAllAndFlush(promotions);
  }

  @Transactional
  public void delete(Integer... id) {
    if (id.length == 0) {
      return;
    }
    Arrays.stream(id).forEach(i -> promotionRepository.deleteById(i));
  }

  @SneakyThrows
  public List<Promotion> convertPromotionData( JsonNode promotionData) {
    TypeReference<List<Promotion>> typeReference = new TypeReference<List<Promotion>>() {
    };
    List<Promotion> promotions = objectMapper.readValue(String.valueOf(promotionData.get("promotion")), typeReference);
    promotions.forEach(p -> convertDetailData(p, promotionData));
    return promotions;
  }

  @SneakyThrows
  private void convertDetailData(Promotion promotion, JsonNode promotionData) {

    promotion.setDetail(
        objectMapper.readValue(String.valueOf(promotionData.get("promotion").get("detail")),
            promotion.getType().getClz()));
  }
}
