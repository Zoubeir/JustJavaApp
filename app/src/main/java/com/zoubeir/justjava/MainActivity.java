package com.zoubeir.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

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
        //displayMessage(); //create and display order summary

        String msgToSend = createOrderSummary(price, whippedCream.isChecked(), chocolate.isChecked());


        String addresses[] = new String[]{"zoubx7@gmail.com"};//array to hold send to email addresses

        Intent intent = new Intent(Intent.ACTION_SENDTO); //e-mail without attachments
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee is ready!"); //email subject
        intent.putExtra(Intent.EXTRA_TEXT, msgToSend);//email content - message to send

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }






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

        ++quantity;

        //validating quantity to be up to 100
        if (quantity > 100) {
            //use a toast to display an error msg
            Toast toast = Toast.makeText(getApplicationContext(), "Hey Stop! You can't order more than 100 coffees!", Toast.LENGTH_SHORT);
            toast.show();

            quantity = 100;
        }

        displayQuantity(quantity);






    }

    /**
     * decrement button click handler
     * @param view is the button which has been clicked
     */
    public void decrement(View view){
         --quantity;

        //validating the value to be always >0
        if (quantity < 1) {

            //use a toast to display an error msg
            Toast toast = Toast.makeText(getApplicationContext(), "You can't order less than 1 coffee!", Toast.LENGTH_SHORT);
            toast.show();

            quantity = 1;
        }

        displayQuantity(quantity);
    }
}