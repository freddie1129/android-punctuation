package com.fredroid.symbolling;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    public int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        mMode = intent.getIntExtra(FirstActivity.FUNC_TYPE, 1);


        if (findViewById(R.id.fragmnt_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            Headline headlinefragment = new Headline();
            headlinefragment.setMode(mMode);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmnt_container, headlinefragment).commit();

        }

    }

    public void onClickTestButton(View view) {
        Intent intent = new Intent(this, PunctuationTestActivity.class);
        intent.putExtra(FirstActivity.FUNC_TYPE, mMode);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("About");
            alertDialog.setMessage(Html.fromHtml("<b/><p>Developer: <a href=\"mailto:freddiechenchen@gmail.com\">freddiechenchen@gmail.com</a></p>" +
                    "<p>Source Code:<a href=\"https://github.com/freddie1129/android-punctuation\">Github</a></p>"));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            alertDialog.show();
            TextView v = alertDialog.findViewById(android.R.id.message);
            v.setMovementMethod(LinkMovementMethod.getInstance());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
