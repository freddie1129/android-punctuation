package com.fredroid.symbolling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends Activity {

    final public static String FUNC_TYPE = "FUNCTYPE";
    public static int FUNC_SYMBOL = 1;
    public static int FUNC_GREEK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);
        String strWel;
        strWel = String.format("%d %s%s", Punctuations.data.length, getString(R.string.punctuationUnit), getString(R.string.punctuation));
        ((TextView) findViewById(R.id.textView_puncintro)).setText(strWel);
        strWel = String.format("%d %s%s", Punctuations.datagreek.length, getString(R.string.punctuationUnit), getString(R.string.greekalph));
        ((TextView) findViewById(R.id.textView_greekintro)).setText(strWel);


    }

    public void onClickPunctuation(View view) {
        Intent intent = new Intent(this, MainActivity.class);/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();*/
        intent.putExtra(FUNC_TYPE, FUNC_SYMBOL);
        startActivity(intent);
    }



    public void onClickGreek(View view) {
        Intent intent = new Intent(this, MainActivity.class);/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();*/
        intent.putExtra(FUNC_TYPE, FUNC_GREEK);
        startActivity(intent);
    }

    public void onExit(View view) {
        finish();
    }


}
