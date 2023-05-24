package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class completeprofile extends AppCompatActivity {

    Button profile_button;
    EditText fullnameedittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completeprofile);

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
        x.putExtra("full_name",namedata);
        startActivity(x);

    }
}
