package com.jiai.sun.appforenglist.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by admin on 2016/4/18.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /**
     * 常规字段
     * text integer 数据库数据类型
     * 逗号是分隔符
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /**
     * 建立words表sql语句
     */
    private static final String SQL_CREATE_WORDS =
            "CREATE TABLE " + WORDSTABLE.TABLE_NAME + " (" +
                    WORDSTABLE._ID + " INTEGER PRIMARY KEY," +
                    WORDSTABLE.COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +
                    WORDSTABLE.COLUMN_NAME_MEAN + TEXT_TYPE + COMMA_SEP +
                    WORDSTABLE.COLUMN_NAME_EXAMPLE + TEXT_TYPE + COMMA_SEP +
                    WORDSTABLE.COLUMN_NAME_DEGREE + TEXT_TYPE + COMMA_SEP +
                    WORDSTABLE.COLUMN_NAME_STAR + TEXT_TYPE + COMMA_SEP +
                    WORDSTABLE.COLUMN_NAME_CATEGORY + TEXT_TYPE +
                    " )";
    /**
     * 建立user表sql语句
     */
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + USERTABLE.TABLE_NAME + " (" +
                    USERTABLE._ID + " INTEGER PRIMARY KEY," +
                    USERTABLE.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_WORDSMARK + TEXT_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_WRODSLIBRARY + TEXT_TYPE +
                    " )";

    /**
     * 建立record表sql语句
     */
    private static final String SQL_CREATE_RECORD =
            "CREATE TABLE " + RECORDTABLE.TABLE_NAME + " (" +
                    RECORDTABLE._ID + " INTEGER PRIMARY KEY," +
                    RECORDTABLE.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    RECORDTABLE.COLUMN_NAME_LEARNDATE + TEXT_TYPE + COMMA_SEP +
                    RECORDTABLE.COLUMN_NAME_EASYWORDSCOUNT + INTEGER_TYPE + COMMA_SEP +
                    RECORDTABLE.COLUMN_NAME_DIFFICULTWORDSCOUNT + INTEGER_TYPE + COMMA_SEP +
                    RECORDTABLE.COLUMN_NAME_STARWORDSCOUNT + INTEGER_TYPE +
                    " )";

    private static final String SQL_DELETE_WORDS =
            "DROP TABLE IF EXISTS " + WORDSTABLE.TABLE_NAME;

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + USERTABLE.TABLE_NAME;
    private static final String SQL_DELETE_RECORD =
            "DROP TABLE IF EXISTS " + RECORDTABLE.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app_for_english.db";



    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WORDS);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_WORDS);
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_RECORD);

        onCreate(db);
    }

    /**
     * 定义words表结构
     * user 用户名字
     * date 记录日期
     */
    public static abstract class WORDSTABLE implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEAN = "mean";
        public static final String COLUMN_NAME_EXAMPLE = "example";
        public static final String COLUMN_NAME_DEGREE = "degree";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_STAR = "star";

    }
    /**
     * 定义user表结构
     * user 用户名字
     * date 记录日期
     */
    public static abstract class USERTABLE implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "user_name";
        public static final String COLUMN_NAME_WORDSMARK = "words_mark";
        public static final String COLUMN_NAME_WRODSLIBRARY = "words_library";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
    /**
     * 定义Record表结构
     * user 用户名字
     * date 记录日期
     */
    public static abstract class RECORDTABLE implements BaseColumns {
        public static final String TABLE_NAME = "record";
        public static final String COLUMN_NAME_USERNAME = "user_name";
        public static final String COLUMN_NAME_LEARNDATE = "learn_date";
        public static final String COLUMN_NAME_EASYWORDSCOUNT = "easy_words_count";
        public static final String COLUMN_NAME_STARWORDSCOUNT = "star_words_count";
        public static final String COLUMN_NAME_DIFFICULTWORDSCOUNT = "difficult_words_count";

    }
}
