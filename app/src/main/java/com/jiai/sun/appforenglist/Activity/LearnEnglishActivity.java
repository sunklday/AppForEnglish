package com.jiai.sun.appforenglist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jiai.sun.appforenglist.Adapter.CardStackAdapter;
import com.jiai.sun.appforenglist.R;
import com.wenchao.cardstack.CardStack;

public class LearnEnglishActivity extends AppCompatActivity {
    private Button button;

    private CardStack mCardStack;
    private CardStackAdapter mCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_english);
        mCardStack = (CardStack)findViewById(R.id.csk_englishCard);

        mCardStack.setContentResource(R.layout.learn_english_card_content);
        mCardStack.setStackMargin(20);

        mCardAdapter = new CardStackAdapter(getApplicationContext());
        mCardAdapter.add("test1");
        mCardAdapter.add("test2");
        mCardAdapter.add("test3");
        mCardAdapter.add("test4");
        mCardAdapter.add("test5");
        LearnEnglishCardStackListener myListener = new LearnEnglishCardStackListener(this);
        mCardStack.setListener(myListener);
        mCardStack.setAdapter(mCardAdapter);

        if(mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }
}
