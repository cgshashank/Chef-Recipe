package com.app.cg.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splash_screen_thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent=new Intent(SplashScreen.this,ActivityMain.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
          splash_screen_thread.start();
    }
}
