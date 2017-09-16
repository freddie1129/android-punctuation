package com.fredroid.symbolling;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fredroid.symbolling.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;

public class PunctuationTestActivity extends AppCompatActivity {
    Button butAnswerA,butAnswerB,butAnswerC,butAnswerD;
    public TextView tvQuestion;
    public TextView tvIndex;
    private int mQuestionIndex = 0;
    private int mAnsIndex = 0;
    ArrayList<Integer> mquestionList = new ArrayList<Integer>();
    ArrayList<Integer> mansList = new ArrayList<Integer>();
    String [][] data;
    ArrayList<Integer> mRadomQuestionIndx = new ArrayList<Integer>();

    public int mMode;
    AdView  mAdView;
    AdRequest mAdRequest;


    public String mstrQuestion,mstrAnswerA,mstrAnswerB,mstrAnswerC,mstrAnswerD,mstrNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
           // mQuestionIndex--;
           // mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
            int a = 90;
            a = 90 + 89;
            return;
        }

        setContentView(R.layout.activity_punctuation_test);
        butAnswerA = (Button)findViewById(R.id.button_answer_a);
        butAnswerB = (Button)findViewById(R.id.button_answer_b);
        butAnswerC = (Button)findViewById(R.id.button_answer_c);
        butAnswerD = (Button)findViewById(R.id.button_answer_d);
        tvQuestion = (TextView)findViewById(R.id.textView_question);
        tvIndex = (TextView)findViewById(R.id.textView_Count);

        for(int i = 0; i < Punctuations.data.length; i++)
        {
            mquestionList.add(i);
            mRadomQuestionIndx.add(i);

        }
        for (int i = 0; i < 4; i++)
        {
            mansList.add(i);
        }

        Intent intent = getIntent();
        mMode = intent.getIntExtra(FirstActivity.FUNC_TYPE,1);
        data = (mMode == FirstActivity.FUNC_SYMBOL) ? Punctuations.data : Punctuations.datagreek;

        Collections.shuffle(mRadomQuestionIndx);

        mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
        MobileAds.initialize(this,"ca-app-pub-2373746499025999/6372347568");
        mAdView = (AdView) findViewById(R.id.adView2);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

       // if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //do your stuff here
            //
       // } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //do your stuff here
            setContentView(R.layout.activity_punctuation_test);
            butAnswerA = (Button)findViewById(R.id.button_answer_a);
            butAnswerB = (Button)findViewById(R.id.button_answer_b);
            butAnswerC = (Button)findViewById(R.id.button_answer_c);
            butAnswerD = (Button)findViewById(R.id.button_answer_d);
            tvQuestion = (TextView)findViewById(R.id.textView_question);
            tvIndex = (TextView)findViewById(R.id.textView_Count);
            butAnswerA.setText(mstrAnswerA);
            butAnswerB.setText(mstrAnswerB);
            butAnswerC.setText(mstrAnswerC);
            butAnswerD.setText(mstrAnswerD);
            tvQuestion.setText(mstrQuestion);
            tvIndex.setText(mstrNum);
            mAdView = (AdView) findViewById(R.id.adView2);
        if (BuildConfig.DEBUG)
        {
            mAdRequest = new AdRequest.Builder().addTestDevice("E56CCA964C2058652B8E19C7DE84EFFB")
                    .build();}
        else
        {
            mAdRequest = new AdRequest.Builder().build();
        }
            mAdView.loadAd(mAdRequest);


      //  }

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

    public int CreateQuestion(int qindex)
    {
        Collections.shuffle(mansList);
        int []index = generateQuestion(qindex);
        mstrQuestion = data[qindex][0];
        tvQuestion.setText(mstrQuestion);
        mstrAnswerA = "A." + data[index[mansList.get(0)]][1];
        butAnswerA.setText(mstrAnswerA);
        mstrAnswerB = "B." + data[index[mansList.get(1)]][1];
        butAnswerB.setText(mstrAnswerB);
        mstrAnswerC = "C." + data[index[mansList.get(2)]][1];
        butAnswerC.setText(mstrAnswerC);
        mstrAnswerD = "D." + data[index[mansList.get(3)]][1];
        butAnswerD.setText(mstrAnswerD);
        mstrNum = String.format("#%02d/%02d",mQuestionIndex + 1,data.length);
        tvIndex.setText(mstrNum);

         return mansList.indexOf(3);   //
    }
    //Create a question randomly, the first three are wrong, and the last one is always the right answer
    public int [] generateQuestion(int quesIndex)
    {
        int [] questionCollection = new int[4];
        Collections.shuffle(mquestionList);
        for (int i = 0; i < 3; i++)
        {
            if (mquestionList.get(i) == quesIndex)
                Collections.swap(mquestionList,i,3);
        }
        for (int i = 0; i < 3; i++)
        {
            questionCollection[i] = mquestionList.get(i);
        }
        questionCollection[3] = quesIndex;      //Last one always is the index of question's answer
        return  questionCollection;
    }


    public int getRadomQuestionIndex(int escape,int max)
    {
        int ans;
        while (true)
        {
            ans = (int)Math.random() * max;
            if (ans != escape)
            {
                return ans;
            }
        }
    }

    public void nextQuestion(int Answer,String txt)
    {
        if (mAnsIndex == Answer)
        {
            //answer correctly

            if (mQuestionIndex == mquestionList.size() - 1)
            {
                mQuestionIndex = 0;
                Collections.shuffle(mRadomQuestionIndx);
            }
            else
            {
                mQuestionIndex = mQuestionIndex + 1;
            }



            mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));


            String strAudioPath = txt.subSequence(2,(txt.length())).toString();
            strAudioPath = strAudioPath.replace(" ","_");
            int drawableResourceId = this.getResources().getIdentifier(strAudioPath, "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this,drawableResourceId);
            mediaPlayer.start();
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Incorrect!";
            int duration = Toast.LENGTH_SHORT;


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void onClickA(View view)
    {
        nextQuestion(0,butAnswerA.getText().toString());
    }

    public void onClickB(View view)
    {
        nextQuestion(1,butAnswerB.getText().toString());
    }

    public void onClickC(View view)
    {
        nextQuestion(2,butAnswerC.getText().toString());
    }

    public void onClickD(View view)
    {
        nextQuestion(3,butAnswerD.getText().toString());
    }


}
