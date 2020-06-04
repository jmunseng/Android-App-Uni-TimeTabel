package com.example.registeration;

import android.content.Context;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private Schedule schedule = new Schedule();
    private List<Integer> courseIDList;

    public CourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
        schedule = new Schedule();
        courseIDList = new ArrayList<Integer>();
        new BackgroundTask().execute();
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
        View v = View.inflate(context, R.layout.course, null);
        TextView courseGrade = v.findViewById(R.id.courseGrade);
        TextView courseTitle = v.findViewById(R.id.courseTitle);
        TextView courseCredit = v.findViewById(R.id.courseCredit);
        TextView courseDivide = v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = v.findViewById(R.id.courseProfessor);
        TextView courseTime = v.findViewById(R.id.courseTime);

        if (courseList.get(position).getCourseGrade().equals("unlimit") || courseList.get(position).getCourseGrade().equals("")) {
            courseGrade.setText("All Level");
        } else {
            courseGrade.setText(courseList.get(position).getCourseGrade() + "Level");
        }
        courseGrade.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText(courseList.get(position).getCourseCredit() + "Grade");
        courseDivide.setText(courseList.get(position).getCourseDivide() + "Divide");


        if (courseList.get(position).getCoursePersonnel() == 0) {
            coursePersonnel.setText("Unlimit");
        } else {
            coursePersonnel.setText("Maximum : " + courseList.get(position).getCoursePersonnel());
        }
        if (courseList.get(position).getCourseProfessor().equals("")) {
            courseProfessor.setText("Individual Research");
        } else {
            courseProfessor.setText(courseList.get(position).getCourseProfessor() + "Professor");
        }
        courseProfessor.setText(courseList.get(position).getCourseProfessor());
        courseTime.setText(courseList.get(position).getCourseTime());


        v.setTag(courseList.get(position).getCourseID());

        Button addButton = v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean validate = false;
                validate = schedule.validate(courseList.get(position).getCourseTime());

                if (!already(courseIDList, courseList.get(position).getCourseID())) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    AlertDialog dialog = builder.setMessage("Course Already Have")
                            .setPositiveButton("OK", null)
                            .create();

                    dialog.show();
                } else if (validate == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    AlertDialog dialog = builder.setMessage("Course Repeat")
                            .setPositiveButton("OK", null)
                            .create();

                    dialog.show();

                } else {


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

                                    courseIDList.add(courseList.get(position).getCourseID());
                                    schedule.addSchedule(courseList.get(position).getCourseTime());
                                } else {
                                    System.out.println("=============3=====");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                    AlertDialog dialog = builder.setMessage("Add Course Failed!")
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
                    AddRequest addRequest = new AddRequest(userID, courseList.get(position).getCourseID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getContext());
                    queue.add(addRequest);
                }
            }
        });
        return v;

    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://deakin.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(userID, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        //connection database
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                System.out.println("=========get notice");
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... value) {
            super.onProgressUpdate();
            System.out.println("=========onprogress");
        }

// get data from database

        @Override
        public void onPostExecute(String result) {
            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String courseProfessor;
                String courseTime;
                int courseID;
                while (count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseIDList.add(courseID);
                    schedule.addSchedule(courseTime);

                    //adapter data changed... !!!!
                    count++;


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean already(List<Integer> courseIDList, int item) {
        for (int i = 0; i < courseIDList.size(); i++) {
            if (courseIDList.get(i) == item) {
                return false;
            }
        }
        return true;
    }
}
