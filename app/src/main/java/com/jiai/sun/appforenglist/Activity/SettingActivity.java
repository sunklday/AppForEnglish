package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.R;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton rb5;
    private RadioButton rb10;
    private RadioButton rb20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb5 = (RadioButton) findViewById(R.id.rb_5);
        rb10 = (RadioButton) findViewById(R.id.rb_10);
        rb20 = (RadioButton) findViewById(R.id.rb_20);
        UserDBManager userDBManager = new UserDBManager(getApplicationContext());

        Integer amount =userDBManager.findUserById(1).getAmount();
        switch (amount){
            case 5:
                rb5.setChecked(true);
                break;
            case 10:
                rb10.setChecked(true);
                break;
            case 20:
                rb20.setChecked(true);
                break;
            default:
                rb5.setChecked(true);
                break;
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_5:
                        break;
                    case R.id.rb_10:
                        break;
                    case R.id.rb_20:
                        break;
                }
            }
        });
    }

    public void setWordsLibrary(View view){
        Intent intent = new Intent(this.getApplicationContext(),WordsLibraryActivity.class);
        startActivity(intent);
    }
}
