package com.jiai.sun.appforenglist.Activity;

import android.os.Environment;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.tool.Download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class WordsLibraryActivity extends AppCompatActivity {
    private Button button;
    private static final String TAG = "MainActivity";
    String downloadUrl = "http://192.168.191.1:8080/download";
    String picUrl ="http://img01.mifile.cn/images/accs/xmjsb_11.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_library);
        button = (Button) findViewById(R.id.download);

    }

    public void download(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                download();
            }
        }).start();

    }
    //下载具体操作
    private void download() {
        try {
            URL url = new URL(downloadUrl);
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
            String fileName = dirName + "1.txt";
            File file1 = new File(fileName);
            if (file1.exists()) {
                file1.delete();
            }
            //创建字节流
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(fileName);
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
}
