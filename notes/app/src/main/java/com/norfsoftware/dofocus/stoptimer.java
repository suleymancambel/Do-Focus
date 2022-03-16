package com.norfsoftware.dofocus;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class stoptimer extends AppCompatActivity {

    Button start,stop;
    ImageView imageView;
    Animation animation,btnanimation;
    Chronometer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoptimer);
        getSupportActionBar().hide();
        start = findViewById(R.id.btnstartttt);
        stop = findViewById(R.id.btnstop);
        imageView = findViewById(R.id.arrow);
        timer= findViewById(R.id.timer);
        btnanimation = AnimationUtils.loadAnimation(this,R.anim.btn);
        stop.setAlpha(0);



        //animasyon yüklemesi
        animation = AnimationUtils.loadAnimation(this,R.anim.roundingalone);
       btnanimation = AnimationUtils.loadAnimation(this,R.anim.btn);
       start.startAnimation(btnanimation);
        stop.startAnimation(btnanimation);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                start.startAnimation(btnanimation);
                stop.startAnimation(btnanimation);
                imageView.startAnimation(animation);
                stop.animate().alpha(1).translationY(-80).setDuration(300).start();
                start.animate().alpha(0).setDuration(300).start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.startAnimation(btnanimation);
                stop.startAnimation(btnanimation);
                start.animate().alpha(1).translationY(-80).setDuration(300).start();
                stop.animate().alpha(0).setDuration(300).start();
                imageView.clearAnimation();
                animation.cancel();
                animation.reset();
                timer.stop();

            }
        });





    }


}