package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;

public class ProductProfileActivity extends AppCompatActivity {

    TextView name, price, quantity, description;
    DatabaseReference databaseCarts;
    Button addToCart;
    String userID;
    String itemName, itemPrice, image, itemDescription;
    ImageView imageView;
    ImageButton plus, minus;
    int totalQuantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_profile);

        if (LoginActivity.loggedUser == null){
            userID = "temp";
        }
        else {
            userID = LoginActivity.loggedUser;
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            itemName = extras.getString("EXTRA_NAME");
            itemPrice = extras.getString("EXTRA_PRICE");
            image = extras.getString("EXTRA_IMAGE");
            itemDescription = extras.getString("EXTRA_DESCRIPTION");
        }

        databaseCarts = FirebaseDatabase.getInstance().getReference("cart");
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        quantity = (TextView) findViewById(R.id.quantity);
        description = (TextView) findViewById(R.id.description);
        addToCart = (Button) findViewById(R.id.addToCart);
        imageView = (ImageView) findViewById(R.id.imageView);
        plus = (ImageButton) findViewById(R.id.plus);
        minus = (ImageButton) findViewById(R.id.minus);

        addToCart.setText(LanguageSetter.getresources().getString(R.string.addtocart_productp));

        name.setText(itemName);
        price.setText("Rs " + itemPrice + ".00");
        description.setText(itemDescription);
        Picasso.get().load(image).into(imageView);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuantity = totalQuantity + 1;
                if(totalQuantity <= 0) {
                    quantity.setText("1");
                    totalQuantity = 1;
                }
                else{
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuantity = totalQuantity - 1;
                if(totalQuantity <= 0) {
                    quantity.setText("1");
                    totalQuantity = 1;
                }
                else{
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }

    /*
     * This method is saving a new cart item to the
     * Firebase Realtime Database
     * */
    private void addToCart() {
        //getting the values to save
        String itemQuantity = quantity.getText().toString().trim();

        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for cart item
        String id = databaseCarts.push().getKey();
        int totalPrice = Integer.parseInt(itemPrice) * Integer.parseInt(itemQuantity);
        //creating a cart Object
        ShoppingCart shoppingCart = new ShoppingCart(id, userID, image, itemName, itemPrice, String.valueOf(totalQuantity), String.valueOf(totalPrice));

        //Saving the cart item
        databaseCarts.child(id).setValue(shoppingCart);

        //displaying a success toast
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ProductProfileActivity.this, ScrollActivity.class);
        startActivity(intent);
    }


}