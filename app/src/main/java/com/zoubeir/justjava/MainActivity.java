package com.zoubeir.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        double price = calculatePrice(); //call to method calculate total price of order
        displayMessage(createOrderSummary(price)); //create and display order summary
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice(){
        double coffeePrice = 1.8;//unit price for a coffee

        CheckBox whippedCream = findViewById(R.id.chkWhippedCream);

        if(whippedCream.isChecked()){
            return (quantity * coffeePrice)+2.5;
        }

        return quantity * coffeePrice; //calculating coffee price
    }

    /**
     * Create an order summary
     * @param totalPrice of order
     * @return order summary
     */
    private String createOrderSummary(double totalPrice){
        return "Name: Zoubeir \n"+ "Quantity: "+quantity+"\nTotal: $"+totalPrice+"\nThank you!";
    }

    /**
     * displays the order summary
     * @param msg ordet summary to display
     */
    private void displayMessage(String msg){
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(msg);
    }

    /**
     * display the quantity chosen by the user
     * @param qty to display
     */
    private void displayQuantity(int qty){
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + qty);
    }

    /**
     * increment button click handler
     * @param view is the button which has been clicked
     */
    public void increment(View view) {
        int qty = ++quantity;
        displayQuantity(qty);
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

        displayQuantity(quantity);
    }
}