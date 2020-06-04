package com.example.registeration;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private TextView monday[] = new TextView[14];
    private TextView tuesday[] = new TextView[14];
    private TextView wednesday[] = new TextView[14];
    private TextView thursday[] = new TextView[14];
    private TextView friday[] = new TextView[14];
    private  Schedule schedule = new Schedule();


    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        monday[0] = getView().findViewById(R.id.monday0);
        monday[1] = getView().findViewById(R.id.monday1);
        monday[2] = getView().findViewById(R.id.monday2);
        monday[3] = getView().findViewById(R.id.monday3);
        monday[4] = getView().findViewById(R.id.monday4);
        monday[5] = getView().findViewById(R.id.monday5);
        monday[6] = getView().findViewById(R.id.monday6);
        monday[7] = getView().findViewById(R.id.monday7);
        monday[8] = getView().findViewById(R.id.monday8);
        monday[9] = getView().findViewById(R.id.monday9);
        tuesday[0] = getView().findViewById(R.id.tuesday0);
        tuesday[1] = getView().findViewById(R.id.tuesday1);
        tuesday[2] = getView().findViewById(R.id.tuesday2);
        tuesday[3] = getView().findViewById(R.id.tuesday3);
        tuesday[4] = getView().findViewById(R.id.tuesday4);
        tuesday[5] = getView().findViewById(R.id.tuesday5);
        tuesday[6] = getView().findViewById(R.id.tuesday6);
        tuesday[7] = getView().findViewById(R.id.tuesday7);
        tuesday[8] = getView().findViewById(R.id.tuesday8);
        tuesday[9] = getView().findViewById(R.id.tuesday9);
        wednesday[0] = getView().findViewById(R.id.wednesday0);
        wednesday[1] = getView().findViewById(R.id.wednesday1);
        wednesday[2] = getView().findViewById(R.id.wednesday2);
        wednesday[3] = getView().findViewById(R.id.wednesday3);
        wednesday[4] = getView().findViewById(R.id.wednesday4);
        wednesday[5] = getView().findViewById(R.id.wednesday5);
        wednesday[6] = getView().findViewById(R.id.wednesday6);
        wednesday[7] = getView().findViewById(R.id.wednesday7);
        wednesday[8] = getView().findViewById(R.id.wednesday8);
        wednesday[9] = getView().findViewById(R.id.wednesday9);

        thursday[0] = getView().findViewById(R.id.thursday0);
        thursday[1] = getView().findViewById(R.id.thursday1);
        thursday[2] = getView().findViewById(R.id.thursday2);
        thursday[3] = getView().findViewById(R.id.thursday3);
        thursday[4] = getView().findViewById(R.id.thursday4);
        thursday[5] = getView().findViewById(R.id.thursday5);
        thursday[6] = getView().findViewById(R.id.thursday6);
        thursday[7] = getView().findViewById(R.id.thursday7);
        thursday[8] = getView().findViewById(R.id.thursday8);
        thursday[9] = getView().findViewById(R.id.thursday9);

        friday[0] = getView().findViewById(R.id.friday0);
        friday[1] = getView().findViewById(R.id.friday1);
        friday[2] = getView().findViewById(R.id.friday2);
        friday[3] = getView().findViewById(R.id.friday3);
        friday[4] = getView().findViewById(R.id.friday4);
        friday[5] = getView().findViewById(R.id.friday5);
        friday[6] = getView().findViewById(R.id.friday6);
        friday[7] = getView().findViewById(R.id.friday7);
        friday[8] = getView().findViewById(R.id.friday8);
        friday[9] = getView().findViewById(R.id.friday9);

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://deakin.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(MainActivity.userID, "UTF-8");
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
                System.out.println(result + "!!!!!!!!!======!!!!!");
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String courseProfessor;
                String courseTime;
                int courseID;
                String courseTitle;
                String noticeContent, noticeName, noticeDate;
                while (count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseTitle = object.getString("courseTitle");

                    schedule.addSchedule(courseTime, courseTitle, courseProfessor);
                    count++;


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            schedule.setting(monday,tuesday,wednesday,thursday,friday,getContext());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }
}
