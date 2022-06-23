package com.example.knocomy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishScene extends AppCompatActivity {

    ImageView resultImg;
    TextView nickView, scoreView, resultView;
    Button toHome;

    String nick = "";
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_scene);

        nickView = findViewById(R.id.nickView);
        scoreView = findViewById(R.id.scoreView);
        resultView = findViewById(R.id.resultView);
        resultImg = findViewById(R.id.resultImg);

        toHome = findViewById(R.id.homeBtn);



        if(getIntent().getExtras()!=null){
            score = getIntent().getExtras().getInt("score");
            nick = getIntent().getExtras().getString("nick");
        }

        if(score > 40)
        {
            resultView.setText("CONGRATULATİONS");
            resultImg.setBackgroundResource(R.drawable.fireworks2);
        }
        else
        {
            resultView.setText("YOU FAILED");
            resultImg.setBackgroundResource(R.drawable.failedview);
        }

        nickView.setText(nick);
        scoreView.setText(String.valueOf(score));

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(getApplicationContext(), LoginMenu.class);
                toHome.putExtra("nick", nick);
                startActivity(toHome);
            }
        });

    }

}
