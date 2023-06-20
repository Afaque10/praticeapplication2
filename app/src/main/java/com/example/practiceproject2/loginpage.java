package com.example.practiceproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {

    EditText emailid,password;
    Button loginbutton;
    FirebaseAuth mAuth;
    ProgressBar signprogress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        emailid=(EditText) findViewById(R.id.emailid);
        password=(EditText) findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
        signprogress=(ProgressBar) findViewById(R.id.progressbarverify);
        loginbutton=(Button) findViewById(R.id.signinbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }
    public void login (){
        signprogress.setVisibility(View.VISIBLE);

        String emaildata = emailid.getText().toString().trim();
        String Passworddata = password.getText().toString().trim();


        if (emaildata.isEmpty())
        {
            signprogress.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Email Should Not Be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (!emaildata.contains("@gmail.com")){
            signprogress.setVisibility(View.GONE);

            Toast.makeText(getApplicationContext(),"Email Error",Toast.LENGTH_LONG).show();
        }
        else if (Passworddata.isEmpty())
        {
            signprogress.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Password Should Not Be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (Passworddata.length()<8)
        {
            signprogress.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Password Length Should Be Greater Than 8", Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(emaildata,Passworddata).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        signprogress.setVisibility(View.GONE);
                        Intent x = new Intent(loginpage.this,imageprofile.class);
                        x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_LONG).show();
                        startActivity(x);
                        finish();
                    }
                    else {
                        String msg = task.getException().getMessage();
                        if (msg.equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                            signprogress.setVisibility(View.GONE);
                            Toast.makeText(loginpage.this, "No user Found", Toast.LENGTH_LONG).show();

                        }


                        else if (msg.equals("The password is invalid or the user does not have a password.")){
                            signprogress.setVisibility(View.GONE);
                            Toast.makeText(loginpage.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();


                        }

                        else {
                            signprogress.setVisibility(View.GONE);
                            Toast.makeText(loginpage.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            });

        }





    }
}