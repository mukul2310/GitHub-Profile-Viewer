package com.example.githubprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    EditText editText;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.etId);
        btn=findViewById(R.id.btnSubmit);
        btn.setOnClickListener(view ->
        {
            Intent i=new Intent(MainActivity.this, ProfileActivity.class);
            i.putExtra("userName",editText.getText().toString());
            startActivity(i);
        });

    }

}