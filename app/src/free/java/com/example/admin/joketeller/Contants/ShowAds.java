package com.example.admin.joketeller.Contants;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.joketeller.MainActivity;
import com.example.admin.joketeller.Sync.AdClosed;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by admin on 2/11/2017.
 */

public class ShowAds extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    Runnable displayAd;
    private Handler mHandler;

    public void addCode(final Context context, final AdClosed adClosed)
    {
    mInterstitialAd = new InterstitialAd(context);
    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

    mInterstitialAd.setAdListener(new AdListener() {
        @Override
        public void onAdClosed() {
            requestNewInterstitial();
            adClosed.afterAdClosed();
       }
    });
        mHandler = new Handler(Looper.getMainLooper());
        displayAd = new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    }
                });
            }
        };

    requestNewInterstitial();

}

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    public void displayInterstitial() {
        mHandler.postDelayed(displayAd, 1);
    }

}
