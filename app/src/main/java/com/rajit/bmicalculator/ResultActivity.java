package com.rajit.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import static android.Manifest.permission.*;

public class ResultActivity extends AppCompatActivity {
    TextView resultLabel, bmiTv, svu, svu_tv, u, u_tv, h, h_tv, ovw, ovw_tv, ob1,
            ob1_tv, ob2, ob2_tv, ob3, ob3_tv;

    ImageView cursor;
    Button reset;

    String bmi;
    float samp;

    Typeface din1451TTF, latoBoldTTF, latoRegularTTF, nunitoTTF, robotoTTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultLabel = findViewById(R.id.resultLabel);
        bmiTv = findViewById(R.id.bmiTv);
        svu = findViewById(R.id.svu);
        svu_tv = findViewById(R.id.svu_tv);
        u = findViewById(R.id.u);
        u_tv = findViewById(R.id.u_tv);
        h = findViewById(R.id.h);
        h_tv = findViewById(R.id.h_tv);
        ovw = findViewById(R.id.ovw);
        ovw_tv = findViewById(R.id.ovw_tv);
        ob1 = findViewById(R.id.ob1);
        ob1_tv = findViewById(R.id.ob1_tv);
        ob2 = findViewById(R.id.ob2);
        ob2_tv = findViewById(R.id.ob2_tv);
        ob3 = findViewById(R.id.ob3);
        ob3_tv = findViewById(R.id.ob3_tv);

        reset = findViewById(R.id.reset);

        cursor = findViewById(R.id.cursor);

        //Setting Typeface

        settingTypeFace();

        bmi = getIntent().getStringExtra("Calculated BMI");
        bmiTv.setText("BMI:  "+bmi+" Kg/m\u00B2");


        samp = Float.parseFloat(bmi);

        highlightBMIResultBar();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });


    }

    private void settingTypeFace() {

        robotoTTF = Typeface.createFromAsset(getAssets(), "fonts/roboto_bold.ttf");
        nunitoTTF = Typeface.createFromAsset(getAssets(), "fonts/nunito_bold.ttf");
        latoBoldTTF = Typeface.createFromAsset(getAssets(), "fonts/lato_bold.ttf");
        latoRegularTTF = Typeface.createFromAsset(getAssets(), "fonts/lato_regular.ttf");
        din1451TTF = Typeface.createFromAsset(getAssets(), "fonts/din1451.ttf");

        resultLabel.setTypeface(robotoTTF);
        bmiTv.setTypeface(latoBoldTTF);

        svu.setTypeface(latoRegularTTF);
        svu_tv.setTypeface(din1451TTF);
        u.setTypeface(latoRegularTTF);
        u_tv.setTypeface(din1451TTF);
        h.setTypeface(latoRegularTTF);
        h_tv.setTypeface(din1451TTF);
        ovw.setTypeface(latoRegularTTF);
        ovw_tv.setTypeface(din1451TTF);
        ob1.setTypeface(latoRegularTTF);
        ob1_tv.setTypeface(din1451TTF);
        ob2.setTypeface(latoRegularTTF);
        ob2_tv.setTypeface(din1451TTF);
        ob3.setTypeface(latoRegularTTF);
        ob3_tv.setTypeface(din1451TTF);

        reset.setTypeface(nunitoTTF);

    }

    private void highlightBMIResultBar() {

        if(samp <= 15.0){

            svu.setTextColor(Color.parseColor("#F8155B"));
            svu_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(-110);

        }else if(samp>=15.1 && samp<=18.4){

            u.setTextColor(Color.parseColor("#F8155B"));
            u_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(-50);

        }else if(samp>=18.5 && samp<=24.9){

            h.setTextColor(Color.parseColor("#F8155B"));
            h_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(0);

        }else if(samp>= 25.0 && samp<=29.9){

            ovw.setTextColor(Color.parseColor("#F8155B"));
            ovw_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(45);

        }else if(samp>=30.0 && samp<=34.9){

            ob1.setTextColor(Color.parseColor("#F8155B"));
            ob1_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(90);

        }else if(samp >= 35.0 && samp <= 39.9){

            ob2.setTextColor(Color.parseColor("#F8155B"));
            ob2_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(90);

        }else{

            ob3.setTextColor(Color.parseColor("#F8155B"));
            ob3_tv.setTextColor(Color.parseColor("#F8155B"));
            cursor.setRotation(140);

        }

    }


}
