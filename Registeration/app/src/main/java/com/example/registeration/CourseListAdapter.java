package com.example.registeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;

    public CourseListAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.course, null);
        TextView courseGrade = v.findViewById(R.id.courseGrade);
        TextView courseTitle = v.findViewById(R.id.courseTitle);
        TextView courseCredit = v.findViewById(R.id.courseCredit);
        TextView courseDivide = v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = v.findViewById(R.id.courseProfessor);
        TextView courseTime = v.findViewById(R.id.courseTime);

        if(courseList.get(position).getCourseGrade().equals("unlimit") || courseList.get(position).getCourseGrade().equals("")){
            courseGrade.setText("All Level");
        }else {
            courseGrade.setText(courseList.get(position).getCourseGrade() + "Level");
        }
        courseGrade.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText(courseList.get(position).getCourseCredit() + "Grade");
        courseDivide.setText(courseList.get(position).getCourseDivide() + "Divide");


        if(courseList.get(position).getCoursePersonnel() == 0){
            coursePersonnel.setText("Unlimit");
        }else{
            coursePersonnel.setText("Maximum : " + courseList.get(position).getCoursePersonnel());
        }

        courseProfessor.setText(courseList.get(position).getCourseProfessor() );
        courseTime.setText(courseList.get(position).getCourseTime());


        v.setTag(courseList.get(position).getCourseID());
        return v;

    }
}
