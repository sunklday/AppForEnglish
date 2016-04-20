package com.jiai.sun.appforenglist.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jiai.sun.appforenglist.domain.Record;
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


    public List<Record> findRecordByUserName(String userName){
        List<Record> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Record record = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] name = {userName};
            cursor = db.query("record", null, "user_name=?", name, null, null, null);
            while (cursor.moveToNext()) {
                record = new Record();
                record.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                record.setLearnDate(cursor.getString(cursor.getColumnIndex("learn_date")));
                record.setWordsCount(cursor.getString(cursor.getColumnIndex("words_count")));
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
}
