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

public class otppage extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());
    EditText Phonenumber;
    Button enterbutton;
    ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppage);
        prgbar = (ProgressBar) findViewById(R.id.progressbar);
        Phonenumber=(EditText) findViewById(R.id.otpnumber);
        enterbutton=(Button) findViewById(R.id.next);
        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc();

            }
        });

    }
    public void  abc( ){
        String phone = Phonenumber.getText().toString().trim();
        if (phone.length()!=10){
            Toast.makeText(getApplicationContext(),"Phone Number Should be 10 Digits",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent x = new Intent(otppage.this,enterotp.class);
            x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            prgbar.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    prgbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Otp sent successfully",Toast.LENGTH_LONG).show();
                    startActivity(x);
                    finish();
                }
            },1500);
        }






    }
}

