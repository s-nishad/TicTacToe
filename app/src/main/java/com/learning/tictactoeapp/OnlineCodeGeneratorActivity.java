package com.learning.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OnlineCodeGeneratorActivity extends AppCompatActivity {

    TextView Heading;
    EditText editCode;
    Button btnCreate, btnJoin;
    ProgressBar pbLoading;


    boolean isCodeMaker = true;
    String code = "";
    boolean codeFound = false;
    boolean checkTemp = true;
    String keyValue = "";

    FirebaseDatabase database;
    DatabaseReference playerRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_code_generator);

        Heading = findViewById(R.id.Heading);
        editCode = findViewById(R.id.editCode);
        btnCreate = findViewById(R.id.btnCreate);
        btnJoin = findViewById(R.id.btnJoin);
        pbLoading = findViewById(R.id.pbLoading);

        //for create
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //for join
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    } //first end

    void accepted() {
        startActivity(new Intent(this, OnlinePlayActivity.class));
        btnCreate.setVisibility(View.VISIBLE);
        btnJoin.setVisibility(View.VISIBLE);
        editCode.setVisibility(View.VISIBLE);
        Heading.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }


//    Boolean isValueAvailable(DataSnapshot snapshot,String code) {
//        database.getReference();
//
//    }
} //last end