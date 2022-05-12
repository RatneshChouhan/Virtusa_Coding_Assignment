package net.plus.supermarket.service;

import net.plus.supermarket.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BillCalculationStrategy {

  default BigDecimal calculateSubTotalForProduct(List<Product> productList, Product product, int quantity) {
      return BigDecimal.valueOf(quantity).multiply(product.getUnitPrice());
  }



}
