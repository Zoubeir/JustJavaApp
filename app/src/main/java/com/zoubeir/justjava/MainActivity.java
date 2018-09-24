package com.zoubeir.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        double coffeePrice = 1.8;

        double price = quantity * coffeePrice;

        /*TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int qtyOrdered = Integer.parseInt(quantityTextView.getText().toString());*/

        displayMessage(price);
    }

    /**
     * displays the given price on the screen
     * @param number
     */
    private void displayMessage(double number){
        TextView priceTextView = findViewById(R.id.price_text_view);
        String msg = "Total: Rs "+number+"\nThank You!";
        priceTextView.setText(msg);
    }

    private void displayQty(int qty){
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + qty);
    }

    /**
     * increment button click handler
     * @param view is the button which has been clicked
     */
    public void increment(View view) {
        int qty = ++quantity;
        displayQty(qty);
    }

    /**
     * decrement button click handler
     * @param view is the button which has been clicked
     */
    public void decrement(View view){
         --quantity;

         //validating the value to be always >=0
        if (quantity<=0){
            quantity = 0;
        }

        displayQty(quantity);
    }
}