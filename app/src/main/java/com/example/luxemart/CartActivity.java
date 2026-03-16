package com.example.luxemart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private TextView tvGrandTotal;
    private Button btnCheckout;
    private CartAdapter adapter;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rv_cart);
        tvGrandTotal = findViewById(R.id.tv_grand_total);
        btnCheckout = findViewById(R.id.btn_checkout);
        cartManager = CartManager.getInstance();

        List<CartItem> cartItems = cartManager.getCartItems();
        adapter = new CartAdapter(this, cartItems, new CartAdapter.OnCartUpdateListener() {
            @Override
            public void onCartUpdated() {
                updateGrandTotal();
            }
        });

        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);

        updateGrandTotal();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartManager.getCartItems().isEmpty()) {
                    Toast.makeText(CartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                cartManager.clearCart();
                Toast.makeText(CartActivity.this, "Checked out successfully", Toast.LENGTH_SHORT).show();
                updateGrandTotal();
                adapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void updateGrandTotal() {
        double total = cartManager.getGrandTotal();
        tvGrandTotal.setText("Grand Total: $" + String.format("%.2f", total));
    }
}

