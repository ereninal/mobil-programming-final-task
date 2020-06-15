package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread openpage = new Thread(){
            @Override
            public void run() {
                ImageView logo = findViewById(R.id.id_opened);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_open);
                logo.startAnimation(animation);
            }
        };
        openpage.start();
        Thread redirect = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();//activitynin sonlanması için gerekli, geri butonuna basıldığında tekrar başlamamsını sağlar
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        redirect.start();

    }
}
