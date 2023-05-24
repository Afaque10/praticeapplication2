package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dateofbirthpage extends AppCompatActivity {

    Button bottombutton;
    EditText dobedittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateofbirthpage);

        dobedittext=(EditText) findViewById(R.id.dob);

        bottombutton=(Button) findViewById(R.id.in);
        bottombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd();

            }
        });

    }
    public void  asd( ){
        Intent x = new Intent(dateofbirthpage.this,imageprofile.class);
        x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent = getIntent();

        String dateofbirth = dobedittext.getText().toString().trim();
        String fullname = intent.getStringExtra("full_name");
        String emaildata = intent.getStringExtra("email");
        String artistdata = intent.getStringExtra("artist");
        String genderdata = intent.getStringExtra("gender");
        x.putExtra("full_name",fullname);
        x.putExtra("email",emaildata);
        x.putExtra("artist",artistdata);
        x.putExtra("gender",genderdata);
        x.putExtra("dateofbirth",dateofbirth);
        startActivity(x);



        startActivity(x);
        finish();
    }
}
