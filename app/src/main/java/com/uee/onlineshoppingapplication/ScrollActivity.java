package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class ScrollActivity extends AppCompatActivity {

    ImageView Image1;
    ImageView Image2;
    ImageView Image3;
    ImageView Image4;
    ImageView Image5;
    ImageView Image6;



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

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/frock.png?alt=media&token=aed6d16f-9739-40a8-ba0b-51e6a3576b35").into(Image1);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/image-retrieve-33687.appspot.com/o/images%2Fcam1.jpeg?alt=media&token=ed6db825-e481-49d1-a7ff-8d9047ec59c2").into(Image2);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch.jpeg?alt=media&token=d0ae14f1-d1ec-40e4-a86a-22d90e8d9bce").into(Image3);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/tshirt.png?alt=media&token=5c105284-bb1d-4df6-b295-e3652d869711").into(Image4);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/shoes2.png?alt=media&token=b2cf57f7-01dd-4940-bff7-c2f5fa3e9a82").into(Image5);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/online-shopping-applicat-a6cd2.appspot.com/o/watch2.jpeg?alt=media&token=9a0753ef-846b-4b43-b76b-9a9625012e3c").into(Image6);

    }
}
