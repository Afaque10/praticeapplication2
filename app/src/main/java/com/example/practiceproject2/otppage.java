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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otppage extends AppCompatActivity {

    EditText Phonenumberedittext,otpdigitone,otpdigitwo,otpdigitthree,otpdigitfour,otpdigitfive,otpdigitsix;
    Button sendotpbutton,verifyotpbutton;
    ProgressBar prgbarotpsend,prgbarotpverify;
    TextView phonenumbertextview;
    FirebaseAuth mAuth; // object created
    LinearLayout otpsendlayout,otpverifylayout;
    String otpID,phonenumber,finalotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppage);

        prgbarotpsend = (ProgressBar) findViewById(R.id.progressbarsendotp);
        prgbarotpverify = (ProgressBar) findViewById(R.id.progressbarotpverify);
        Phonenumberedittext=(EditText) findViewById(R.id.phonenumberedittext);
        verifyotpbutton = (Button) findViewById(R.id.otpverifybutton);
        sendotpbutton=(Button) findViewById(R.id.otpsendbutton);
        phonenumbertextview=findViewById(R.id.phonetextview);
        otpsendlayout = findViewById(R.id.linearlayoutsendotp);
        otpverifylayout =findViewById(R.id.linearlayoutverifyotp);
        mAuth = FirebaseAuth.getInstance(); // connected Database

        otpdigitone=(EditText) findViewById(R.id.otpdigit1);
        otpdigitwo=(EditText) findViewById(R.id.otpdigit2);
        otpdigitthree=(EditText) findViewById(R.id.otpdigit3);
        otpdigitfour=(EditText) findViewById(R.id.otpdigit4);
        otpdigitfive=(EditText) findViewById(R.id.otpdigit5);
        otpdigitsix=(EditText) findViewById(R.id.otpdigit6);

        sendotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendOTP();
            }
        });

        verifyotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyOTP();
            }
        });


    }

    private void verifyOTP() {
        String otpone = otpdigitone.getText().toString().trim();
        String otptwo = otpdigitwo.getText().toString().trim();
        String otpthree = otpdigitthree.getText().toString().trim();
        String otpfour = otpdigitfour.getText().toString().trim();
        String otpfive = otpdigitfive.getText().toString().trim();
        String otpsix = otpdigitsix.getText().toString().trim();

        finalotp =otpone+otptwo+otpthree+otpfour+otpfive+otpsix;


        if (otpone.isEmpty()||otptwo.isEmpty()||otpthree.isEmpty()||otpfour.isEmpty()||otpfive.isEmpty()||otpsix.isEmpty()){
            Toast.makeText(getApplicationContext(),"Otp Cannot be blank",Toast.LENGTH_LONG).show();
        }
        else {
            if (finalotp.length()!=6){
                Toast.makeText(getApplicationContext(),"Otp Length Less Then Six",Toast.LENGTH_LONG).show();
            }

            else {

                prgbarotpverify.setVisibility(View.VISIBLE);
                PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(otpID, finalotp);
                signInWithPhoneAuthCredential(authCredential);




            }

        }


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    prgbarotpverify.setVisibility(View.GONE);
                    Intent x = new Intent(otppage.this,completeprofile.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(getApplicationContext(),"Otp Verified successfully",Toast.LENGTH_LONG).show();
                    startActivity(x);
                    finish();
                }
                else{
                    prgbarotpverify.setVisibility(View.GONE);
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

    private void sendOTP() {
        phonenumber = Phonenumberedittext.getText().toString().trim();
        if (phonenumber.isEmpty()){
            Toast.makeText(getApplicationContext(),"Phone Number Should not be empty",Toast.LENGTH_LONG).show();

        }
        else if (phonenumber.length()!=10){
            Toast.makeText(getApplicationContext(),"Phone Number Should be 10 Digits",Toast.LENGTH_LONG).show();
        }
        else
        {
            prgbarotpsend.setVisibility(View.VISIBLE);
            PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth).
                    setPhoneNumber("+91"+phonenumber).
                    setTimeout(60L, TimeUnit.SECONDS).
                    setActivity(this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                           

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            prgbarotpsend.setVisibility(View.GONE);
                            otpsendlayout.setVisibility(View.GONE);
                            phonenumbertextview.setText("+91 "+phonenumber);
                            otpverifylayout.setVisibility(View.VISIBLE);
                            otpID = verificationId;
                            Toast.makeText(getApplicationContext(),"OTP sent successfully",Toast.LENGTH_LONG).show();
                            //mResendToken = token;
                        }
                    }).build();
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);











        }



    }
}

