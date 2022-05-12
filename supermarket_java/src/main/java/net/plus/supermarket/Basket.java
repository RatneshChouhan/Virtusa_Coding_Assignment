package net.plus.supermarket;

import net.plus.supermarket.model.Product;
import net.plus.supermarket.service.CheckoutService;
import net.plus.supermarket.service.InventoryService;
import net.plus.supermarket.service.impl.BasketServiceImpl;
import net.plus.supermarket.service.impl.CheckoutServiceImpl;
import net.plus.supermarket.service.impl.InventoryServiceImpl;

import java.util.List;
import java.util.Map;

/*
*    Main class which first load inventory data,
*    takes shopping items details as command line arguments,
*    Then perform checkout and bill calculation
*
*    @param items list of items to be buy
*     @return calculation output with offers messages
*/

public class Basket {
    public static void main(String[] items) {
        Map<String, Object> finalBills = performCheckout(items);
        System.out.println(finalBills);
    }

    static Map<String, Object> performCheckout(String[] items) {
        InventoryService inventoryService = new InventoryServiceImpl();
        List<Product> productList = inventoryService.createStock();
        Map<Product, Integer> basket = new BasketServiceImpl(items, productList).createNewBasket();
        CheckoutService checkoutService = new CheckoutServiceImpl(basket, productList);
        return checkoutService.checkout();
    }

}
