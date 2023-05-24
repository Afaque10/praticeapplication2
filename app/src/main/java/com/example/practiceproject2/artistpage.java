package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class artistpage extends AppCompatActivity {
    Button topbutton;
    EditText artistedittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistpage);

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
        x.putExtra("artist",artistdata);
        x.putExtra("full_name",fullname);
        x.putExtra("email",emaildata);
        startActivity(x);
    }
}
