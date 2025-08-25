package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, Integer> quantities = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        products.put(product.getId(), product);
        quantities.put(product.getId(), quantity);
    }

    public void removeProduct(int productId) {
        products.remove(productId);
        quantities.remove(productId);
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Map<Integer, Integer> getQuantities() {
        return quantities;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            int quantity = quantities.get(entry.getKey());
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
