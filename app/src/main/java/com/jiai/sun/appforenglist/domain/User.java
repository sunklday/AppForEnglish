package com.jiai.sun.appforenglist.domain;

/**
 * Created by admin on 2016/4/18.
 */
public class User {
    private String _id;
    private String userName;
    private String wordsMark;
    private String wordsLibrary;
    private Integer amount;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWordsMark() {
        return wordsMark;
    }

    public void setWordsMark(String wordsMark) {
        this.wordsMark = wordsMark;
    }

    public String getWordsLibrary() {
        return wordsLibrary;
    }

    public void setWordsLibrary(String wordsLibrary) {
        this.wordsLibrary = wordsLibrary;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", userName='" + userName + '\'' +
                ", wordsMark='" + wordsMark + '\'' +
                ", wordsLibrary='" + wordsLibrary + '\'' +
                ", amount=" + amount +
                '}';
    }
}
