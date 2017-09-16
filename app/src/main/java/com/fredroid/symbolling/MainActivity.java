package com.fredroid.symbolling;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fredroid.symbolling.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private AdRequest mAdRequest;

    public  int mMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        mMode = intent.getIntExtra(FirstActivity.FUNC_TYPE,1);
     //   TextView textView = new TextView(this);


/*        ListView list = (ListView) findViewById(R.id.listname);
        Symbol s1 = new Symbol(",","Comma","lkd");
        Symbol s2 = new Symbol(";","Simcoma","ddfs");
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
        symbolList.add(s1);
        symbolList.add(s2);
       final SymbolAdapter adapter = new SymbolAdapter(this,symbolList);
        list.setAdapter(adapter);*/
        // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.testitem, DataSourse.Headlines);
       // list.setAdapter(adapter);
        MobileAds.initialize(this,"ca-app-pub-2373746499025999/6372347568");
        mAdView = (AdView) findViewById(R.id.adView3);
        if (BuildConfig.DEBUG)
        {
            mAdRequest = new AdRequest.Builder().addTestDevice("E56CCA964C2058652B8E19C7DE84EFFB")
                    .build();}
        else
        {
            mAdRequest = new AdRequest.Builder().build();
        }
        mAdView.loadAd(mAdRequest);





        if (findViewById(R.id.fragment_container) != null)
        {
            if (savedInstanceState != null)
            {
                return;
            }
            Headline headlinefragment = new Headline();
            headlinefragment.setMode(mMode);
           getSupportFragmentManager().beginTransaction()
                   .add(R.id.fragment_container, headlinefragment).commit();

        }

    }

    public void onClickTestButton(View view)
    {
        Intent intent = new Intent(this, PunctuationTestActivity.class);/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();*/
        intent.putExtra(FirstActivity.FUNC_TYPE, mMode);
        startActivity(intent);
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
