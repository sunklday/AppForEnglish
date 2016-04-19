package com.jiai.sun.appforenglist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2016/4/18.
 */
public class WordsDBManager {
    DBOpenHelper dbOpenHelper;
    Context context;
  /*  private String _id;
    private String word;
    private String mean;
    private String example;
    private String degree;
    private String category;
    private String star;*/
    public WordsDBManager(Context context){
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public List<Words> getWorsByAmount(Integer Amount){
        List<Words> list = new ArrayList<>();
         for(int i = 1;i<=Amount;i++){
             double id=Math.random()*100;//100是id计数，以后要改，加一个wordlibrary表。
             Words words = getWorsById((int)id);
             list.add(words);
         }
        return list;
    }

    public Words getWorsById(Integer id) {
        List<Words> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Words words = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _id = {String.valueOf(id)};
            cursor = db.query("words", null, "_id=?", _id, null, null, null);
            while (cursor.moveToNext()) {
                words = new Words();
                words.set_id(Integer.toString(id));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setMean(cursor.getString(cursor.getColumnIndex("mean")));
                words.setExample(cursor.getString(cursor.getColumnIndex("example")));
                words.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
                words.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                words.setStar(cursor.getString(cursor.getColumnIndex("star")));
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return words;
    }


    public void insertWords(Words words){
        SQLiteDatabase db = null;

        try {
            long newRowId;
            db = dbOpenHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("word", words.getWord());
            cv.put("example", words.getExample());
            cv.put("degree", words.getDegree());
            cv.put("mean", words.getMean());
            cv.put("category", words.getCategory());
            cv.put("star",words.getStar());
            db.insert("words", null, cv);
        } catch (Exception e) {

        } finally {
            db.close();
        }
    }
}