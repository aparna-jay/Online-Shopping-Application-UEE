package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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


    DatabaseReference databaseCarts;
    List<ShoppingCart> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        databaseCarts = FirebaseDatabase.getInstance().getReference("cart");
        carts = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseCarts.addValueEventListener(new ValueEventListener() {
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


//                //creating adapter
//                UserList artistAdapter = new ArtistList(MainActivity.this, artists);
//                //attaching adapter to the listview
//                listViewArtists.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}