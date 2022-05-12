package net.plus.supermarket.model;

import java.math.BigDecimal;

public class Product {
    private final long id;
    private final String name;
    private final BigDecimal unitPrice;

    public Product(long id, String name, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
