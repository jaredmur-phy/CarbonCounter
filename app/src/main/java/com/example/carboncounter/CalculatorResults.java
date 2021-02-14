package com.example.carboncounter;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/*
This activity:
- Calculates the carbon used based on user input from calling activity
- Calculates how the user fares against the BC average from 2017
- Displays results
- Allows the user to screenshot their results
- Saves results if they beat their "best result" for viewing
- Saves results no matter what for "most recent" viewing
*/
public class CalculatorResults extends AppCompatActivity {

    //SharedPreferences
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String RECENT_PREFS = "recentPrefs";
    public static final String BEEF =  "beef";
    public static final String GAS = "gas";
    public static final String WATER = "water";
    public static final String DAIRY = "dairy";
    public static final String TOTAL = "total";

    //Screenshot
    private Bitmap bitmap;
    private AppCompatActivity activity = CalculatorResults.this;

    //Singleton use
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calculator_results);

        calculateShow();
        saveResults();
    }

    /*
    Calculates carbon results from values saved in singleton, shows them to user
    */
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
        //Rounds results before sending to SharedPreferences
        saveStats(((int)(Math.round(beefResult))), ((int)(Math.round(gasResult))), ((int)(Math.round(waterResult))), ((int)(Math.round(dairyResult))), ((int)(Math.round(totalResult))));
    }

    //SharedPreferences, to save both best results and most recent results
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

    //Screenshot button
    private void saveResults(){
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionAndSave();
            }
        });
    }
    //Used from: http://www.androidtutorialshub.com/android-take-screenshot-programmatically/
    private void requestPermissionAndSave() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //Makes screenshot
                        bitmap = ScreenshotUtil.getInstance().takeScreenshotForScreen(activity);
                        if (bitmap != null) {
                            String path = Environment.getExternalStorageDirectory().toString() + "/CarbonCounter.png";
                            FileUtil.getInstance().storeBitmap(bitmap, path);
                            Toast.makeText(activity, "Saved in " + path, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(activity, "NULL", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(activity, "DENIED", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}