package com.norfsoftware.dofocus;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.norfsoftware.dofocus.R;

public class Start_activity extends AppCompatActivity {
    Animation animation,btn;
    ImageView imageView;
    Button podo;
    private AdView mAdView;
    Button todo;
    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        getSupportActionBar().hide();





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        podo = findViewById(R.id.podo);
        todo = findViewById(R.id.todo);
        imageView = findViewById(R.id.imageView);




//        animasyonlar işlemleri
        animation = AnimationUtils.loadAnimation(this,R.anim.atg);
        btn = AnimationUtils.loadAnimation(this,R.anim.btn);

        podo.startAnimation(btn);
        todo.startAnimation(btn);
        imageView.startAnimation(animation);



///////reklamişlemleri
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd.load(this,"ca-app-pub-8999351861022970/6427416207", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });
        //catch

//        podo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent b1 = new Intent(Start_activity.this,progresstart.class);
//                b1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(b1);
//
//
//            }
//        });

        podo.setOnClickListener(v -> {
            showads();
            startActivity(new Intent(Start_activity.this, pomodoro_sayac.class));
        });



        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showads();
                Intent b = new Intent(Start_activity.this,MainActivity.class);
//                b.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(b);
            }
        });









    }

    public void  showads(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(Start_activity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }


}