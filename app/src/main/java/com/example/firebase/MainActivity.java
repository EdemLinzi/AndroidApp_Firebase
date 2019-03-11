package com.example.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("ContactData");

    public static final String NAME_KEY = "Name";
    public static final String DESCRIPTION_KEY = "Description";
    public static final String EMAIL_KEY = "Email";

    EditText editTextName;
    EditText editTextDescription;
    EditText editTextEmail;
    Button buttonSend;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editText_Name);
        editTextDescription = findViewById(R.id.editText_Description);
        editTextEmail = findViewById(R.id.editText_Email);
        buttonSend = findViewById(R.id.button_send);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        String name = mPreferences.getString(NAME_KEY,"");
        String description = mPreferences.getString(DESCRIPTION_KEY,"");
        String email = mPreferences.getString(EMAIL_KEY,"");

        editTextName.setText(name);
        editTextDescription.setText(description);
        editTextEmail.setText(email);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(NAME_KEY,editTextName.getText().toString());
        preferencesEditor.putString(DESCRIPTION_KEY,editTextDescription.getText().toString());
        preferencesEditor.putString(EMAIL_KEY,editTextEmail.getText().toString());

        preferencesEditor.apply();

    }

    public void sendData(View v){
        Log.i("MainActivity","Send Data");
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String email = editTextEmail.getText().toString();

        Details details = new Details(name,description,email);

        databaseReference.push()
                .setValue(details);
    }

    public void openActivity2(View v){
        Log.i("MainActivity","Open Activity2");
        startActivity(new Intent(this,ContactDetails.class));
    }

}
