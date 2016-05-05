package com.jiai.sun.appforenglist.domain;

/**
 * Created by admin on 2016/4/18.
 */
public class Words {
    private String _id;
    private String word;
    private String mean;
    private String example;
    private String degree;
    private String category;
    private String star;
    private String learndate;
    public String getLearndate() {
        return learndate;
    }

    public void setLearndate(String learndate) {
        this.learndate = learndate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Words{" +
                "_id='" + _id + '\'' +
                ", word='" + word + '\'' +
                ", mean='" + mean + '\'' +
                ", example='" + example + '\'' +
                ", degree='" + degree + '\'' +
                ", category='" + category + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
