package com.example.knocomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Konu_Bolumu extends AppCompatActivity {

    Button dbButton;
    Button dsButton;
    Button algButton;
    Button seButton;
    Button osButton;
    Button cnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu__bolumu);

        dbButton = findViewById(R.id.dbButton);
        dsButton = findViewById(R.id.dsButton);
        algButton = findViewById(R.id.algButton);
        seButton = findViewById(R.id.seButton);
        osButton = findViewById(R.id.osButton);
        cnButton = findViewById(R.id.cnButton);

        int index = 0;

        String nick = getIntent().getExtras().getString("nick");

        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "DATABASE");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);

            }
        });

        dsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "DATA STRUCTURES");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);
            }
        });

        algButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "ALGORITHM");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);
            }
        });

        seButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "SOFTWARE ENGINEERING");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);
            }
        });

        osButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "OPERATİNG SYSTEM");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);
            }
        });

        cnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                toQuestions.putExtra("subject", "COMPUTER NETWORKS");
                toQuestions.putExtra("nick", nick);
                toQuestions.putExtra("index", index);
                startActivity(toQuestions);
            }
        });

    }
}