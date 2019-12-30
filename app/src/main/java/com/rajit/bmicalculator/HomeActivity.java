package com.rajit.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    LinearLayout rootLayout;
    TextView screenLabel, bodyweightLabel, body_weight_tv, heightLabel, height_tv;
    SeekBar body_weight_seekbar, height_seekbar;
    Button calculateBtn;

    int heightMinVal = 30;
    double heightM = 0.0f;

    double bmi;

    Typeface nunitoTTF, robotoTTF, latoBoldTTF, latoSemiBoldTTF, latoRegularTTF, din1451TTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Fade fade = new Fade();
            View decor = getWindow().getDecorView();

            fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);

            getWindow().getSharedElementEnterTransition().setDuration(1000);

        }

        //Root Linear Layout

        rootLayout = findViewById(R.id.rootLayout);

        //SeekBar

        body_weight_seekbar = findViewById(R.id.body_weight_seekbar);
        height_seekbar = findViewById(R.id.height_seekbar);

        //TextViews

        screenLabel = findViewById(R.id.screenLabel);
        bodyweightLabel = findViewById(R.id.bodyweightLabel);
        body_weight_tv = findViewById(R.id.body_weight_tv);
        heightLabel = findViewById(R.id.heightLabel);
        height_tv = findViewById(R.id.height_tv);



        //Calculate Button

        calculateBtn = findViewById(R.id.calculateBtn);

        settingTypeface();


        // Listening to the progress of the WEIGHT SeekBar and passing it to the WEIGHT TextView

        body_weight_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                body_weight_tv.setText(progress+" Kg");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Listening to the progress of the HEIGHT SeekBar and passing it to the HEIGHT TextView
        // Before passing the progress value we need to convert it to floating value


        height_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {


                progress += heightMinVal;
                height_tv.setText(String.format("%.1f", getHeightInFt(progress))+"ft");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pro = height_seekbar.getProgress();
                pro += 30;
                heightM = getHeightInMetres(getHeightInFt(pro));

                int weight = body_weight_seekbar.getProgress();
                if (weight != 0){

                    bmi = (weight/(heightM*heightM));
                    String bmiConverted = String.format("%.2f", bmi);


                    Intent result = new Intent(getApplicationContext(), ResultActivity.class);
                    result.putExtra("Calculated BMI", ""+bmiConverted);
                    startActivity(result);
                }else{

                    Snackbar ss = Snackbar.make(rootLayout, "Weight cannot be 0", Snackbar.LENGTH_SHORT)
                            .setActionTextColor(Color.WHITE);
                    View v = ss.getView();
                    v.setBackgroundColor(Color.RED);
                    ss.show();
                }




            }
        });


    }

    private void settingTypeface() {

        robotoTTF = Typeface.createFromAsset(getAssets(), "fonts/roboto_bold.ttf");
        nunitoTTF = Typeface.createFromAsset(getAssets(), "fonts/nunito_bold.ttf");
        latoBoldTTF = Typeface.createFromAsset(getAssets(), "fonts/lato_bold.ttf");
        latoSemiBoldTTF = Typeface.createFromAsset(getAssets(), "fonts/lato_semibold.ttf");
        latoRegularTTF = Typeface.createFromAsset(getAssets(), "fonts/lato_regular.ttf");
        din1451TTF = Typeface.createFromAsset(getAssets(), "fonts/din1451.ttf");

        //TextView

        screenLabel.setTypeface(robotoTTF);
        bodyweightLabel.setTypeface(latoBoldTTF);
        heightLabel.setTypeface(latoBoldTTF);
        body_weight_tv.setTypeface(nunitoTTF);
        height_tv.setTypeface(nunitoTTF);

        //Calculate Button

        calculateBtn.setTypeface(latoBoldTTF);

    }

    public float getHeightInFt(int v){

        return .1f * v;

    }

    public double getHeightInMetres(float v){

        return 0.3048 * v;
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_dialog, viewGroup, false);

        Button positiveBtn = dialogView.findViewById(R.id.positiveBtn);
        Button negativeBtn = dialogView.findViewById(R.id.negativeBtn);

        TextView promptQ = dialogView.findViewById(R.id.promptQ);

        //Setting Typeface for Dialog Buttons

        promptQ.setTypeface(nunitoTTF);
        positiveBtn.setTypeface(latoSemiBoldTTF);
        negativeBtn.setTypeface(latoSemiBoldTTF);

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAffinity();

            }
        });



        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();

            }
        });
        alertDialog.show();




//        Toast.makeText(this, "Use an Exit Dialog in this activity", Toast.LENGTH_SHORT).show();
//        s = Snackbar.make(rootLayout, "Done!", Snackbar.LENGTH_INDEFINITE)
//                .setAction("Close", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        s.dismiss();
//                    }
//                })
//                .setActionTextColor(Color.parseColor("#ffffff"));
//        View sView = s.getView();
//        sView.setBackgroundColor(Color.parseColor("#43BE31"));
//        s.show();

        /* Use this method to remove the splash text logo from the launcher after exiting the app */
//        finish();
    }
}
