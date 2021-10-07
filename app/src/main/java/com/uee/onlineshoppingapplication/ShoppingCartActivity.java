package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView cartListView;
    String user;
    DatabaseReference dbref;
    List<ShoppingCart> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        dbref = FirebaseDatabase.getInstance().getReference("cart");
        cartListView = (ListView) findViewById(R.id.cartListView);
        carts = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous cart item list
                carts.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting cart item
                    ShoppingCart shoppingCart = postSnapshot.getValue(ShoppingCart.class);
                    //adding cart item to the list
                    Log.e("Cart item list", " " + shoppingCart.getItemName());
                    carts.add(shoppingCart);
                }
                CartListAdapter cartListAdapter = new CartListAdapter(ShoppingCartActivity.this, carts);
                cartListView.setAdapter(cartListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}




//        if (LoginActivity.loggedUser == null){
//            user = "null";
//        }
//        else {
//            user = LoginActivity.loggedUser;
//        }
//        Log.e("Logged User", user);
