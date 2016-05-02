package com.jiai.sun.appforenglist.biz;

import android.content.Context;

import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.List;

/**
 * Created by admin on 2016/4/19.
 */
public class WordsBiz {
    private WordsDBManager wordsDBManager;
    private Context context;
    public WordsBiz(Context context){
        this.context = context;
        wordsDBManager = new WordsDBManager(context);
    }

    public List<Words> getReviewWords(int count){


        return null;
    }
}
