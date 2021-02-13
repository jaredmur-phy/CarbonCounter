package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class PersonalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Toast toast = Toast.makeText(getApplicationContext(), beefResult, LENGTH_SHORT);
        toast.show();
    }
}