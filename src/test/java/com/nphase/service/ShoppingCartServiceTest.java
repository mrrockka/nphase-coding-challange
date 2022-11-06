package com.nphase.service;


import com.nphase.config.Configuration;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.nphase.entity.Category.DRINKS;
import static com.nphase.entity.Category.FOOD;


@SpringBootTest
public class ShoppingCartServiceTest {
    @Autowired
    private ShoppingCartService service;

    @Autowired
    private Configuration configuration;

    @Test
    public void calculatesPrice() {
        ShoppingCart cart = new ShoppingCart(createList());

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(BigDecimal.valueOf(31.84).stripTrailingZeros(), result.stripTrailingZeros());
    }

    private List<Product> createList() {
        final var map = configuration.getProducts();

        final var products = Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), DRINKS),
                new Product("Coffee", BigDecimal.valueOf(3.5), DRINKS),
                new Product("Cheese", BigDecimal.valueOf(8), FOOD)
        );

        products.forEach(product -> product.setQuantity(map.get(product.getName())));
        return products;

    }


}
