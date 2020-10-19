/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText myName = (EditText)findViewById(R.id.editTextName);
        String giveName = myName.getText().toString();
        CheckBox whippedCreamCheckBox= (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        Boolean hasWhippedCreamObject = whippedCreamCheckBox.isChecked();
        CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.Choclate_checkBox);
        Boolean hasChoclateCheckBack = choclateCheckBox.isChecked();
        int price = calculatePrice(hasChoclateCheckBack,hasWhippedCreamObject);
        String Price_message =createOrderSummary(price,hasWhippedCreamObject,hasChoclateCheckBack,giveName) ;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for  "+ giveName);
        intent.putExtra(Intent.EXTRA_TEXT, Price_message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }



    public void increment(View view) {

        if(quantity == 100)
        {

            Toast.makeText(this,"cannot order more than 100 cup of coffey",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+ 1;
        displayQuantity(quantity);

    }
    private int calculatePrice(boolean choclate, boolean cream) {
        int basePrice = 5;
        if (choclate == true) {
           basePrice = basePrice+2;
        }  if (cream == true){
            basePrice = basePrice+1;
        }

            return quantity*basePrice;

    }
    public void decrement(View view) {
        if(quantity == 1)
        {
            Toast.makeText(this,"Quantity cannot be less than 1",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity-1;
        displayQuantity(quantity);
    }
    private String createOrderSummary(int price,Boolean hasOrNot,Boolean Choclate,String name) {
        String PriceMessage = "Name :"+name;
        PriceMessage += "\nAdding whipped cream -> "+hasOrNot;
        PriceMessage += "\nAdding Choclate -> "+Choclate;
        PriceMessage += "\nQuantity:" + quantity;
        PriceMessage += "\nTotal :$" + price;
        PriceMessage += "\nThank you!";
        return PriceMessage;
    }






        /***
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */


}