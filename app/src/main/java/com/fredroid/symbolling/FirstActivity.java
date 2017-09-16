package com.fredroid.symbolling;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.fredroid.symbolling.R;

import java.util.Locale;

public class FirstActivity extends Activity {
    private AdView mAdView;
    private AdRequest mAdRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);
        String strWel;
        strWel = String.format("%d %s%s",Punctuations.data.length,getString(R.string.punctuationUnit),getString(R.string.punctuation));
        ((TextView) findViewById(R.id.textView_puncintro)).setText(strWel);
        strWel = String.format("%d %s%s",Punctuations.datagreek.length,getString(R.string.punctuationUnit),getString(R.string.greekalph));
        ((TextView) findViewById(R.id.textView_greekintro)).setText(strWel);

       // MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        mAdView = (AdView) findViewById(R.id.adView);

        if (BuildConfig.DEBUG)
        {
            mAdRequest = new AdRequest.Builder().addTestDevice("E56CCA964C2058652B8E19C7DE84EFFB")
                .build();}
        else
            {
                mAdRequest = new AdRequest.Builder().build();
            }
        mAdView.loadAd(mAdRequest);
    }

    public static int FUNC_SYMBOL = 1;
    public  static int FUNC_GREEK = 2;
    final public  static  String FUNC_TYPE = "FUNCTYPE";
    public void onClickPunctuation(View view) {
        Intent intent = new Intent(this, MainActivity.class);/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();*/
        intent.putExtra(FUNC_TYPE, FUNC_SYMBOL);
        startActivity(intent);
    }

    public void onClickMath(View view) {
        /*Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
    }

    public void onClickGreek(View view) {
        Intent intent = new Intent(this, MainActivity.class);/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();*/
        intent.putExtra(FUNC_TYPE, FUNC_GREEK);
        startActivity(intent);
    }

    public void onExit(View view)
    {
        finish();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
