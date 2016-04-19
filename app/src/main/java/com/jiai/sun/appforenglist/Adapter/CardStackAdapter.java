package com.jiai.sun.appforenglist.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.List;

/**
 * Created by admin on 2016/4/18.
 */
public class CardStackAdapter extends ArrayAdapter<String> {

    List<Words> wordsList;
    public CardStackAdapter(Context context, List<Words> wordsList) {
        super(context, R.layout.learn_english_card_content);
        this.wordsList = wordsList;
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        TextView numberTextView = (TextView)(contentView.findViewById(R.id.txv_learnEnglishNumber));
        TextView wordTextView = (TextView)(contentView.findViewById(R.id.txv_learnEnglishWord));
        TextView meanTextView = (TextView)(contentView.findViewById(R.id.txv_learnEnglishMean));
        TextView exampleTextView = (TextView)(contentView.findViewById(R.id.txv_learnEnglishExample));
        numberTextView.setText(getItem(position));
        wordTextView.setText(wordsList.get(position).getWord());
        meanTextView.setText(wordsList.get(position).getMean());
        exampleTextView.setText(wordsList.get(position).getExample());
        return contentView;
    }

}
