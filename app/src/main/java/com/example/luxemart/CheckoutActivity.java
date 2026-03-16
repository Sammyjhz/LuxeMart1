package com.example.luxemart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    EditText editAddress, editPhone;
    TextView textTotalPrice;
    Button buttonPlaceOrder;

    double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        textTotalPrice = findViewById(R.id.textTotalPrice);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);

        // Get total price from intent (CartActivity should send this)
        totalPrice = getIntent().getDoubleExtra("TOTAL_PRICE", 0);

        textTotalPrice.setText("Total: $" + totalPrice);

        buttonPlaceOrder.setOnClickListener(v -> {

            String address = editAddress.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();

            if (address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Move to PaymentActivity
            Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);
        });
    }
}