package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactDetails extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("ContactData");
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                displayContact();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        displayContact();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void displayContact(){

        FirebaseRecyclerOptions<Details> options = new FirebaseRecyclerOptions.Builder<Details>()
                .setQuery(databaseReference,Details.class)
                .build();
        FirebaseRecyclerAdapter<Details,MyRecyclerViewHolder> adapter =
                new FirebaseRecyclerAdapter<Details, MyRecyclerViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position, @NonNull Details model) {
                        holder.textViewName.setText(model.getName());
                        holder.textViewDescription.setText(model.getDescription());
                        holder.textViewEmail.setText(model.getEmail());
                    }

                    @NonNull
                    @Override
                    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View itemview = LayoutInflater.from(getBaseContext()).inflate(R.layout.list_item,viewGroup,false);
                        return new MyRecyclerViewHolder(itemview);
                    }
                };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

}
