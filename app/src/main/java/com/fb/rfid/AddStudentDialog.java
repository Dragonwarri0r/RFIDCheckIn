package com.fb.rfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fb.rfid.Utils.NfcUtils;
import com.fb.rfid.models.Student;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.fb.rfid.Utils.NfcUtils.mPendingIntent;

public class AddStudentDialog extends AppCompatActivity {

    String str;
    NfcAdapter mNfcAdapter;
    EditText ed_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_dialog);

        final NfcUtils mNfc = new NfcUtils(this);
        mNfcAdapter = NfcUtils.mNfcAdapter;

        final EditText ed_name = findViewById(R.id.std_name);
        ed_id = findViewById(R.id.std_id);
        final EditText ed_sex = findViewById(R.id.std_sex);
        final EditText ed_phone = findViewById(R.id.std_phone);
        Button btn_commit = findViewById(R.id.sure);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ed_id.getText().toString();
                if(id.equals("")){
                    Toast.makeText(AddStudentDialog.this, "id can`t Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Student> temp = DataSupport.select("idc").where("idc = ?", id).find(Student.class);
                if (!temp.isEmpty()) {
                    Toast.makeText(AddStudentDialog.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Student student = new Student();
                    student.setName(ed_name.getText().toString());
                    student.setIdc(ed_id.getText().toString());
                    student.setPhone(ed_phone.getText().toString());
                    String sex = ed_sex.getText().toString();
                    if (sex.equals("男")) {
                        student.setMale(true);
                    } else {
                        student.setMale(false);
                    }
                    Toast.makeText(AddStudentDialog.this, student.getName() + student.getIdc() + student.save(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

//        Button btn_read = findViewById(R.id.read);
//        btn_read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                ed_id.setText(str);
//            }
//        });

        Button btn_cancle = findViewById(R.id.dismiss);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);//打开前台发布系统，使页面优于其它nfc处理.当检测到一个Tag标签就会执行mPendingItent
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //当该Activity接收到NFC标签时，运行该方法
        //调用工具方法，读取NFC数据
        try {
            str = NfcUtils.readNFCId(intent);
            ed_id.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
