package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(this::doCalculations)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal doCalculations(Product product) {
        final var quantity = product.getQuantity();
        final var payment = product.getPricePerUnit().multiply(BigDecimal.valueOf(quantity));

        return applyDiscount(payment, quantity);
    }

    private BigDecimal applyDiscount(BigDecimal payment, Integer quantity){
        if(quantity > 3) {
            return payment.multiply(BigDecimal.valueOf(0.9));
        }

        return payment;
    }
}
