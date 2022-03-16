package com.norfsoftware.dofocus;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.norfsoftware.dofocus.App.CHANNEL_1_ID;
import static com.norfsoftware.dofocus.App.CHANNEL_2_ID;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;

public class pomodoro_sayac extends AppCompatActivity {


//    final String pmdrnoti1 = getResources().getString(R.string.mola_yap);
//    final String pmdrnoti2 = getResources().getString(R.string.mola_yap);



 private    ProgressBar mProgressBar1;
    private Button startbtn;
    Animation animation,btn;
    private Button reset;
    private Button pausebtn;
    private TextView textView;
    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;
    private long timeBlinkInMilliseconds;
    private long mEndTime;
    private AdView mAdView;

    private static final long START_TIME_IN_MILLIS=1500000 ;
    private  boolean mTimerRunning;
    private long    mtimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_sayac);
        getSupportActionBar().hide();

        animation = AnimationUtils.loadAnimation(this,R.anim.atg);
        btn = AnimationUtils.loadAnimation(this,R.anim.btn);

        startbtn=findViewById(R.id.startbtn);
        reset = findViewById(R.id.reset);
        mProgressBar1 = findViewById(R.id.progressbartime);
        textView=findViewById(R.id.timepomodoro);

        startbtn.startAnimation(btn);
        reset.startAnimation(btn);















        mProgressBar1.startAnimation(animation);


        MobileAds.initialize(this, new
                OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.sayacbanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    pauseTimer();
                }
                else    {
                    startTimer();
                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        resetTimer();
            }
        });




    }



    private void resetTimer() {




        mtimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        startbtn.setVisibility(View.VISIBLE);
        updateCountDownText();
//        int time=25;
//        mProgressBar1.setMax(60*time);

        mProgressBar1.setProgress((int) (mtimeLeftInMillis / 1000));




    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mtimeLeftInMillis;
        countDownTimer = new CountDownTimer(mtimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int time =25;
                mtimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mProgressBar1.setMax(60*time);

                mProgressBar1.setProgress((int) (mtimeLeftInMillis / 1000));

            }

            @Override
            public void onFinish() {


                String noti3 = getString(R.string.sure_bitti);
                String noti4 = getString(R.string.mola_yap);


                Intent resultIntent = new Intent( pomodoro_sayac.this, mola.class );
                PendingIntent resultPendingIntent = PendingIntent.getActivity(pomodoro_sayac.this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                mTimerRunning = false;
//                startbtn.setText("Start");
//                startbtn.setVisibility(View.INVISIBLE);
//                reset.setVisibility(View.VISIBLE);
                updateButtons();

                ////////

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel1 = new NotificationChannel(
                            CHANNEL_1_ID,
                            "Channel 1",
                            NotificationManager.IMPORTANCE_HIGH
                    );
                    channel1.setDescription("This is Channel 1");

                    NotificationChannel channel2 = new NotificationChannel(
                            CHANNEL_2_ID,
                            "Channel 2",
                            NotificationManager.IMPORTANCE_LOW
                    );
                    channel2.setDescription("This is Channel 2");

                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel1);
                    manager.createNotificationChannel(channel2);
                }





//                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//                    NotificationChannel channel = new NotificationChannel("mynoti","myoti", NotificationManager.IMPORTANCE_DEFAULT);
//                    NotificationManager manager = getSystemService(NotificationManager.class);
//                    manager.createNotificationChannel(channel);
//                }

//
//



                NotificationCompat.Builder builder = new NotificationCompat.Builder(pomodoro_sayac.this,"channel1");
                builder.setContentTitle(noti3);
                builder.setContentText(noti4);

                builder.setContentIntent(resultPendingIntent);

                builder.setAutoCancel(true);



                builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(pomodoro_sayac.this);
                managerCompat.notify(1, builder.build());


//                NotificationCompat.Builder mbuilder = (NotificationCompat.Builder)
//                        new NotificationCompat.Builder(getApplicationContext())
//                                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24,10)
//
//
//                                .setContentTitle("Notification")
//                                .setContentText("This is a notification for you");
//
//                NotificationManager notificationManager = (NotificationManager)
//                        getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(0,mbuilder.build());


                new AlertDialog.Builder(pomodoro_sayac.this)
                        .setTitle(R.string.sure_bitti)
                        .setMessage(R.string.mola_yap)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent b3 = new Intent(pomodoro_sayac.this,mola.class);
//                                textView.setText("05:00");

                                startActivity(b3);

                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetTimer();
//                        finish();

                    }
                }).show();


            }
        }.start();

        mTimerRunning = true;
//        startbtn.setText("pause");
//        reset.setVisibility(View.INVISIBLE);
        updateButtons();
    }

    private void updateCountDownText() {

        int minutes = (int) (mtimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mtimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textView.setText(timeLeftFormatted);
    }
    private void updateButtons(){
        if (mTimerRunning) {
            reset.setVisibility(View.INVISIBLE);
            startbtn.setText(R.string.stoppomodoro);
        } else {
            startbtn.setText(R.string.startpomodorosayac);

            if (mtimeLeftInMillis < 1000) {
                startbtn.setVisibility(View.INVISIBLE);
            } else {
                startbtn.setVisibility(View.VISIBLE);
            }

            if (mtimeLeftInMillis < START_TIME_IN_MILLIS) {
                reset.setVisibility(View.VISIBLE);
            } else {
                reset.setVisibility(View.INVISIBLE);
            }
    }     }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft",mtimeLeftInMillis);
        outState.putBoolean("timerRunning",mTimerRunning);
        outState.putLong("endTime", mEndTime);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mtimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        updateButtons();

        if (mTimerRunning) {
            mEndTime = savedInstanceState.getLong("endTime");
            mtimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }
    }

    private void pauseTimer() {

        countDownTimer.cancel();
        mTimerRunning = false;
       updateButtons();
    }
}