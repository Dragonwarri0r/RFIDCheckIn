package com.fb.rfid.models;

import org.litepal.crud.DataSupport;

import java.util.List;

public class TodayStudent extends DataSupport {
    private List<Student> students;
    private String Date;
    private int HereStudents;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getHereStudents() {
        return HereStudents;
    }

    public void setHereStudents(int hereStudents) {
        HereStudents = hereStudents;
    }
}
