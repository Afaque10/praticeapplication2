package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class genderpage extends AppCompatActivity {

    Button downbutton;
    EditText genderedittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genderpage);

        genderedittext=(EditText)   findViewById(R.id.gender);


        downbutton=(Button) findViewById(R.id.tap);
        downbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd();

            }
        });

    }
    public void  asd( ){
        Intent x = new Intent(genderpage.this,dateofbirthpage.class);
        Intent intent = getIntent();

        String genderdata = genderedittext.getText().toString().trim();
        String fullname = intent.getStringExtra("full_name");
        String emaildata = intent.getStringExtra("email");
        String artistdata = intent.getStringExtra("artist");
        x.putExtra("full_name",fullname);
        x.putExtra("email",emaildata);
        x.putExtra("artist",artistdata);
        x.putExtra("gender",genderdata);
        startActivity(x);
    }
}
