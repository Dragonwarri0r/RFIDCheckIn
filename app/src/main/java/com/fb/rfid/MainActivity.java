package com.fb.rfid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.fb.rfid.Utils.NfcUtils;
import com.fb.rfid.models.Student;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends FragmentActivity implements AddFragment.OnFragmentInteractionListener {

    Fragment mAddFragment;
    Fragment mCheckInFragment;
    Fragment now;
    Fragment mWebFragment;
    FragmentTransaction mTransaction;
    MyApplication myApplication;
    List<Student> students ;
    Handler handler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (now != mCheckInFragment) {
                        mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        mTransaction.hide(now);
                        mTransaction.show(mCheckInFragment);
                        now = mCheckInFragment;
                        mTransaction.commit();
                    } else {
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.navigation_add:
                    if (now != mAddFragment) {
                        mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        if(mAddFragment == null){
                            mAddFragment = new AddFragment();
                            mTransaction.add(R.id.content,mAddFragment);
                        }
                        mTransaction.hide(now);
                        mTransaction.show(mAddFragment);
                        now = mAddFragment;
                        mTransaction.commit();
                    } else {
                        Toast.makeText(MainActivity.this, "dash", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.navigation_view:
                    if (now != mWebFragment) {
                        mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        if(mWebFragment == null){
                            mWebFragment = new SchoolFragment();
                            mTransaction.add(R.id.content,mWebFragment);
                        }
                        mTransaction.hide(now);
                        mTransaction.show(mWebFragment);
                        now = mWebFragment;
                        mTransaction.commit();
                    } else {
                        Toast.makeText(MainActivity.this, "view", Toast.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myApplication = (MyApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(MainActivity.this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        mCheckInFragment = new CheckInFragment();
        mTransaction.add(R.id.content, mCheckInFragment);
        now = mCheckInFragment;
        mTransaction.commit();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if(message.what == 1){
                    students = myApplication.getStudents();
                    mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    mTransaction.hide(now);
                    mTransaction.show(now);
                    mTransaction.commit();
//                    if(now == mAddFragment){
//                        mTransaction.remove(mAddFragment);
//                        mTransaction.add(R.id.content,mAddFragment);
//                    }else{
//                        mTransaction.remove(mAddFragment);
//                        mTransaction.add(R.id.content,mAddFragment);
//                        mTransaction.hide(mAddFragment);
//                    }
//                    now = mCheckInFragment;
//                    mTransaction.commit();
                }
                return true;
            }
        });

        NfcUtils nfcUtils = new NfcUtils(this);

    }

    @Override
    protected void onResume() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                myApplication.setStudents(DataSupport.findAll(Student.class));
                Log.e("fb", "doInBackground: "+DataSupport.findAll(Student.class).toString() );
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                return 1;
            }
        }.execute(10);
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
