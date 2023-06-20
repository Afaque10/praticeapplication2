package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class imageprofile extends AppCompatActivity {

    TextView name,datofbirth,Gender,emailaddress,artisttype;
    ImageView Loggout;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageprofile);
        Loggout=(ImageView) findViewById(R.id.loggoutbutton);
        name = findViewById(R.id.name);
        datofbirth = findViewById(R.id.dob);
        Gender = findViewById(R.id.gender);
        emailaddress = findViewById(R.id.email);
        artisttype = findViewById(R.id.artist);
        mAuth=FirebaseAuth.getInstance();

        Loggout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Signout Successfull", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(imageprofile.this,otppage.class);
                c.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(c);
                finish();

            }
        });



    }
}