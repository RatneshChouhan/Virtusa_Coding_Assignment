package net.plus.supermarket.service.impl;

import net.plus.supermarket.model.Product;
import net.plus.supermarket.service.InventoryService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    public List<Product> createStock() {

        List<Product> productList = Arrays.asList(
                new Product(1, "A", BigDecimal.valueOf(0.50)),
                new Product(2, "B", BigDecimal.valueOf(0.30)),
                new Product(3, "C", BigDecimal.valueOf(0.20)),
                new Product(4, "D", BigDecimal.valueOf(0.15)),
                new Product(5, "E", BigDecimal.valueOf(0.05))
        );

        return productList;
    }
}
