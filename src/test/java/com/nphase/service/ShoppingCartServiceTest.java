package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.nphase.entity.Category.DRINKS;
import static com.nphase.entity.Category.FOOD;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2, DRINKS),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, DRINKS),
                new Product("Cheese", BigDecimal.valueOf(8), 2, FOOD)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(BigDecimal.valueOf(31.84).stripTrailingZeros(), result.stripTrailingZeros());
    }

}
