package com.jiai.sun.appforenglist.domain;

import java.util.Date;

/**
 * Created by admin on 2016/4/18.
 */
public class Record {
    private String _id;
    private String userName;
    private Date learnDate;
    private Integer easyWordsCount;
    private Integer starWordsCount;
    private Integer difficultWordsCount;

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

    public Date getLearnDate() {
        return learnDate;
    }

    public void setLearnDate(Date learnDate) {
        this.learnDate = learnDate;
    }

    public Integer getEasyWordsCount() {
        return easyWordsCount;
    }

    public void setEasyWordsCount(Integer easyWordsCount) {
        this.easyWordsCount = easyWordsCount;
    }

    public Integer getStarWordsCount() {
        return starWordsCount;
    }

    public void setStarWordsCount(Integer starWordsCount) {
        this.starWordsCount = starWordsCount;
    }

    public Integer getDifficultWordsCount() {
        return difficultWordsCount;
    }

    public void setDifficultWordsCount(Integer difficultWordsCount) {
        this.difficultWordsCount = difficultWordsCount;
    }

    @Override
    public String toString() {
        return "Record{" +
                "_id='" + _id + '\'' +
                ", userName='" + userName + '\'' +
                ", learnDate=" + learnDate +
                ", easyWordsCount=" + easyWordsCount +
                ", starWordsCount=" + starWordsCount +
                ", difficultWordsCount=" + difficultWordsCount +
                '}';
    }
}
