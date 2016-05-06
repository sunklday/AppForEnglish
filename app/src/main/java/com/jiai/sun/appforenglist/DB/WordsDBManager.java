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
         for(int i = 1;i<=Amount;){
             double id=Math.random()*200;//100是id计数，以后要改，加一个wordlibrary表。
             Words words = new Words();
             words = getWorsById((int)id);
             if(words.getDegree()!=null){
                 continue;
             }
             i++;
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

    public void updateWord(Words words){

        SQLiteDatabase db = null;

        try {
            db = dbOpenHelper.getWritableDatabase();
            String _id = "_id=?";
            String[] id = {words.get_id()};
            ContentValues cv = new ContentValues();
            cv.put("word", words.getWord());
            cv.put("example", words.getExample());
            cv.put("degree", words.getDegree());
            cv.put("mean", words.getMean());
            cv.put("category", words.getCategory());
            cv.put("star",words.getStar());
            cv.put("learn_date",words.getLearndate());
            db.update("words",cv,_id,id);
        } catch (Exception e) {

        } finally {
            db.close();
        }
    }

    public void setDegreeById(Integer wordsId){

        SQLiteDatabase db = null;
        Integer degree = Integer.parseInt(getWorsById(wordsId).getDegree())+1;
        try {
            db = dbOpenHelper.getWritableDatabase();
            String _id = "_id=?";
            String[] id = {wordsId.toString()};
            ContentValues cv = new ContentValues();
            cv.put("degree", degree+"");
            db.update("words",cv,_id,id);
        } catch (Exception e) {

        } finally {
            db.close();
        }
    }
    public List<Words> findWordbyReview(String count) {

        SQLiteDatabase db = null;
        List<Words> list = new ArrayList<>();
        Words words = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _degree = {"0"};
            cursor = db.query("words", null, "degree >=?", _degree, null, null, "degree ASC", count);
            while (cursor.moveToNext()) {
                words = new Words();
                words.set_id(Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setMean(cursor.getString(cursor.getColumnIndex("mean")));
                words.setExample(cursor.getString(cursor.getColumnIndex("example")));
                words.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
                words.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                words.setStar(cursor.getString(cursor.getColumnIndex("star")));
                list.add(words);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return list;
    }
    public Integer getCountBylearn(){
        SQLiteDatabase db = null;

        Integer count = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _degree = {"0"};

            cursor = db.rawQuery("SELECT COUNT(*) FROM words where degree>?",_degree);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }

        return count;
    }
    public Integer getCountByOk(){
        SQLiteDatabase db = null;

        Integer count = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _degree = {"1"};

            cursor = db.rawQuery("SELECT COUNT(*) FROM words where degree>?",_degree);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }

        return count;
    }
    public Integer getCount(){
        SQLiteDatabase db = null;

        Integer count = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _degree = {"3"};

            cursor = db.rawQuery("SELECT COUNT(*) FROM words",null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }

        return count;
    }
    public void delectall(){
        SQLiteDatabase db = null;


        try {
            db = dbOpenHelper.getWritableDatabase();
            db.delete("words", null, null);
        } catch (Exception e) {

        } finally {
            db.close();
        }

    }
    /**
     * 找新单词
     */
    public List<Words> getNewWorsByAmount(Integer Amount){
        List<Words> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String[] _degree = {"0"};
        Words words;
        try {
            db = dbOpenHelper.getReadableDatabase();
            cursor = db.query("words", null, "degree =?", _degree, null, null, "_id ASC",Amount+"");
            while (cursor.moveToNext()) {
                words = new Words();
                words.set_id(Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setMean(cursor.getString(cursor.getColumnIndex("mean")));
                words.setExample(cursor.getString(cursor.getColumnIndex("example")));
                words.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
                words.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                words.setStar(cursor.getString(cursor.getColumnIndex("star")));
                list.add(words);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return list;
    }
    public List<Words> getOldWorsByTime(){
        List<Words> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String[] _degree = {"1"};
        Words words;
        try {
            db = dbOpenHelper.getReadableDatabase();
            cursor = db.query("words", null, "degree =?", _degree, null, null, "learn_date ASC","5");
            while (cursor.moveToNext()) {
                words = new Words();
                words.set_id(Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setMean(cursor.getString(cursor.getColumnIndex("mean")));
                words.setExample(cursor.getString(cursor.getColumnIndex("example")));
                words.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
                words.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                words.setStar(cursor.getString(cursor.getColumnIndex("star")));
                list.add(words);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return list;
    }
    public List<Words> getOldWorsByTimeDesc(){
        List<Words> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String[] _degree = {"1"};
        Words words;
        try {
            db = dbOpenHelper.getReadableDatabase();
            cursor = db.query("words", null, "degree =?", _degree, null, null, "learn_date DESC","5");
            while (cursor.moveToNext()) {
                words = new Words();
                words.set_id(Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setMean(cursor.getString(cursor.getColumnIndex("mean")));
                words.setExample(cursor.getString(cursor.getColumnIndex("example")));
                words.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
                words.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                words.setStar(cursor.getString(cursor.getColumnIndex("star")));
                list.add(words);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return list;
    }
}
