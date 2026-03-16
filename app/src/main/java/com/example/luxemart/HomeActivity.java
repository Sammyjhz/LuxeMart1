package com.example.luxemart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private Button btnCart;
    private TextView tvLogout;
    private ProductAdapter adapter;
    private List<Product> productList;
    private CartManager cartManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvProducts = findViewById(R.id.rv_products);
        btnCart = findViewById(R.id.btn_cart);
        tvLogout = findViewById(R.id.tv_logout);
        cartManager = CartManager.getInstance();
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Sample products (hardcoded for demo)
        productList = new ArrayList<>();
        productList.add(new Product(1, "Headphone", 10.99, R.drawable.headphones)); // Assume R.drawable.ic_product1 exists
        productList.add(new Product(2, "Laptop", 20.49, R.drawable.laptop));
        productList.add(new Product(3, "Sneakers", 5.99, R.drawable.sneakers));
        productList.add(new Product(4, "Watch", 2.99, R.drawable.watch));
        productList.add(new Product(5, "Backpack", 1.99, R.drawable.bag));
        productList.add(new Product(6, "Smart Tv", 21.99, R.drawable.tv));

        adapter = new ProductAdapter(this, productList, new ProductAdapter.OnAddToCartListener() {
            @Override
            public void onAddToCart(Product product) {
                cartManager.addToCart(product);
                updateCartButton();
            }
        });

        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(adapter);

        updateCartButton();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear session
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartButton();
    }

    private void updateCartButton() {
        int count = cartManager.getCartItemCount();
        btnCart.setText("Cart (" + count + ")");
    }
}


