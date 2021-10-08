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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_n_e_w);

        reference = FirebaseDatabase.getInstance().getReference("favourites");
        favListView = (ListView) findViewById(R.id.fListView);
        fav = new ArrayList<>();

//        LayoutInflater inflater = getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.favourite_row, null, true);
//
//        Intent i = getIntent();
//        String image = i.getStringExtra("");
//        String price = i.getStringExtra("");

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