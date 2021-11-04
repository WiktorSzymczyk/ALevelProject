package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    ImageView logo_image;
    TextView desc, gamer;
    Animation bottom, top, logo_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo_image = findViewById(R.id.logo);
        gamer = findViewById(R.id.gamer_text);
        desc = findViewById(R.id.bannerDescription_splash);

        logo_anim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        top = AnimationUtils.loadAnimation(this, R.anim.top_text_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_text_animation);

        logo_image.setAnimation(logo_anim);
        gamer.setAnimation(top);
        desc.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainIntent = new Intent(SplashScreenActivity.this, ChooseLoginORRegisterActivity.class);
                startActivity(MainIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}