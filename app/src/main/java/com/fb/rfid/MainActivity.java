package com.fb.rfid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.fb.rfid.Utils.NfcUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements AddFragment.OnFragmentInteractionListener {

    Fragment mAddFragment;
    Fragment mCheckInFragment;
    Fragment now;
    FragmentTransaction mTransaction;

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
                        mTransaction.hide(now);
                        mTransaction.show(mAddFragment);
                        now = mAddFragment;
                        mTransaction.commit();
                    } else {
                        Toast.makeText(MainActivity.this, "dash", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.navigation_view:
                    Toast.makeText(MainActivity.this, "view", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        mAddFragment = new AddFragment();
        mCheckInFragment = new CheckInFragment();
        mTransaction.add(R.id.content, mAddFragment);
        mTransaction.add(R.id.content, mCheckInFragment);
        now = mCheckInFragment;
        mTransaction.hide(mAddFragment);
        mTransaction.commit();

        NfcUtils nfcUtils = new NfcUtils(this);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
