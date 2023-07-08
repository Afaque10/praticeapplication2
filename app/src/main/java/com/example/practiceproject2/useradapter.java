package com.example.practiceproject2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class useradapter extends FirebaseRecyclerAdapter
        <users,useradapter.userViewholder>
{

    public useradapter(
            @NonNull FirebaseRecyclerOptions<users> options)
    {
    super(options);

    }
    @Override
    protected void onBindViewHolder(@NonNull userViewholder holder,
                                    int position, @NonNull users model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Artistholder.setText(model.getArtist());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.DOBholder.setText(model.getDOB());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Emailholder.setText(model.getEmail());
        holder.Genderholder.setText(model.getGender());
        holder.Nameholder.setText(model.getName());
    }

    public userViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_layout, parent, false);
        return new useradapter.userViewholder(view);
    }


    class userViewholder extends RecyclerView.ViewHolder {
        TextView Artistholder ,DOBholder,Emailholder,Genderholder,Nameholder;
        public userViewholder(@NonNull View itemView)
        {
            super(itemView);

            Artistholder = itemView.findViewById(R.id.card_artist);
            DOBholder = itemView.findViewById(R.id.card_dob);
            Emailholder = itemView.findViewById(R.id.card_emailaddress);
            Genderholder = itemView.findViewById(R.id.card_gender);
            Nameholder = itemView.findViewById(R.id.card_name);
        }
    }


}




