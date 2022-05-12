package net.plus.supermarket.service.impl;

import net.plus.supermarket.model.Product;
import net.plus.supermarket.service.CheckoutService;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CheckoutServiceImpl implements CheckoutService {

    private final Map<Product, Integer> basket;
    private final List<Product> productList;
    //   private final BillCalculationStrategy billCalculationStrategy;

    public CheckoutServiceImpl(Map<Product, Integer> basket, List<Product> productList) {
        this.basket = basket;
        this.productList = productList;
    }

    @Override
    public Map<String, Object> checkout() {
        BigDecimal subtotal;
        BigDecimal totalBill = new BigDecimal("0.00");
        System.out.println("-------------------------------------------------");
        System.out.println("********* AFTER APPLYING SPECIAL PRICE **********");

        Map<Product, Integer> shoppingCart = basket.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

        //    subtotal = calculateSubTotalForProduct(productList, product, quantity);
            subtotal = calculateSubTotalForProduct(basket, product, quantity);
            totalBill = totalBill.add(subtotal);
        }

        Map<String, Object> checkOutResult = new HashMap<>();
        checkOutResult.put("Total Bill", totalBill);
        System.out.println("********************************************");
        return checkOutResult;
    }

//    private BigDecimal calculateSubTotalForProduct(List<Product> productList, Product product, int quantity) {
    private BigDecimal calculateSubTotalForProduct(Map<Product, Integer> basket, Product product, int quantity) {
        BigDecimal bill = BigDecimal.valueOf(0.0);
        List<Pair<Integer, BigDecimal>> offerList = new ArrayList<>();

        switch (product.getName()) {
            case "A":
                offerList.add(Pair.of(3, BigDecimal.valueOf(1.30)));
                bill = getProductSubTotal(product, quantity, offerList);
                break;
            case "B":
                offerList.add(Pair.of(2, BigDecimal.valueOf(0.45)));
                bill = getProductSubTotal(product, quantity, offerList);
                break;
            case "C":
                offerList.add(Pair.of(3, BigDecimal.valueOf(0.50)));
                offerList.add(Pair.of(2, BigDecimal.valueOf(0.38)));
                bill = getProductSubTotal(product, quantity, offerList);
                break;
            case "D":
                Optional<Product> itemA = productList.stream().filter(p -> "A".equalsIgnoreCase(p.getName())).findAny();
                if (itemA.isPresent() && basket.containsKey(itemA.get())) {
                    bill = bill.add(BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(0.05)));
                } else {
                    bill = bill.add(BigDecimal.valueOf(quantity).multiply(product.getUnitPrice()));
                }
                break;
            case "E":
                bill = bill.add(BigDecimal.valueOf(quantity).multiply(product.getUnitPrice()));
                break;
        }
        System.out.println("Total Purchase Value for Product " + product.getName() + " is " + bill);
        return bill;
    }

    private BigDecimal getProductSubTotal(Product product, int productQty, List<Pair<Integer, BigDecimal>> offerList) {
        BigDecimal bill = BigDecimal.valueOf(0.0);
        offerList = offerList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

        for (Pair<Integer, BigDecimal> p : offerList) {
            int offerQty = p.getLeft();
            BigDecimal offerPrice = p.getRight();

            if (productQty >= offerQty) {
                int discQty = productQty / offerQty;
                productQty = productQty % offerQty;
                bill = bill.add(BigDecimal.valueOf(discQty).multiply(offerPrice));
            }
        }

        return bill.add(BigDecimal.valueOf(productQty).multiply(product.getUnitPrice()));
    }
}
