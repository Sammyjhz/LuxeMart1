package com.example.luxemart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    RadioGroup radioGroupPayment;
    RadioButton radioMpesa, radioCash;
    Button buttonPay;

    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        radioMpesa = findViewById(R.id.radioMpesa);
        radioCash = findViewById(R.id.radioCash);
        buttonPay = findViewById(R.id.buttonPay);

        totalPrice = getIntent().getDoubleExtra("TOTAL_PRICE", 0);

        buttonPay.setOnClickListener(v -> {

            int selectedId = radioGroupPayment.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            String paymentMethod = "";

            if (selectedId == R.id.radioMpesa) {
                paymentMethod = "M-Pesa";
            } else if (selectedId == R.id.radioCash) {
                paymentMethod = "Cash on Delivery";
            }

            Toast.makeText(this,
                    "Payment successful via " + paymentMethod +
                            "\nTotal Paid: $" + totalPrice,
                    Toast.LENGTH_LONG).show();

            // After payment you can move to OrdersActivity
            finish();
        });
    }
}