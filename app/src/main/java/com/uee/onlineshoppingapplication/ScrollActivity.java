package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.currencySetter;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

//    ImageView Image1;
//    ImageView Image2;
//    ImageView Image3;
//    ImageView Image4;
//    ImageView Image5;
//    ImageView Image6;
    Spinner languageSpinner ,currancySpinner;
    String Text;
    TextView txtLogo;
    ListView productListView;
    String user;
    DatabaseReference dbref;
    List<ScrollHome> products;
    String text;
//    int valueMult ;
float number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        dbref = FirebaseDatabase.getInstance().getReference("products");
        productListView = (ListView) findViewById(R.id.prListView);
        products = new ArrayList<>();

//        Image1=(ImageView)findViewById(R.id.img1);
//        Image2=(ImageView)findViewById(R.id.img2);
//        Image3=(ImageView)findViewById(R.id.img3);
//        Image4=(ImageView)findViewById(R.id.img4);
//        Image5=(ImageView)findViewById(R.id.img5);
//        Image6=(ImageView)findViewById(R.id.img6);d
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

//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/frock.png?alt=media&token=aed6d16f-9739-40a8-ba0b-51e6a3576b35").into(Image1);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/image-retrieve-33687.appspot.com/o/images%2Fcam1.jpeg?alt=media&token=ed6db825-e481-49d1-a7ff-8d9047ec59c2").into(Image2);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch.jpeg?alt=media&token=d0ae14f1-d1ec-40e4-a86a-22d90e8d9bce").into(Image3);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/tshirt.png?alt=media&token=5c105284-bb1d-4df6-b295-e3652d869711").into(Image4);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/shoes2.png?alt=media&token=b2cf57f7-01dd-4940-bff7-c2f5fa3e9a82").into(Image5);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch2.jpeg?alt=media&token=9a0753ef-846b-4b43-b76b-9a9625012e3c").into(Image6);

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
                    //getting cart item
                    ScrollHome scrollHome = postSnapshot.getValue(ScrollHome.class);
                    //adding cart item to the list
                    Log.e("Product list", " " + scrollHome.getName());
                    products.add(scrollHome);
                }
                number1 = currencySetter.getValue();
                ScrollAdapter scrollAdapter = new ScrollAdapter(ScrollActivity.this, products , number1,text);
                productListView.setAdapter(scrollAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

