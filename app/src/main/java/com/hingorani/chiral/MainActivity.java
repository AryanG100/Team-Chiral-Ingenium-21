package com.hingorani.chiral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView title;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.titleLaunch);
        logo = findViewById(R.id.logoLaunch);

        title.setAlpha(0f);
        title.animate().alpha(1).setDuration(500);

        logo.setAlpha(0f);
        logo.animate().alpha(1).setDuration(1500);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();
            }
        }, 2000);



    }
}