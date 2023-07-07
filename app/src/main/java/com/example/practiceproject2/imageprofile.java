package com.example.practiceproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class imageprofile extends AppCompatActivity {

    TextView name,datofbirth,Gender,emailaddress,artisttype,abouttap;
    ImageView Loggout;
    FirebaseAuth mAuth;
    String uid ;
    DatabaseReference profileref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageprofile);
        Loggout=(ImageView) findViewById(R.id.loggoutbutton);
        name = findViewById(R.id.name);
        abouttap = findViewById(R.id.card_about);
        datofbirth = findViewById(R.id.dob);
        Gender = findViewById(R.id.gender);
        emailaddress = findViewById(R.id.email);
        artisttype = findViewById(R.id.artist);
        mAuth=FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        profileref = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        profileref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String dbname,dbgender,dbemail,dbartisttype,dbdob;

                dbname = snapshot.child("Name").getValue().toString();
                dbgender = snapshot.child("Gender").getValue().toString();
                dbemail = snapshot.child("Email").getValue().toString();
                dbartisttype = snapshot.child("Arstist").getValue().toString();
                dbdob = snapshot.child("DOB").getValue().toString();

                name.setText(dbname);
                Gender.setText(dbgender);
                emailaddress.setText(dbemail);
                datofbirth.setText(dbdob);
                artisttype.setText(dbartisttype);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        abouttap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd();

            }
            public void  asd( ){
                Intent x = new Intent(imageprofile.this,list_users.class);
                startActivity(x);
            }
        });




    }
}