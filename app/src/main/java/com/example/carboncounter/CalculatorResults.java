package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.round;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class CalculatorResults extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
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
        saveResults();
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
        return;

    }
    private Bitmap takeScreenshot() {
        View screenshot = findViewById(android.R.id.content).getRootView();
        screenshot.setDrawingCacheEnabled(true);
        return screenshot.getDrawingCache();
    } //takes the screenshot
    private void saveScreenshot(Bitmap finalBitmap, String image_name) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveResults(){
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveScreenshot(bitmap, "results");

            }
        });
    }
}