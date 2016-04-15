package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiai.sun.appforenglist.R;

public class ReadyToLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_learn);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.readtolearn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LearnEnglishActivity.class);
                startActivity(intent);
            }
        });
    }
}
