package com.fredroid.symbolling;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class PunctuationTestActivity extends AppCompatActivity {
    public TextView tvQuestion;
    public TextView tvIndex;
    public int mMode;
    public String mstrQuestion, mstrAnswerA, mstrAnswerB, mstrAnswerC, mstrAnswerD, mstrNum;
    Button butAnswerA, butAnswerB, butAnswerC, butAnswerD;
    ArrayList<Integer> mquestionList = new ArrayList<Integer>();
    ArrayList<Integer> mansList = new ArrayList<Integer>();
    String[][] data;
    ArrayList<Integer> mRadomQuestionIndx = new ArrayList<Integer>();
    private int mQuestionIndex = 0;
    private int mAnsIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_punctuation_test);
        butAnswerA = findViewById(R.id.button_answer_a);
        butAnswerB = findViewById(R.id.button_answer_b);
        butAnswerC = findViewById(R.id.button_answer_c);
        butAnswerD = findViewById(R.id.button_answer_d);
        tvQuestion = findViewById(R.id.textView_question);
        tvIndex = findViewById(R.id.textView_Count);

        for (int i = 0; i < Punctuations.data.length; i++) {
            mquestionList.add(i);
            mRadomQuestionIndx.add(i);

        }
        for (int i = 0; i < 4; i++) {
            mansList.add(i);
        }

        Intent intent = getIntent();
        mMode = intent.getIntExtra(FirstActivity.FUNC_TYPE, 1);
        data = (mMode == FirstActivity.FUNC_SYMBOL) ? Punctuations.data : Punctuations.datagreek;

        Collections.shuffle(mRadomQuestionIndx);

        mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        setContentView(R.layout.activity_punctuation_test);
        butAnswerA = findViewById(R.id.button_answer_a);
        butAnswerB = findViewById(R.id.button_answer_b);
        butAnswerC = findViewById(R.id.button_answer_c);
        butAnswerD = findViewById(R.id.button_answer_d);
        tvQuestion = findViewById(R.id.textView_question);
        tvIndex = findViewById(R.id.textView_Count);
        butAnswerA.setText(mstrAnswerA);
        butAnswerB.setText(mstrAnswerB);
        butAnswerC.setText(mstrAnswerC);
        butAnswerD.setText(mstrAnswerD);
        tvQuestion.setText(mstrQuestion);
        tvIndex.setText(mstrNum);



    }


    public int CreateQuestion(int qindex) {
        Collections.shuffle(mansList);
        int[] index = generateQuestion(qindex);
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
        mstrNum = String.format("#%02d/%02d", mQuestionIndex + 1, data.length);
        tvIndex.setText(mstrNum);

        return mansList.indexOf(3);   //
    }

    //Create a question randomly, the first three are wrong, and the last one is always the right answer
    public int[] generateQuestion(int quesIndex) {
        int[] questionCollection = new int[4];
        Collections.shuffle(mquestionList);
        for (int i = 0; i < 3; i++) {
            if (mquestionList.get(i) == quesIndex)
                Collections.swap(mquestionList, i, 3);
        }
        for (int i = 0; i < 3; i++) {
            questionCollection[i] = mquestionList.get(i);
        }
        questionCollection[3] = quesIndex;      //Last one always is the index of question's answer
        return questionCollection;
    }




    public void nextQuestion(int Answer, String txt) {
        if (mAnsIndex == Answer) {
            //answer correctly

            if (mQuestionIndex == mquestionList.size() - 1) {
                mQuestionIndex = 0;
                Collections.shuffle(mRadomQuestionIndx);
            } else {
                mQuestionIndex = mQuestionIndex + 1;
            }


            mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));


            String strAudioPath = txt.subSequence(2, (txt.length())).toString();
            strAudioPath = strAudioPath.replace(" ", "_");
            int drawableResourceId = this.getResources().getIdentifier(strAudioPath, "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this, drawableResourceId);
            mediaPlayer.start();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Incorrect!";
            int duration = Toast.LENGTH_SHORT;


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void onClickA(View view) {
        nextQuestion(0, butAnswerA.getText().toString());
    }

    public void onClickB(View view) {
        nextQuestion(1, butAnswerB.getText().toString());
    }

    public void onClickC(View view) {
        nextQuestion(2, butAnswerC.getText().toString());
    }

    public void onClickD(View view) {
        nextQuestion(3, butAnswerD.getText().toString());
    }


}
