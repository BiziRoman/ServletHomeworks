package org.example.model;

import java.util.*;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    /*public void addProduct(Product product) {
        items.add(product);
    }*/

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Product product, int quantity){
        for (CartItem item : items){
            if (item.getProduct().getId() == product.getId()){
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(Product product){
        items.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    public double getTotalPrice(){
        double total = 0.0;
        for (CartItem item : items){
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}

