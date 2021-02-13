package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton();
        myStatsLaunch();

    }

    private void myStatsLaunch() {
        Button statsActivity = (Button) findViewById(R.id.myStats);
        statsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyStats.class);
                startActivity(intent);
            }
        });
    }

    private void goButton() {
        Button go = (Button) findViewById(R.id.startApp);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonalInfo.class);
                startActivity(intent);
            }
        });
    }
}