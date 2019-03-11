package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewDescription;
    TextView textViewEmail;

    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.textView_Name);
        textViewDescription = itemView.findViewById(R.id.textView_Description);
        textViewEmail = itemView.findViewById(R.id.textView_Email);

    }
}
