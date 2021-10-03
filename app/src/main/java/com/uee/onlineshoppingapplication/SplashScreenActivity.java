package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
//import android.widget.ImageView;
//
//import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    //    ImageView logo, splashImg;
//    LottieAnimationView lottieAnimationView;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(6000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreenActivity.this, HomeFragment.class);
//                        startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

//        logo = findViewById(R.id.logo);
//        lottieAnimationView = findViewById(R.id.lottie);
//        splashImg = findViewById(R.id.splash_image);
//
//        splashImg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
//        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
    }
}
