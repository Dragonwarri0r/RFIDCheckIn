package com.fb.rfid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fb.rfid.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Create By FB
 * @description:
 * @date :2019/11/26 15:05
 */
public class CheckInAdapter extends RecyclerView.Adapter {

    private List<Student> students;
    private Context mcontext;
    private OnItemChildClickListener mOnItemChildClickListener;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag;
        TextView tvName;
        TextView tvTime;
        TextView tvSex;
        TextView tvId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.stu_tag);
            tvId = itemView.findViewById(R.id.stu_id);
            tvName = itemView.findViewById(R.id.stu_name);
            tvTime = itemView.findViewById(R.id.stu_time);
            tvSex = itemView.findViewById(R.id.stu_sex);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemChildClickListener != null){
                        mOnItemChildClickListener.onStudentClick(CheckInAdapter.this,getLayoutPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student student = students.get(position);



    }

    public CheckInAdapter (List<Student> studentList , Context context){
        students = studentList;
        mcontext = context;
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        mOnItemChildClickListener = listener;
    }

    public interface OnItemChildClickListener {
        void onStudentClick(CheckInAdapter adapter, int position);
    }
}
