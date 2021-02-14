package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class CalculatorResults extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String RECENT_PREFS = "recentPrefs";
    public static final String BEEF =  "beef";
    public static final String GAS = "gas";
    public static final String WATER = "water";
    public static final String DAIRY = "dairy";
    public static final String TOTAL = "total";

    User user = User.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calculator_results);

        calculateShow();
    }

    private void calculateShow() {
        float beefResult = user.calcMeat();
        TextView beef = (TextView) findViewById(R.id.beefCalculated);
        beef.setText(String.valueOf(beefResult));

        float gasResult = user.calcTransport();
        TextView gas = (TextView) findViewById(R.id.gasCalculated);
        gas.setText(String.valueOf(gasResult));

        float waterResult = user.calcWater();
        TextView water = (TextView) findViewById(R.id.waterCalculated);
        water.setText(String.valueOf(waterResult));

        float dairyResult = user.calcDairy();
        TextView dairy = (TextView) findViewById(R.id.dairyCalculated);
        dairy.setText(String.valueOf(dairyResult));

        float totalResult = user.calcTotal();
        TextView total = (TextView) findViewById(R.id.totalCalculated);
        total.setText(String.valueOf(totalResult));

        double compareResults = (double) (totalResult / 55.35714) * 100; //avg for BC
        int roundedResult = (int)(Math.round(compareResults));
        TextView compared = (TextView) findViewById(R.id.compared);
        if(compareResults < 100.0) {
            String result = String.valueOf(roundedResult);
            compared.setText("You are " + result + "% better than average");
        } else {
            String result = String.valueOf(roundedResult - 100.0);
            compared.setText("You are " + result + "% worse than average");
        }

        saveStats(((int)(Math.round(beefResult))), ((int)(Math.round(gasResult))), ((int)(Math.round(waterResult))), ((int)(Math.round(dairyResult))), ((int)(Math.round(totalResult))));
    }

    private void saveStats(int beef, int gas, int water, int dairy, int total) {
        SharedPreferences bestScore = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = bestScore.edit();
        if(beef < bestScore.getInt(BEEF, 9999)) {
            editor.putInt(BEEF, beef);
        }
        if(gas < bestScore.getInt(GAS, 9999)) {
            editor.putInt(GAS, gas);
        }
        if(water < bestScore.getInt(WATER, 9999)) {
            editor.putInt(WATER, gas);
        }
        if(dairy < bestScore.getInt(DAIRY, 9999)) {
            editor.putInt(DAIRY, gas);
        }
        if(total < bestScore.getInt(TOTAL, 9999)) {
            editor.putInt(TOTAL, gas);
        }

        editor.commit();

        SharedPreferences recentPrefs = getSharedPreferences(RECENT_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editNew = recentPrefs.edit();

        editNew.putInt(BEEF, beef);
        editNew.putInt(GAS, gas);
        editNew.putInt(WATER, water);
        editNew.putInt(DAIRY, dairy);
        editNew.putInt(TOTAL, total);

        editNew.commit();

        return;

    }
}