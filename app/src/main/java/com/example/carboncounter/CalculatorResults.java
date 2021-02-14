package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class CalculatorResults extends AppCompatActivity {

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
    }
}