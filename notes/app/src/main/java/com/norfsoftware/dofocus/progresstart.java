package com.norfsoftware.dofocus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class progresstart extends AppCompatActivity {

    private Button pmrd;
    private Button pmdrset;
    private Button pomodoro;
    Animation animation1,btn1;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresstart);
        getSupportActionBar().hide();

        pmrd= findViewById(R.id.btnset3);
        pmdrset = findViewById(R.id.btnset);
        animation1 = AnimationUtils.loadAnimation(this,R.anim.atg);
        btn1 = AnimationUtils.loadAnimation(this,R.anim.btn);
        imageView = findViewById(R.id.imageViewpomo);
        pomodoro = findViewById(R.id.btnset2);
        getSupportActionBar().hide();



        pmrd.startAnimation(btn1);
//        pmdrset.startAnimation(btn1);
        pomodoro.startAnimation(btn1);
        imageView.startAnimation(animation1);

        pmrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent b2 = new Intent(progresstart.this,stoptimer.class);
                b2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(b2);

            }
        });



        pomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b3 = new Intent(progresstart.this,pomodoro_sayac.class);
                b3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(b3);

            }
        });
    }


}