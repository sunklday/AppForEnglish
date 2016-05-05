package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private boolean correct;
    private TextView txvTitle;
    private List<Words> list;
    private List<String> otherMeanList;
    private int reviewCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_review);
        btnA = (Button) findViewById(R.id.btn_A);
        btnB = (Button) findViewById(R.id.btn_B);
        btnC = (Button) findViewById(R.id.btn_C);
        btnD = (Button) findViewById(R.id.btn_D);
        txvTitle = (TextView) findViewById(R.id.txv_review_title);
        toolbar.setTitle("测试");
        setSupportActionBar(toolbar);
        initDate();
        reviewCount = 1;
        initView(reviewCount);
    }

    private void initDate() {
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        list = wordsDBManager.findWordbyReview("15");
        otherMeanList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            Words  words= wordsDBManager.getWorsById((int) (Math.random() * 100));
            otherMeanList.add(words.getMean());
        }
    }

    private void initView(int i) {
        Words words = list.get(i);
        txvTitle.setText(words.getWord());
        initButton(words.getMean());
    }

    private void initButton(String mean) {
        double buttonId = Math.random() * 4;
        int tNumber = (int) buttonId;
        switch (tNumber) {

            case 0:
                btnA.setText(mean);
                initOtherButton(tNumber);
                break;
            case 1:
                btnB.setText(mean);
                initOtherButton(tNumber);
                break;
            case 2:
                btnC.setText(mean);
                initOtherButton(tNumber);
                break;
            case 3:
                btnD.setText(mean);
                initOtherButton(tNumber);
                break;
            default:
                break;
        }
    }

    private void initOtherButton(int number) {
        btnA.setBackgroundColor(getResources().getColor(R.color.abcd));
        btnB.setBackgroundColor(getResources().getColor(R.color.abcd));
        btnC.setBackgroundColor(getResources().getColor(R.color.abcd));
        btnD.setBackgroundColor(getResources().getColor(R.color.abcd));
        switch (number) {
            case 0:
                btnB.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnC.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnD.setText(otherMeanList.get((int) (Math.random() * 40)));
                break;
            case 1:
                btnA.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnC.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnD.setText(otherMeanList.get((int) (Math.random() * 40)));
                break;
            case 2:
                btnA.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnB.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnD.setText(otherMeanList.get((int) (Math.random() * 40)));
                break;
            case 3:
                btnA.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnB.setText(otherMeanList.get((int) (Math.random() * 40)));
                btnC.setText(otherMeanList.get((int) (Math.random() * 40)));
                break;
            default:
                break;
        }
    }


    private String getMean(String word) {
        for (Words words : list) {
            if (words.getWord().equals(word)) {
                return words.getMean();
            }
        }
        return null;
    }

    public void checking(View view) {
        Button button = (Button) view;
        String bMean = button.getText().toString();
        String trueMean = getMean(this.txvTitle.getText().toString());
        if (bMean.equals(trueMean)) {
            button.setBackgroundColor(getResources().getColor(R.color.correct_true));
            this.correct = true;
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.correct_false));
            this.correct = false;
        }
    }

    public void next(View view) {
        if (correct) {

            if (reviewCount <= 10) {
                reviewCount++;
                initView(reviewCount);
                correct = false;
            } else {
                setDegree();
                Intent intent = new Intent(this.getApplicationContext(),ReturnReViewAcivity.class);
                startActivity(intent);
                finish();
            }
        }
        else {
            return;
        }
    }

    private void setDegree(){
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        for(Words words:list) {
            wordsDBManager.setDegreeById(Integer.parseInt(words.get_id()));
        }
    }
}
