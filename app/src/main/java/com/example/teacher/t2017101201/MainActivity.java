package com.example.teacher.t2017101201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
//                        try {
//                            JSONArray array = new JSONArray(response);
//                            for (int i=0;i<array.length();i++)
//                            {
//                                JSONObject obj = array.getJSONObject(i);
//                                Log.d("NET", "district:" + obj.getString("district"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        Gson gson = new Gson();
                        AnimalHome[] ah;
                        ah = gson.fromJson(response, AnimalHome[].class);
                        for (AnimalHome a : ah)
                        {
                            Log.d("NET", a.district);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NET", error.toString());
            }
        });
        queue.add(request);
        queue.start();

    }
}
