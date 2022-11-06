package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import com.nphase.utils.ShoppingCartConverter;
import java.util.List;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return ShoppingCartConverter.createMap(shoppingCart.getProducts())
                .values()
                .stream()
                .map(this::doProductsCalculations)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal doProductsCalculations(List<Product> products) {
        final var categoryQuantity = quantity(products);
        final var payment = products.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return applyDiscount(payment, categoryQuantity);
    }

    private BigDecimal applyDiscount(BigDecimal payment, Integer quantity) {
        if (quantity > 3) {
            return payment.multiply(BigDecimal.valueOf(0.9));
        }

        return payment;
    }

    private Integer quantity(List<Product> products) {
        return products.stream()
                .map(Product::getQuantity)
                .reduce(Integer::sum)
                .orElse(0);
    }
}