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

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otppage extends AppCompatActivity {

    EditText Phonenumber;
    Button enterbutton;
    ProgressBar prgbar;
    FirebaseAuth mAuth; // object created
    String otpID,authcred;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppage);
        prgbar = (ProgressBar) findViewById(R.id.progressbar);
        Phonenumber=(EditText) findViewById(R.id.otpnumber);
        enterbutton=(Button) findViewById(R.id.next);
        mAuth = FirebaseAuth.getInstance(); // connected Database

        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP();
            }
        });

    }




    private void sendOTP() {
        String phone = Phonenumber.getText().toString().trim();
        if (phone.isEmpty()){
            Toast.makeText(getApplicationContext(),"Phone Number Should not be empty",Toast.LENGTH_LONG).show();

        }
        else if (phone.length()!=10){
            Toast.makeText(getApplicationContext(),"Phone Number Should be 10 Digits",Toast.LENGTH_LONG).show();
        }
        else
        {
            prgbar.setVisibility(View.VISIBLE);
            PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth).
                    setPhoneNumber("+91"+phone).
                    setTimeout(60L, TimeUnit.SECONDS).
                    setActivity(this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {

                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            prgbar.setVisibility(View.GONE);
                            otpID = verificationId;
                            Toast.makeText(getApplicationContext(),"OTP sent successfully",Toast.LENGTH_LONG).show();
                            Intent x = new Intent(otppage.this,enterotp.class);
                            x.putExtra("Otp",otpID);
                            x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(x);
                            finish();


                            //mResendToken = token;
                        }
                    }).build();
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);











        }



    }
}

