package com.example.api1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url;
    JSONArray jsonarr;
    RecyclerView rv1;
    Handler h = new Handler();
    String userId, id, title, body;
    private List<personlist> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();


        url = "https://jsonplaceholder.typicode.com/posts";

        new Thread(new Runnable() {
            @Override
            public void run() {
/*
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                    for (int i = 0; i < response.length(); i++) {
                        personlist personlist1 = new personlist();

//                        JsonArrayRequest jsonArrayRequest=response.
                        JSONObject jsonObject=response.getJSONObject(i);
                        personlist1.setUserId(jsonObject.getString("userId"));
                        personlist1.setId(jsonObject.getString("id"));
                        personlist1.setTitle(jsonObject.getString("title"));
                        personlist1.setBody(jsonObject.getString("body"));
                        list.add(personlist1);

                    }
                    rv1=findViewById(R.id.rv1);
                    RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(list);
                    rv1.setHasFixedSize(true);
                    rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv1.setAdapter(recyclerViewAdapter);

                        }catch (Exception e){
                            Log.d("er",e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Method.GET, url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                personlist personlist1 = new personlist();

                                JSONObject jsonObject=response.getJSONObject(i);
                                personlist1.setUserId(jsonObject.getString("userId"));
                                personlist1.setId(jsonObject.getString("id"));
                                personlist1.setTitle(jsonObject.getString("title"));
                                personlist1.setBody(jsonObject.getString("body"));
                                list.add(personlist1);

                            }
                            rv1=findViewById(R.id.rv1);
                            RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(list);
                            rv1.setHasFixedSize(true);
                            rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv1.setAdapter(recyclerViewAdapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonArrayRequest);

            }
        }).start();

    }
}
