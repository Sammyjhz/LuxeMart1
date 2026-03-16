package com.example.luxemart;

public class Order {

    private String orderId;
    private double totalPrice;
    private String status;
    private String date;

    public Order(String orderId, double totalPrice, String status, String date) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}