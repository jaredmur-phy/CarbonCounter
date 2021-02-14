package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/*
Takes users info about their carbon usages and saves to singleton
- Calculate button shows carbon footprint stats
*/
public class PersonalInfo extends AppCompatActivity {

    User user = User.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_personal_info);

        setCalculateButton();
    }

    private void setCalculateButton() {
        Button calculate = (Button) findViewById(R.id.goCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Intent intent = new Intent(getApplicationContext(), CalculatorResults.class);
                startActivity(intent);
            }
        });
    }

    private void calculate() {
        EditText beef = (EditText) findViewById(R.id.enterBeef);
        String beefResult = beef.getText().toString();
        if(!beefResult.matches("")) {
            user.setBeef(Float.parseFloat(beefResult));
        }

        EditText pork = (EditText) findViewById(R.id.enterPork);
        String porkResult = pork.getText().toString();
        if(!porkResult.matches("")) {
            user.setPork(Float.parseFloat(porkResult));
        }
        EditText lamb = (EditText) findViewById(R.id.enterLamb);
        String lambResult = lamb.getText().toString();
        if(!lambResult.matches("")) {
            user.setLamb(Float.parseFloat(lambResult));
        }
        EditText chicken = (EditText) findViewById(R.id.enterChicken);
        String chickenResult = chicken.getText().toString();
        if(!chickenResult.matches("")) {
            user.setChicken(Float.parseFloat(chickenResult));
        }
        EditText gas = (EditText) findViewById(R.id.enterGas);
        String gasResult = gas.getText().toString();
        if(!gasResult.matches("")) {
            user.setGas(Float.parseFloat(gasResult));
        }

        EditText dairy = (EditText) findViewById(R.id.enterDairy);
        String dairyResult = dairy.getText().toString();
        if(!dairyResult.matches("")) {
            user.setDairy(Float.parseFloat(dairyResult));
        }
        EditText water = (EditText) findViewById(R.id.enterWater);
        String waterResult = water.getText().toString();
        if(!waterResult.matches("")) {
            user.setWater(Float.parseFloat(waterResult));
        }
    }
}