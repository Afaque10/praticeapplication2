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

public class profile extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    Button connectbutton;
    EditText emailedittextview;
    ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        emailedittextview=(EditText) findViewById(R.id.emailaddress);
        prgbar = (ProgressBar) findViewById(R.id.loadingbar);
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
        if (emaildata.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Email Should not be empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            if (!emaildata.contains("@gmail.com")){
                Toast.makeText(getApplicationContext(),"Email Invalid",Toast.LENGTH_LONG).show();
             }
            else {
                x.putExtra("email",emaildata);
                x.putExtra("full_name",fullname);
                prgbar.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prgbar.setVisibility(View.GONE);
                        startActivity(x);
                        finish();
                    }
                },1500);

            }


        }


    }
}
