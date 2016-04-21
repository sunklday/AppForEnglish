package com.jiai.sun.appforenglist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jiai.sun.appforenglist.domain.Record;
import com.jiai.sun.appforenglist.domain.User;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/4/18.
 */
public class RecordDBManager {

    DBOpenHelper dbOpenHelper;
    Context context;



    public RecordDBManager(Context context){
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
    }


    public List<Record> findRecordById(Integer userId){
        List<Record> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Record record = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] user_id = {String.valueOf(userId)};
            cursor = db.query("record", null, "user_id=?", user_id, null, null, null);
            while (cursor.moveToNext()) {
                record = new Record();
                record.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                record.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                record.setLearnDate(cursor.getString(cursor.getColumnIndex("learn_date")));
                record.setWordsCount(cursor.getInt(cursor.getColumnIndex("words_count")));
                record.setStarWordsCount(cursor.getInt(cursor.getColumnIndex("star_words_count")));
                record.setDifficultWordsCount(cursor.getInt(cursor.getColumnIndex("difficult_words_count")));
                record.setEasyWordsCount(cursor.getInt(cursor.getColumnIndex("easy_words_count")));
                list.add(record);
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return list;
    }
/*
   public static final String COLUMN_NAME_USERNAME = "user_name";
        public static final String COLUMN_NAME_LEARNDATE = "learn_date";
        public static final String COLUMN_NAME_EASYWORDSCOUNT = "easy_words_count";
        public static final String COLUMN_NAME_STARWORDSCOUNT = "star_words_count";
        public static final String COLUMN_NAME_DIFFICULTWORDSCOUNT = "difficult_words_count";
 */
    public void addRecord(Record record){
            SQLiteDatabase db = null;
            try {
                db = dbOpenHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("user_id",record.getUserId());
                cv.put("learn_date",record.getLearnDate());
                cv.put("easy_words_count",record.getEasyWordsCount());
                cv.put("star_words_count",record.getStarWordsCount());
                cv.put("difficult_words_count",record.getDifficultWordsCount());
                cv.put("words_count",record.getWordsCount());
                db.insert("record",null,cv);
            }catch (Exception e){

            }finally {
                db.close();
            }
    }
}
