package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class completeprofile extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    Button profile_button;
    EditText fullnameedittext;
    ProgressBar prgbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completeprofile);



        prgbar = (ProgressBar) findViewById(R.id.loading);
        profile_button=(Button) findViewById(R.id.continue_button);
        fullnameedittext=(EditText) findViewById(R.id.full_name);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qwe();

            }
        });

    }
    public void  qwe( ){
        Intent x = new Intent(completeprofile.this,profile.class);
        String namedata = fullnameedittext.getText().toString().trim();
        if (namedata.isEmpty()){
            Toast.makeText(getApplicationContext(),"Name cannot be empty",Toast.LENGTH_LONG).show();

        }
        else {
            x.putExtra("full_name",namedata);
            prgbar.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    prgbar.setVisibility(View.VISIBLE);
                    startActivity(x);
                    finish();
                }
            },1500);

        }



    }
}
