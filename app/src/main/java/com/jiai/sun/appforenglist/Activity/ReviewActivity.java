package com.jiai.sun.appforenglist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jiai.sun.appforenglist.R;

public class ReviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_review);
        toolbar.setTitle("测试");
        setSupportActionBar(toolbar);
    }
}
