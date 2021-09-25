package com.uee.onlineshoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

//    //Variables
//    DrawerLayout drawerLayout;
//    ActionBarDrawerToggle toggle;
//    Toolbar toolbar;
//    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //       Hooks
//
//        drawerLayout = findViewById(R.id.DrawerLayout);
//        toolbar = findViewById(R.id.toolBar);
//        navigationView = findViewById(R.id.nav_view);
//
//        setSupportActionBar(toolbar);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int id=item.getItemId();
//                switch (id)
//                {
//                    case R.id.nav_home:
//                        break;
//                    case R.id.nav_cart:
//                        break;
//                    case R.id.nav_favourites:
//                        break;
//                    case R.id.nav_profile:
//                        break;
//                    case R.id.nav_login:
//                        break;
//                    case R.id.nav_share:
//                        break;
//                    case R.id.nav_rate:
//                        break;
//                }
//                return true;
//            }
//        });
//
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


    }
}