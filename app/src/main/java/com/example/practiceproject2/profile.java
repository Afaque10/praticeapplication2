package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profile extends AppCompatActivity {

    Button connectbutton;
    EditText emailedittextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        emailedittextview=(EditText) findViewById(R.id.emailaddress);

        connectbutton=(Button) findViewById(R.id.top_button);
        connectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd();

            }
        });

    }
    public void  asd( ){
        Intent x = new Intent(profile.this,artistpage.class);
        Intent intent = getIntent();

        String fullname = intent.getStringExtra("full_name");
        String emaildata = emailedittextview.getText().toString().trim();
        x.putExtra("email",emaildata);
        x.putExtra("full_name",fullname);

        startActivity(x);
    }
}
