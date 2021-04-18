package com.example.githubprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfileActivity extends AppCompatActivity
{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv=findViewById(R.id.text);
        Intent i=getIntent();
        String name=i.getStringExtra("userName");
        MyTask myTask=new MyTask();
        myTask.execute("https://api.github.com/search/users?q="+name);
    }
    class MyTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                Scanner sc=new Scanner(inputStream);
                String s="";
                while(sc.hasNext())
                {
                    s+=sc.next();
                    return s;
                }
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return "Not found";
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            ArrayList<Profile> a = null;
//            tv.setText(s);
            try
            {
                a=parseJSON(s);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            ProfileAdapter profileAdapter=new ProfileAdapter(a);
            RecyclerView rv=findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            rv.setAdapter(profileAdapter);
//            recyclerView.setA
        }
    }
    public static ArrayList<Profile> parseJSON(String s) throws JSONException
    {
        ArrayList<Profile> a=new ArrayList<>();
        JSONObject json=new JSONObject(s);
        JSONArray items=json.getJSONArray("items");
        for(int i=0;i<items.length();i++)
        {
            a.add(new Profile(items.getJSONObject(i).getString("login"),items.getJSONObject(i).getInt("id"),items.getJSONObject(i).getString("html_url"),items.getJSONObject(i).getString("avatar_url"),items.getJSONObject(i).getDouble("score")));
        }
        return  a;
    }

}