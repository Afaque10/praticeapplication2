package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class onboarding extends AppCompatActivity {

    //object creation
    Button onboardingbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //object initialized from the ui element
        onboardingbutton=(Button) findViewById(R.id.on_boarding_button);

        //registering the eventlistener on the button
        onboardingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(),otppage.class);
                startActivity(b);
            }
        });

    }
}