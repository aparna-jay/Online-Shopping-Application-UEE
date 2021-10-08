package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class FavouriteNEWActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_n_e_w);

        LayoutInflater inflater = getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.favourite_row, null, true);

        Intent i = getIntent();
        String image = i.getStringExtra("");
        String price = i.getStringExtra("");


    }
}