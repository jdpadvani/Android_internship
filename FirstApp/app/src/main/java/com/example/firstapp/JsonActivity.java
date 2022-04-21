package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {

    JSONObject jobj;
    JSONArray jArr;

    JSONParser jsonParser;
    String url="https://api.twitter.com/1/statuses/public_timeline.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        jsonParser =new JSONParser();




        try
        {
              // JSON parsing from Local String

//            jobj =new JSONObject(getListData());
//            jArr = jobj.getJSONArray("Students");
//
//            for(int i=0;i<jArr.length();i++)
//            {
//                Log.i("Name",""+jArr.getJSONObject(i).getString("name"));
//                Log.i("Branch",""+jArr.getJSONObject(i).getString("Branch"));
//            }

            // JSON parsing from URL

            jArr=jsonParser.getJSONFromUrl(url).getJSONArray("errors");
            Log.i("Message",""+jArr.getJSONObject(0).getString("message"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private String getListData() {
//        String json_stu1 = "{ \"Students\" :[" +
//                "{\"name\":\"Akshita Shrivastava \",\"Branch\":\"Computer Science\",\"institute\":\"IIT I\"}" +
//                ",{\"name\":\"Peter Potter\",\"Branch\":\"Civil\",\"institute\":\" IIT R\"}" +
//                ",{\"name\":\"Md Farman \",\"Branch\":\"Information Technology\",\"institute\":\" Bits Pilani\"}" +
//                ",{\"name\":\"Vipul Soni\",\"Branch\":\"Mechanical\",\"institute\":\" MIT\"}" +
//                ",{\"name\":\"Shikha Jain\",\"Branch\":\"Textile\",\"institute\":\" IIIT\"}" +
//                ",{\"name\":\"John Samuel\",\"Branch\":\"Electrical\",\"institute\":\" IIT B\"}" +
//                ",{\"name\":\"Rhea Sharma\",\"Branch\":\"Mechanical\",\"institute\":\"NIT\"}] }";
//        return json_stu1;
//    }
}