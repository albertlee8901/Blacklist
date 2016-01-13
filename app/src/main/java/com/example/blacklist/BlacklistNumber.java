package com.example.blacklist;

/**
 * Blacklist number javabean
 * Created by albertli on 1/11/2016.
 */
public class BlacklistNumber {
    private int _id;
    private String number;

    public BlacklistNumber(int _id, String number) {
        this._id = _id;
        this.number = number;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlacklistNumber{" +
                "_id=" + _id +
                ", number='" + number + '\'' +
                '}';
    }
}
