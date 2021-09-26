package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;

public class ProductProfileActivity extends AppCompatActivity {

    TextView name, price, quantity;
    DatabaseReference databaseCarts;
    Button addToCart;
    String userID = "temp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_profile);

        databaseCarts = FirebaseDatabase.getInstance().getReference("cart");
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        quantity = (TextView) findViewById(R.id.quantity);
        addToCart = (Button) findViewById(R.id.addToCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }

    /*
     * This method is saving a new cart item to the
     * Firebase Realtime Database
     * */
    private void addToCart() {
        //getting the values to save
        String itemName = name.getText().toString().trim();
        String itemPrice = price.getText().toString().trim();
        String itemQuantity = quantity.getText().toString().trim();

        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for cart item
        String id = databaseCarts.push().getKey();

        //creating a cart Object
        ShoppingCart shoppingCart = new ShoppingCart(id, userID, itemName, itemPrice, itemQuantity);

        //Saving the cart item
        databaseCarts.child(id).setValue(shoppingCart);

        //displaying a success toast
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_LONG).show();
    }
}