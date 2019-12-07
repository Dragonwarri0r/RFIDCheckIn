package com.fb.rfid;

import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.rfid.models.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.crud.DataSupport;

import java.util.List;

public class CheckInFragment extends Fragment {

    RecyclerView recyclerView;
    CheckInAdapter checkInAdapter;
    View rootView;
    FloatingActionButton fab_start;
    Button newDay;
    MyApplication myApplication;

    int here = 0;
    int allStudents = 0;
    List<Student> students;

    private CheckInViewModel mViewModel;

    TextView tv_here;
    TextView tv_all;

    public static CheckInFragment newInstance() {
        return new CheckInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.check_in_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.cif_rv);

        myApplication = (MyApplication) getActivity().getApplication();


        tv_here = rootView.findViewById(R.id.ci_child_here);
        tv_all = rootView.findViewById(R.id.ci_child_shoud_here);
        newDay = rootView.findViewById(R.id.new_day);

        newDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("isHere",false);
                DataSupport.updateAll(Student.class,contentValues);
                Toast.makeText(getContext(), "请重启app", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        });


        fab_start = rootView.findViewById(R.id.fab_start);
        fab_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "start", Toast.LENGTH_SHORT).show();
                fab_start.hide();
                Intent intent = new Intent(getContext(),CheckIngActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CheckInViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        if (hidden) {//相当于Fragment的onResume，为true时，Fragment已经可见 

        } else {//相当于Fragment的onPause，为false时，Fragment不可见
            students = myApplication.students;
            checkInAdapter = new CheckInAdapter(students ,getContext());
            Log.e("fbi", "onCreateView: "+myApplication.students );
            recyclerView.setAdapter(checkInAdapter);
            checkInAdapter.notifyDataSetChanged();
            if( students == null){
                return ;
            }
            allStudents = students.size();
            here = getHereStudents();
            tv_here.setText(here + "");
            tv_all.setText(allStudents + "");

        }
    }

    @Override
    public void onResume() {
        fab_start.show();
        super.onResume();
    }

    public int getHereStudents() {
        int num = 0;
        if (students == null) {
            return num;
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isHere()) {
                num++;
            }
        }
        return num;
    }

}
