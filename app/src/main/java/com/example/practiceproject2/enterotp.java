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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class enterotp extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());

    EditText otpdigitone,otpdigitwo,otpdigitthree,otpdigitfour,otpdigitfive,otpdigitsix;

    Button new_button;
    ProgressBar prgbar;
    String otpid;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);

        otpdigitone=(EditText) findViewById(R.id.otpdigit1);
        otpdigitwo=(EditText) findViewById(R.id.otpdigit2);
        otpdigitthree=(EditText) findViewById(R.id.otpdigit3);
        otpdigitfour=(EditText) findViewById(R.id.otpdigit4);
        otpdigitfive=(EditText) findViewById(R.id.otpdigit5);
        otpdigitsix=(EditText) findViewById(R.id.otpdigit6);
        prgbar = (ProgressBar) findViewById(R.id.load);
        new_button=(Button) findViewById(R.id.toplinear1);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        otpid = intent.getStringExtra("Otp");
        Toast.makeText(getApplicationContext(), "code "+otpid, Toast.LENGTH_SHORT).show();
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(otpid);
            }
        });

    }



    public void  verifyOtp( String OTPid){

        String otpone = otpdigitone.getText().toString().trim();
        String otptwo = otpdigitwo.getText().toString().trim();
        String otpthree = otpdigitthree.getText().toString().trim();
        String otpfour = otpdigitfour.getText().toString().trim();
        String otpfive = otpdigitfive.getText().toString().trim();
        String otpsix = otpdigitsix.getText().toString().trim();




        String finalotp = otpone+otptwo+otpthree+otpfour+otpfive+otpsix;

        if (otpone.isEmpty()||otptwo.isEmpty()||otpthree.isEmpty()||otpfour.isEmpty()||otpfive.isEmpty()||otpsix.isEmpty()){
            Toast.makeText(getApplicationContext(),"Otp Cannot be blank",Toast.LENGTH_LONG).show();
        }
        else {
            if (finalotp.length()!=6){
                Toast.makeText(getApplicationContext(),"Otp Length Less Then Six",Toast.LENGTH_LONG).show();
            }

            else {
                 PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        prgbar.setVisibility(View.VISIBLE);
                        PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(OTPid, finalotp);
                        signInWithPhoneAuthCredential(authCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(), "Invalid Code", Toast.LENGTH_SHORT).show();

                    }
                };




            }

        }





    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    prgbar.setVisibility(View.GONE);
                    Intent x = new Intent(enterotp.this,completeprofile.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(getApplicationContext(),"Otp Verified successfully",Toast.LENGTH_LONG).show();
                    startActivity(x);
                    finish();
                }
                else{
                    prgbar.setVisibility(View.GONE);
                    String msg = task.getException().getMessage();
                    if (msg.equals("The sms code has expired. Please re-send the verification code to try again.")) {
                        Toast.makeText(getApplicationContext(), "Code Expired", Toast.LENGTH_LONG).show();
                    } else if (msg.equals("The sms verification code used to create the phone auth credential is invalid. Please resend the verification code sms and be sure use the verification code provided by the user.")) {
                        Toast.makeText(getApplicationContext(), "Invalid Code", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                    }

                }


            }
        });
    }

    }
