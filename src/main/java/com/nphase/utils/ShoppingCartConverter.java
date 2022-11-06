package com.nphase.utils;

import com.nphase.entity.Category;
import com.nphase.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartConverter {

    public static Map<Category, List<Product>> createMap(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(product -> product, Collectors.toList())
                ));
    }
}
