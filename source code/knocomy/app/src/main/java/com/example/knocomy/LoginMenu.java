package com.example.knocomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginMenu extends AppCompatActivity {


    Button playBtn;
    Button highscoresBtn;
    Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);


        String nick = getIntent().getExtras().getString("nick");

        playBtn = findViewById(R.id.playBtn);
        highscoresBtn = findViewById(R.id.highscoresBtn);
        quitBtn = findViewById(R.id.quitBtn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), Konu_Bolumu.class);
                activity2Intent.putExtra("nick", nick);
                startActivity(activity2Intent);

            }
        });

        highscoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), Highscores_Table.class);
                activity2Intent.putExtra("nick", nick);
                startActivity(activity2Intent);

            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMain);
            }
        });

    }
}