package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class imageprofile extends AppCompatActivity {

    TextView name,datofbirth,Gender,emailaddress,artisttype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageprofile);

        name = findViewById(R.id.name);
        datofbirth = findViewById(R.id.dob);
        Gender = findViewById(R.id.gender);
        emailaddress = findViewById(R.id.email);
        artisttype = findViewById(R.id.artist);



    }
}