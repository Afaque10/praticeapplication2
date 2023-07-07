package com.example.practiceproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class list_users extends AppCompatActivity {

    useradapter adapter;
    DatabaseReference mbase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        mbase= FirebaseDatabase.getInstance().getReference().child("Users");
        RecyclerView recyclerView = findViewById(R.id.user_recycler);

        recyclerView.setLayoutManager(
                    new LinearLayoutManager(this));

            FirebaseRecyclerOptions<users> options
                    = new FirebaseRecyclerOptions.Builder<users>()
                    .setQuery(mbase,users.class)
                    .build();
            adapter = new useradapter(options);
            recyclerView.setAdapter(adapter);

        }

        @Override protected void onStart()
        {
            super.onStart();
            adapter.startLisenting();

        }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



    }
