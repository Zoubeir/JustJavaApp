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

    int number=0;

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

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int qtyOrdered = Integer.parseInt(quantityTextView.getText().toString());

        displayPrice(qtyOrdered * coffeePrice);//one cup of coffee is 1.8 Pounds
    }

    /**
     * displays the given price on the screen
     * @param number
     */
    private void displayPrice(double number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayQty(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * increment button click handler
     * @param view is the button which has been clicked
     */
    public void increment(View view) {
        int qty = ++number;
        displayQty(qty);
    }

    /**
     * decrement button click handler
     * @param view is the button which has been clicked
     */
    public void decrement(View view){
         --number;

         //validating the value to be always >=0
        if (number<=0){
            number = 0;
        }

        displayQty(number);
    }
}