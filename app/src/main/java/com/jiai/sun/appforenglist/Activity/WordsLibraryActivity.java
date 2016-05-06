package com.jiai.sun.appforenglist.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class WordsLibraryActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String downloadUrl = "http://192.168.191.1:8080/download/";
    String picUrl ="http://img01.mifile.cn/images/accs/xmjsb_11.jpg";
    String saveFileName;
    String filename;
    private Button mbutton;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_library);
        mbutton = (Button) findViewById(R.id.btn_selectWordsLibrary);

    }

    public void addWords(View view){
       context = this.getApplicationContext();
        if(saveFileName ==null){

            CharSequence text = "请选择词库!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            AddDataTask addDataTask = new AddDataTask();
            addDataTask.execute();
            CharSequence text = "添加完成!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            finish();
        }

    }


    public void download(View view) {
        Button button = (Button) view;
        filename = button.getText().toString();
        button.setBackgroundColor(getResources().getColor(R.color.correct_true));
        mbutton.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                download(filename);
            }
        }).start();


    }
    //下载具体操作
    private void download(String filename) {
        try {
            URL url = new URL(downloadUrl+filename+".txt");
            //打开连接
            URLConnection conn = url.openConnection();
            //打开输入流
            InputStream is = conn.getInputStream();
            //获得长度
            int contentLength = conn.getContentLength();
            Log.e(TAG, "contentLength = " + contentLength);
            //创建文件夹 MyDownLoad，在存储卡下
            String dirName = Environment.getExternalStorageDirectory() + "/MyDownLoad/";
            File file = new File(dirName);
            //不存在创建
            if (!file.exists()) {
                file.mkdir();
            }
            //下载后的文件名
            saveFileName = dirName + filename+".txt";
            File file1 = new File(saveFileName);
            if (file1.exists()) {
                file1.delete();
            }
            //创建字节流
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(saveFileName);
            //写数据
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            //完成后关闭流
            Log.e(TAG, "download-finish");
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class  AddDataTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(saveFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
            wordsDBManager.delectall();
            UserDBManager userDBManager = new UserDBManager(context);
            userDBManager.setWordsLibrary(1,filename);
            String line;
            int i = 0;
            try {
                while (i < 200) {
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
                    words.setCategory(filename);
                    words.setDegree("0");
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
}
