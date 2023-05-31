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

public class artistpage extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());


    Button topbutton;
    EditText artistedittext;
    ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistpage);


        prgbar = (ProgressBar) findViewById(R.id.buffer);
        artistedittext=findViewById(R.id.artist);
        topbutton=(Button) findViewById(R.id.click);
        topbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd();

            }
        });

    }
    public void  asd( ){
        
        Intent x = new Intent(artistpage.this,genderpage.class);
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("full_name");
        String artistdata = artistedittext.getText().toString().trim();
        String emaildata = intent.getStringExtra("email");

        if (artistdata.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Name Sould not be Empty",Toast.LENGTH_LONG).show();
        }
        else {
            x.putExtra("artist",artistdata);
            x.putExtra("full_name",fullname);
            x.putExtra("email",emaildata);
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
