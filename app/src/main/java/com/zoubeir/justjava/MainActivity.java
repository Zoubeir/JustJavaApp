package com.zoubeir.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

        //get a ref to whippedCream checkbox
        CheckBox whippedCream = findViewById(R.id.chkWhippedCream);

        //get a ref to chocolate checkbox
        CheckBox chocolate = findViewById(R.id.chkChocolate);

        double price = calculatePrice(); //call to method calculate total price of order
        displayMessage(createOrderSummary(price, whippedCream.isChecked(), chocolate.isChecked())); //create and display order summary

    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice(){
        double coffeeBasePrice = 5;//unit price for a coffee without any toppings

        double totalPrice = 0.0;//hold total price of coffee order

        //get a ref to whippedCream checkbox
        CheckBox whippedCream = findViewById(R.id.chkWhippedCream);

        //get a ref to chocolate checkbox
        CheckBox chocolate = findViewById(R.id.chkChocolate);

        //verify is user wants whipped cream and add $1 to coffeeBasePrice
        if(whippedCream.isChecked()){
            coffeeBasePrice += 1;
        }

        //verify is user wants chocolate and add $2 to coffeeBasePrice
        if (chocolate.isChecked()) {
            coffeeBasePrice += 2;
        }

        //calculate coffee base price per cup
        totalPrice = coffeeBasePrice * quantity;

        return totalPrice; //calculating total price of order
    }

    /**
     * Create an order summary
     * @param totalPrice of order
     * @param hasWhippedCream to know if user wants whipped cream
     * @return order summary
     */
    private String createOrderSummary(double totalPrice, boolean hasWhippedCream, boolean hasChocolate) {


        //get ref to EditText view
        EditText txtName = findViewById(R.id.txtName);

        return "Name: " + txtName.getText() + "\nAdd Whipped Cream? " + hasWhippedCream + "\nAdd Chocolate? " + hasChocolate + "\nQuantity: " + quantity + "\nTotal: $" + totalPrice + "\nThank you!";
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