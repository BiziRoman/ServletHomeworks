package org.example.model;

public class OrderData {
    private String name;
    private String phone;
    private String address;
    private String delivery;
    private String payment;
    private Cart cart;
    private double totalPrice;

    public OrderData(String name, String phone, String address, String delivery, String payment, Cart cart) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.delivery = delivery;
        this.payment = payment;
        this.cart = cart;
        this.totalPrice = cart.getTotalPrice();
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getDelivery() { return delivery; }
    public String getPayment() { return payment; }
    public Cart getCart() { return cart; }
    public double getTotalPrice() { return totalPrice; }
}
