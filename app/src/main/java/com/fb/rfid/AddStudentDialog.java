package com.fb.rfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_dialog);

        final EditText ed_name = findViewById(R.id.std_name);
        final EditText ed_id = findViewById(R.id.std_id);
        final EditText ed_sex = findViewById(R.id.std_sex);
        final EditText ed_phone = findViewById( R.id.std_phone);
        Button btn_commit = findViewById(R.id.sure);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String id = ed_id.getText().toString();
                String phone = ed_phone.getText().toString();
                String sex = ed_sex.getText().toString();

            }
        });

        Button btn_cancle = findViewById(R.id.dismiss);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
