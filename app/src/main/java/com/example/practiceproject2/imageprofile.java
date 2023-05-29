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

        Intent intent = getIntent();
        String full_name = intent.getStringExtra("full_name");
        String email = intent.getStringExtra("email");
        String artist = intent.getStringExtra("artist");
        String Dob = intent.getStringExtra("dateofbirth");
        String gender = intent.getStringExtra("gender");


        name.setText(full_name);
        emailaddress.setText(email);
        artisttype.setText(artist);
        datofbirth.setText(Dob);
        Gender.setText(gender);


    }
}