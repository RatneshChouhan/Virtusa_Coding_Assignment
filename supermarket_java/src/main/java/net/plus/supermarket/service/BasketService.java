package net.plus.supermarket.service;

import net.plus.supermarket.model.Product;

import java.util.Map;

public interface BasketService {

    public Map<Product, Integer> createNewBasket();
}
