package com.fb.rfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fb.rfid.Utils.NfcUtils;
import com.fb.rfid.Utils.TimeUtils;
import com.fb.rfid.models.Student;
import com.fb.rfid.models.TodayStudent;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.fb.rfid.Utils.NfcUtils.mPendingIntent;

public class CheckIngActivity extends AppCompatActivity {

    String idc;
    NfcAdapter mNfcAdapter;
    Button btnStop;
    TextView tv_welcome;
    EditText et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ing);

        final NfcUtils mNfc = new NfcUtils(this);
        mNfcAdapter = NfcUtils.mNfcAdapter;
        et_id = findViewById(R.id.et_id_check);

        btnStop = findViewById(R.id.stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_id.getText().toString().equals("")){
                    idc = et_id.getText().toString();
                    checkin(idc);
                }else{
                    finish();
                }
            }
        });
        tv_welcome = findViewById(R.id.welcome);

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
            idc = NfcUtils.readNFCId(intent);
            checkin(idc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkin(String idc){
        List<Student> students = DataSupport.select("idc","name").where("idc = ?", idc).find(Student.class);
        if(students!= null && students.size()>0){
            String name = students.get(0).getName();
            tv_welcome.setText("Welcome "+name+" !");
            String time = new TimeUtils().getTime();
            String date = new TimeUtils().getDate();
            ContentValues contentValues = new ContentValues();
            contentValues.put("isHere",true);
            contentValues.put("lastTime",time);
            DataSupport.updateAll(Student.class,contentValues,"idc = ? ",idc);
        }
    }
}
