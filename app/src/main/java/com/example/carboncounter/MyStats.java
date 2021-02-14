package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MyStats extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BEEF = "beef";
    public static final String GAS = "gas";
    public static final String WATER = "water";
    public static final String DAIRY = "dairy";
    public static final String TOTAL = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_stats);

        displayLastCalculation();
        displayBestCalculation();
        resetButton();
    }

    private void resetButton() {
        Button resetButton = (Button) findViewById(R.id.resetBttn);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences bestScore = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                bestScore.edit().clear().commit();

                TextView beefBest = (TextView) findViewById(R.id.meatBest);
                TextView gasBest = (TextView) findViewById(R.id.gasBest);
                TextView bestWater = (TextView) findViewById(R.id.waterBest);
                TextView bestDairy = (TextView) findViewById(R.id.dairyBest);
                TextView bestTotal = (TextView) findViewById(R.id.totalBest);

                beefBest.setText("");
                gasBest.setText("");
                bestWater.setText("");
                bestDairy.setText("");
                bestTotal.setText("");
            }
        });
    }

    private void displayBestCalculation() {
        SharedPreferences bestScore = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int beef = bestScore.getInt(BEEF, 10000);
        int gas = bestScore.getInt(GAS, 10000);
        int water = bestScore.getInt(WATER, 10000);
        int dairy = bestScore.getInt(DAIRY, 10000);
        int total = bestScore.getInt(TOTAL, 10000);

        TextView beefBest = (TextView) findViewById(R.id.meatBest);
        TextView gasBest = (TextView) findViewById(R.id.gasBest);
        TextView bestWater = (TextView) findViewById(R.id.waterBest);
        TextView bestDairy = (TextView) findViewById(R.id.dairyBest);
        TextView bestTotal = (TextView) findViewById(R.id.totalBest);

        beefBest.setText(String.valueOf(beef));
        gasBest.setText(String.valueOf(gas));
        bestWater.setText(String.valueOf(water));
        bestDairy.setText(String.valueOf(dairy));
        bestTotal.setText(String.valueOf(total));


    }

    private void displayLastCalculation() {

    }


}