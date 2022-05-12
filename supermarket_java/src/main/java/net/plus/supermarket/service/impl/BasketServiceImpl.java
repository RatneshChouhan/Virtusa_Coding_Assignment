package net.plus.supermarket.service.impl;

import net.plus.supermarket.exception.ProductNotFoundException;
import net.plus.supermarket.model.Product;
import net.plus.supermarket.service.BasketService;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketServiceImpl implements BasketService {

    private String[] items;
    private List<Product> productList;

    public BasketServiceImpl(String[] items, List<Product> productList) {
        this.items = items;
        this.productList = productList;
    }

    @Override
    public Map<Product, Integer> createNewBasket() {
        Map<Product, Integer> basket = new HashMap<>();

        validateInputIsNotEmpty();
        validateItemName();

        System.out.println("\n*************** YOUR BASKET ***************");

        for (Product product : productList) {
            validateProductPrice(product);

            if (StringUtils.isNotEmpty(product.getName())) {
                Integer qty = Math.toIntExact(Arrays.stream(items)
                        .filter(i -> i.equalsIgnoreCase(product.getName()))
                        .count());

                if (qty > 0) {
                    basket.put(product, qty);
                    System.out.println(qty + " X " + product.getName() + "[ Original Purchase Value ] : " + BigDecimal.valueOf(qty).multiply(product.getUnitPrice()));
                }
            }
        }
        System.out.println("********************************************");
        return basket;
    }


    public void validateInputIsNotEmpty() {
        if (items.length <= 0) {
            StringBuilder availableProducts = new StringBuilder();
            for (Product product : productList) {
                availableProducts.append(product.getName()).append(" ");
            }
            throw new IllegalArgumentException("[ Items input cannot be empty. Available products: " + availableProducts + "]");
        }
    }

    public void validateItemName() {
        for (String item : items) {
            productList.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(item))
                    .findFirst()
                    .orElseThrow(
                            () -> new ProductNotFoundException("[ Product not exists in the current stock: " + item + " ]"));
        }
    }

    private void validateProductPrice(Product product) {
        BigDecimal productPrice = product.getUnitPrice();
        if (productPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("[ Product (" + product.getName() + ") price must be a positive value ]");
        }
    }

}
