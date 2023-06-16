package com.example.practiceproject2;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dateofbirthpage extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    Button bottombutton;
    EditText dobedittext;
    ProgressBar prgbar;
    FirebaseAuth mAuth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateofbirthpage);

        dobedittext=(EditText) findViewById(R.id.dob);
        prgbar = (ProgressBar) findViewById(R.id.prgggbar);
        mAuth=FirebaseAuth.getInstance();

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

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
        if (dateofbirth.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"DOB cannot be empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            String uid = mAuth.getUid();
            prgbar.setVisibility(View.VISIBLE);
            HashMap userMAP = new HashMap();
            userMAP.put("Name",fullname);
            userMAP.put("Email",emaildata);
            userMAP.put("Arstist",artistdata);
            userMAP.put("Gender",genderdata);
            userMAP.put("DOB",dateofbirth);
            userMAP.put("UID",uid);

            userRef.child(uid).updateChildren(userMAP).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    prgbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_LONG).show();
                    startActivity(x);
                    finish();
                }
            });



        }



    }
}
