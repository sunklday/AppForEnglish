package com.jiai.sun.appforenglist.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.jiai.sun.appforenglist.R;

public class StartAnimationActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;// 延迟六秒
    //是否是第一次使用
    private boolean isFirstUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);

        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR); //全屏

        new Handler().postDelayed(new Runnable() {
            public void run() {

                //读取SharedPreferences中需要的数据
                SharedPreferences preferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);

                isFirstUse = preferences.getBoolean("isFirstUse", true);
                if(isFirstUse){
                    startActivity(new Intent(getApplicationContext(),LeadSubmitUserNameActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
