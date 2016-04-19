package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jiai.sun.appforenglist.R;

public class LeadSubmitUserNameActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        editText = (EditText) findViewById(R.id.ett_leadActivityName);
    }

    public void submitUserName(View view){
        String userName = editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(),LeadSelectWordsLibraryActivity.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
        finish();
    }
}
