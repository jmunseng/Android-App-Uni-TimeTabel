package com.example.registeration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
//        noticeList.add(new Notice("test notice.", "test contents", "2020/05/29"));
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        final Button courseButton = findViewById(R.id.courseButton);
        final Button statisticsButton = findViewById(R.id.statisticsButton);
        final Button scheduleButton = findViewById(R.id.scheduleButton);
        final LinearLayout notice = findViewById(R.id.notice);


        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());

                fragmentTransaction.commit();

            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());

                fragmentTransaction.commit();

            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());

                fragmentTransaction.commit();

            }
        });
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {


        String target;

        @Override
        protected void onPreExecute() {
            target = "https://deakin.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) !=null){
                    stringBuilder.append(temp+ "\n");

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
        public void onProgressUpdate(Void... value){
            super.onProgressUpdate();
            System.out.println("=========onprogress");
        }
        @Override
        public void onPostExecute(String result){
            try{

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;
                while (count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent,noticeName,noticeDate);
                    System.out.println("======" + noticeContent);
                    noticeList.add(notice);
                    count++;


                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

//normal setup..
            newsRecycleView = findViewById(R.id.recyclerView);
            newsAdapter = new MyRecyclerAdapter(newsList, this);
            newsAdapter.setFragmentSelected(this);
            newsRecycleView.setAdapter(newsAdapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            newsRecycleView.setLayoutManager(layoutManager);
            //get data to the Arraylist
            for(int i = 0; i < imageList.length; i++){
                int image = imageList[i];
                String title = titleList[i];
                CardItem cardItem = new CardItem(title,image);
                newsList.add(cardItem);
            }

        }
        //run the fragment selected
        public void fragmentSelected(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainView, fragment);
            fragmentTransaction.commit();
        }
        //back button
        public void onBackPressed() {
            // do something on back.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}