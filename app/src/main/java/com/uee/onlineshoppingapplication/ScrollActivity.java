package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.core.view.View;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    ImageView Image1;
    ImageView Image2;
    ImageView Image3;
    ImageView Image4;
    ImageView Image5;
    ImageView Image6;
    Spinner languageSpinner;
    String Text;
    TextView txtLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        Image1=(ImageView)findViewById(R.id.img1);
        Image2=(ImageView)findViewById(R.id.img2);
        Image3=(ImageView)findViewById(R.id.img3);
        Image4=(ImageView)findViewById(R.id.img4);
        Image5=(ImageView)findViewById(R.id.img5);
        Image6=(ImageView)findViewById(R.id.img6);
        txtLogo=(TextView)findViewById(R.id.txtLogo);
        languageSpinner = (Spinner) findViewById(R.id.languageSpinner);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/frock.png?alt=media&token=aed6d16f-9739-40a8-ba0b-51e6a3576b35").into(Image1);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/image-retrieve-33687.appspot.com/o/images%2Fcam1.jpeg?alt=media&token=ed6db825-e481-49d1-a7ff-8d9047ec59c2").into(Image2);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch.jpeg?alt=media&token=d0ae14f1-d1ec-40e4-a86a-22d90e8d9bce").into(Image3);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/tshirt.png?alt=media&token=5c105284-bb1d-4df6-b295-e3652d869711").into(Image4);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/shoes2.png?alt=media&token=b2cf57f7-01dd-4940-bff7-c2f5fa3e9a82").into(Image5);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch2.jpeg?alt=media&token=9a0753ef-846b-4b43-b76b-9a9625012e3c").into(Image6);

        // Spinner click listene
        // Spinner Drop down elements
        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("සිංහල");
        languages.add("தமிழ்");



        //checking comment
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
    }}

