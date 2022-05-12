package net.plus.supermarket;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    public static final String TOTAL_BILL = "Total Bill";

    @Test
    public void shouldCorrectCheckoutWithoutAnyOffers() {
        String[] items = {"B", "C", "D", "E"};
        Map<String,Object> finalBill = Basket.performCheckout(items);
        assertEquals("0.70", finalBill.get(TOTAL_BILL).toString());
    }

    @Test
    public void shouldCorrectCheckoutWithOfferOfProduct_DAndProduct_A() {
        String[] items = {"A", "B", "C", "D", "E"};
        Map<String,Object> finalBill = Basket.performCheckout(items);
        assertEquals("1.10", finalBill.get(TOTAL_BILL).toString());
    }

    @Test
    public void shouldCorrectCheckoutWithAllOffers() {
        String[] items = {"A","A","A","B","B","C","C","C","C","C","D","D","E","E"};
        Map<String,Object> finalBill = Basket.performCheckout(items);
        assertEquals("2.83", finalBill.get(TOTAL_BILL).toString());
    }
}