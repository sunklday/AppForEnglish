package com.jiai.sun.appforenglist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jiai.sun.appforenglist.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/4/18.
 */
public class UserDBManager {
    DBOpenHelper dbOpenHelper;
    Context context;

  /*  private String _id;
    private String userName;
    private String wordsMark;
    private String wordsLibrary;
    private Integer amount;*/

    public UserDBManager(Context context){
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
    }
    public void addUser(User user){
        SQLiteDatabase db = null;
        try {
            db = dbOpenHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("user_name",user.getUserName());
            cv.put("words_mark",user.getWordsMark());
            cv.put("words_library",user.getWordsLibrary());
            cv.put("amount",user.getAmount());
            db.insert("user",null,cv);
        }catch (Exception e){

        }finally {
            db.close();
        }
    }

    public User findUserById(Integer id) {
        User user = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            String[] _id = {id+""};
            cursor = db.query("user", null, "_id=?", _id, null, null, null);
            while (cursor.moveToNext()){
                user = new User();
                user.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setWordsMark(cursor.getString(cursor.getColumnIndex("words_mark")));
                user.setWordsLibrary(cursor.getString(cursor.getColumnIndex("words_library")));
                user.setAmount(cursor.getInt(cursor.getColumnIndex("amount")));
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }
        return user;
    }

 /*   public List<User> findUserById(Integer id){
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            cursor = db.query(WORDTABLE.TABLE_NAME, null, null, null, null, null, " _id desc", limit);
            while (cursor.moveToNext()){
                User user = new User();
                user.se
            }
        }catch (Exception e){

        }finally {
            db.close();
        }
    }*/
}
