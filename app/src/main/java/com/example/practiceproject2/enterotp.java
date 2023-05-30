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
import android.widget.Toast;

public class enterotp extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    EditText otpdigitone,otpdigitwo,otpdigitthree,otpdigitfour,otpdigitfive;

    Button new_button;
    ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);

        otpdigitone=(EditText) findViewById(R.id.otpdigit1);
        otpdigitwo=(EditText) findViewById(R.id.otpdigit2);
        otpdigitthree=(EditText) findViewById(R.id.otpdigit3);
        otpdigitfour=(EditText) findViewById(R.id.otpdigit4);
        otpdigitfive=(EditText) findViewById(R.id.otpdigit5);
        prgbar = (ProgressBar) findViewById(R.id.load);
        new_button=(Button) findViewById(R.id.toplinear1);
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyz();

            }
        });

    }
    public void  xyz( ){

        String otpone = otpdigitone.getText().toString().trim();
        String otptwo = otpdigitwo.getText().toString().trim();
        String otpthree = otpdigitthree.getText().toString().trim();
        String otpfour = otpdigitfour.getText().toString().trim();
        String otpfive = otpdigitfive.getText().toString().trim();

        String finalotp = otpone+otptwo+otpthree+otpfour+otpfive;

        if (otpone.isEmpty()||otptwo.isEmpty()||otpthree.isEmpty()||otpfour.isEmpty()||otpfive.isEmpty()){
            Toast.makeText(getApplicationContext(),"Otp Cannot be blank",Toast.LENGTH_LONG).show();
        }
        else {

            if (finalotp.length()!=5){
                Toast.makeText(getApplicationContext(),"Otp Lenght Less Then Five",Toast.LENGTH_LONG).show();
            }

            else {
                Intent x = new Intent(enterotp.this,completeprofile.class);
                x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                prgbar.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prgbar.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Otp Verified successfully",Toast.LENGTH_LONG).show();
                        startActivity(x);
                        finish();
                    }
                },1500);
            }

        }





    }
}
