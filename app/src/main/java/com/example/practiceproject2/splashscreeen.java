package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;


public class splashscreeen extends AppCompatActivity {

    final Handler handler = new Handler(Looper.getMainLooper());//handler object creation


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreeen);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);

       // handler postdelayed method implemented
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!previouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
                    edit.commit();
                    Intent a = new Intent(getApplicationContext(), onboarding.class);//intent object creation
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//registering flags
                    startActivity(a);//starting the intent
                    finish();
                }
                else {
                    Intent a = new Intent(getApplicationContext(), otppage.class);//intent object creation
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//registering flags
                    startActivity(a);//starting the intent
                    finish();
                }


            }
        }, 1500);

    }
}