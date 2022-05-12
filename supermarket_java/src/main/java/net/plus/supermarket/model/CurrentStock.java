package net.plus.supermarket.model;

import java.util.List;

public class CurrentStock {

    private final List<Product> skuList;

    public CurrentStock(List<Product> skuList) {
        this.skuList = skuList;
    }

    public List<Product> getSkuList() {
        return skuList;
    }
}
