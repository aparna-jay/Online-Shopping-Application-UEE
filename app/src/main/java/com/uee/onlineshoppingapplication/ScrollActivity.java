package com.uee.onlineshoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.currencySetter;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    private FirebaseUser fUser;
    Spinner languageSpinner ,currancySpinner;
    String Text;
    TextView txtLogo;
    ListView productListView;
    String user ;
    String userID;
    DatabaseReference dbref;
    List<ScrollHome> products;
    String text;
    //search
    private AutoCompleteTextView txtSearch;

//    int valueMult ;
float number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        dbref = FirebaseDatabase.getInstance().getReference("products");
        productListView = (ListView) findViewById(R.id.prListView);
        products = new ArrayList<>();
        txtSearch = (AutoCompleteTextView) findViewById(R.id.search);

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        dbref.addListenerForSingleValueEvent(event);

//        if (LoginActivity.loggedUser == null){
//            user = "null";
//        }
//        else {
//            user = LoginActivity.loggedUser;
//        }
//        Log.e("Logged User", user);
//
//        fUser = FirebaseAuth.getInstance().getCurrentUser();
//        dbref = FirebaseDatabase.getInstance().getReference("users");

//        if (LoginActivity.loggedUser == null){
//            userID = "t";
//        }
//        else {
//            userID = LoginActivity.loggedUser;
//        }
//        Log.e("Logged User", user);
//


        txtLogo=(TextView)findViewById(R.id.txtLogo);
        languageSpinner = (Spinner) findViewById(R.id.languageSpinner);



//        currancy
        // Spinner Drop down elements
        List<String> curancy = new ArrayList<String>();
        curancy.add("Rs");
        curancy.add("USD");

        currancySpinner = (Spinner) findViewById(R.id.languageSpinner2);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getApplicationContext() , R.layout.currancy_spinnner, curancy);
        adapter.setDropDownViewResource(R.layout.currancy_layout_drop_down);
        currancySpinner.setAdapter(adapter);
        currancySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                currencySetter.setC_type(text);
                currencySetter.changeCurrency(text);
                number1 = currencySetter.getValue();

                ScrollAdapter scrollAdapter = new ScrollAdapter(ScrollActivity.this, products,number1,text);
                productListView.setAdapter(scrollAdapter);
//                finish();
//                startActivity(getIntent());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

  // Spinner click listene
        // Spinner Drop down elements
        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("සිංහල");
        languages.add("தமிழ்");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(getApplicationContext(), R.layout.language_spinner_item, languages);
        // Drop down layout style - list view with radio button
        dataAdapterType.setDropDownViewResource(R.layout.language_spinner_drop_down_item);
        // attaching data adapter to spinner
        languageSpinner.setAdapter(dataAdapterType);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                Text = parent.getItemAtPosition(position).toString();
                LanguageSetter.setLanguage(Text);
                LanguageSetter.changeLanguage(Text, ScrollActivity.this);
                txtLogo.setText(LanguageSetter.getresources().getString(R.string.app_name));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //search
    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names= new ArrayList<>();
        if(snapshot.exists()){
            for (DataSnapshot ds:snapshot.getChildren()){
                String name = ds.child("name").getValue(String.class);
                names.add(name);
            }
            //set search details
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, names);
            txtSearch.setAdapter(adapter);

            //get search details
            txtSearch.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = txtSearch.getText().toString();
                    searchUser(name);
                }

            });
        }else {
            Log.d("products", "No data found!");
        }
    }

    //display search details
    private void searchUser(String name) {
        Query query = dbref.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    ArrayList<String> listProducts = new ArrayList<>();

                    for (DataSnapshot ds:snapshot.getChildren()){
                        ScrollHome prdct = new ScrollHome(ds.child("id").getValue(String.class),ds.child("name").getValue(String.class),
                                ds.child("price").getValue(String.class),ds.child("image").getValue(String.class),
                                ds.child("description").getValue(String.class), ds.child("userId").getValue(String.class));
                        listProducts.add(prdct.getName() + "\n"+ prdct.getPrice()+"\n\n");
                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,listProducts);
                    productListView.setAdapter(adapter);

                }else {
                    Log.d("products", "No data found!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous cart item list
                products.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting product item
                    ScrollHome scrollHome = postSnapshot.getValue(ScrollHome.class);
                    //adding product item to the favourite list
//                    if(scrollHome.getUserId().equals(userID)) {
                        Log.e("Product list", " " + scrollHome.getName());
                        products.add(scrollHome);


                }

                number1 = currencySetter.getValue();
                ScrollAdapter scrollAdapter = new ScrollAdapter(ScrollActivity.this, products , number1,text);
                productListView.setAdapter(scrollAdapter);
//                Toast.makeText(getApplicationContext(), "Added to the Favourites!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

