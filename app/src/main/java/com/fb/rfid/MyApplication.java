package com.fb.rfid;

import android.app.Application;

import com.fb.rfid.models.Student;

import java.util.List;

public class MyApplication extends Application {
    List<Student> students ;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
