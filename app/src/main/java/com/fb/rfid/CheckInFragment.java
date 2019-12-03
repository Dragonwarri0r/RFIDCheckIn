package com.fb.rfid;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CheckInFragment extends Fragment {

    RecyclerView recyclerView;
    View rootView;
    FloatingActionButton fab_start;
    Button btn_stop;

    private CheckInViewModel mViewModel;

    public static CheckInFragment newInstance() {
        return new CheckInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.check_in_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.cif_rv);

        fab_start = rootView.findViewById(R.id.fab_start);
        fab_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"start",Toast.LENGTH_SHORT).show();
                fab_start.hide();
                btn_stop.setVisibility(View.VISIBLE);
            }
        });
        btn_stop = rootView.findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"stop",Toast.LENGTH_SHORT).show();
                btn_stop.setVisibility(View.GONE);
                fab_start.show();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CheckInViewModel.class);
        // TODO: Use the ViewModel
    }

}
