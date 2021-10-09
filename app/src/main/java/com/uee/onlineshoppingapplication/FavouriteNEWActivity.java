package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.Favourites;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;
import com.uee.onlineshoppingapplication.OnlineDB.currencySetter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteNEWActivity extends AppCompatActivity {

    private FirebaseUser fUser;

    ListView favListView;
    String user ;
    DatabaseReference reference;
    List<Favourites> fav;
    TextView favourite_favourite,items_favourite,product_favourite,price_favourite,remove_favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_n_e_w);

        reference = FirebaseDatabase.getInstance().getReference("favourites");
        favListView = (ListView) findViewById(R.id.fListView);
        fav = new ArrayList<>();
        favourite_favourite = (TextView) findViewById(R.id.favourite_favourite);
        items_favourite = (TextView) findViewById(R.id.items_favourite);
        product_favourite = (TextView) findViewById(R.id.product_favourite);
        price_favourite = (TextView) findViewById(R.id.price_favourite);
        remove_favourite = (TextView) findViewById(R.id.remove_favourite);

//        LayoutInflater inflater = getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.favourite_row, null, true);
//
//        Intent i = getIntent();
//        String image = i.getStringExtra("");
//        String price = i.getStringExtra("");

        favourite_favourite.setText(LanguageSetter.getresources().getString(R.string.favourite_favourite));
        items_favourite.setText(LanguageSetter.getresources().getString(R.string.items_favourite));
        product_favourite.setText(LanguageSetter.getresources().getString(R.string.product_favourite));
        price_favourite.setText(LanguageSetter.getresources().getString(R.string.price_favourite));
        remove_favourite.setText(LanguageSetter.getresources().getString(R.string.remove_favourite));

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous cart item list
                fav.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting cart item
                    Favourites favourites = postSnapshot.getValue(Favourites.class);
                    //adding cart item to the list
                    Log.e("Product list", " " + favourites.getPrice());
                    fav.add(favourites);
                }

                FavouriteAdapter favouriteAdapter = new FavouriteAdapter(FavouriteNEWActivity.this, fav);
                favListView.setAdapter(favouriteAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}