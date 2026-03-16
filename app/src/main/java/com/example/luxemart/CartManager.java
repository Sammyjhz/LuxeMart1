package com.example.luxemart;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    /**
     * Add a product to the cart. If it exists, increase quantity.
     * @param product The product to add.
     */
    public void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    /**
     * Remove a cart item.
     * @param item The item to remove.
     */
    public void removeFromCart(CartItem item) {
        cartItems.remove(item);
    }

    /**
     * Update quantity of a cart item.
     * @param item The item to update.
     * @param newQuantity New quantity.
     */
    public void updateQuantity(CartItem item, int newQuantity) {
        if (newQuantity <= 0) {
            removeFromCart(item);
        } else {
            item.setQuantity(newQuantity);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Get the total number of items in the cart.
     * @return Total items count.
     */
    public int getCartItemCount() {
        int count = 0;
        for (CartItem item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    /**
     * Calculate the grand total of the cart.
     * @return Grand total price.
     */
    public double getGrandTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    /**
     * Clear the cart after checkout.
     */
    public void clearCart() {
        cartItems.clear();
    }
}
