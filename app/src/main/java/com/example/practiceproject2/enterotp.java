package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class enterotp extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    Button new_button;
    ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);


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
