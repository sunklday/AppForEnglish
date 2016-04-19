package com.jiai.sun.appforenglist.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.User;
import com.jiai.sun.appforenglist.domain.Words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LeadSelectWordsLibraryActivity extends AppCompatActivity {
    String  UserName = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_select_word_library);
        Intent intent = getIntent();
        UserName = intent.getStringExtra("userName");
    }


    class  AddDataTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream = getResources().openRawResource(R.raw.sijiword3);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
            String line;
            int i = 0;
            try {
                while (i < 500) {
                    i++;
                    line = bufferedReader.readLine();
                    String[] str = line.split("\\*", 3);
                    if (str.length < 2) {
                        continue;
                    }
                    Words words = new Words();
                    words.setWord(str[0]);
                    words.setMean(str[1]);
                    words.setExample(str[2]);
                    wordsDBManager.insertWords(words);
                    for (int j = 0; j < str.length; j++) {
                        System.out.println(str[j] + " " + j);
                    }
                }
            } catch (IOException e) {

            }
            return null;
        }
    }


    public void addWordsLibrary(View view){
        AddDataTask addDataTask = new AddDataTask();
        addDataTask.execute();
    }


    public void submitWordsLibrary(View view){
        User user = new User();
        user.setAmount(20);
        user.setUserName(UserName);
        user.setWordsLibrary("2");
        UserDBManager  userDBManager = new UserDBManager(getApplicationContext());
        userDBManager.addUser(user);
        //读取SharedPreferences中需要的数据
        SharedPreferences preferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);
        //实例化Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstUse", false);
        //提交修改
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
