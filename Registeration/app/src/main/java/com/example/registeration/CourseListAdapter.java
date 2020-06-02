package com.example.registeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;

    public CourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
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
        if(courseList.get(position).getCourseProfessor().equals("")){
            courseProfessor.setText("Individual Research");
        }else {
            courseProfessor.setText(courseList.get(position).getCourseProfessor() + "Professor");
        }
        courseProfessor.setText(courseList.get(position).getCourseProfessor() );
        courseTime.setText(courseList.get(position).getCourseTime());


        v.setTag(courseList.get(position).getCourseID());

        Button addButton = v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("=============1=====");
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            System.out.println("=============1=====");
                            if (success) {
                                System.out.println("=============2=====");
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                AlertDialog dialog = builder.setMessage("Course Added")
                                        .setPositiveButton("OK", null)
                                        .create();

                                dialog.show();

                            } else {
                                System.out.println("=============3=====");
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                AlertDialog dialog = builder.setMessage("Course already exist!!")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();

                            }
                        } catch (Exception e) {
                            System.out.println("=============4=====");
                            e.printStackTrace();
                        }
                    }
                };
                AddRequest addRequest = new AddRequest(userID, courseList.get(position).getCourseID() + "",responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getContext());
                queue.add(addRequest);
            }
        });
        return v;

    }
}
