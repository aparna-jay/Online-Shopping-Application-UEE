package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;

public class ProductProfileActivity extends AppCompatActivity {

    TextView name, price, quantity, description;
    DatabaseReference databaseCarts;
    Button addToCart;
    String userID = "temp";
    String itemName, itemPrice, itemQuantity, image, itemDescription;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_profile);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            itemName = extras.getString("EXTRA_NAME");
            itemPrice = extras.getString("EXTRA_PRICE");
            itemQuantity = extras.getString("EXTRA_QUANTITY");
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

        name.setText(itemName);
        price.setText(itemPrice);
        description.setText(itemDescription);
        Picasso.get().load(image).into(imageView);

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
        int totalPrice = Integer.parseInt(itemPrice) * Integer.parseInt(itemQuantity);

        //creating a cart Object
        ShoppingCart shoppingCart = new ShoppingCart(id, userID, "abcdefg", itemName, itemPrice, itemQuantity, String.valueOf(totalPrice));

        //Saving the cart item
        databaseCarts.child(id).setValue(shoppingCart);

        //displaying a success toast
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_LONG).show();
    }
}