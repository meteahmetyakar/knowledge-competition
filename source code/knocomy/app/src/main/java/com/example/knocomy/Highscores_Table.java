package com.example.knocomy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Highscores_Table extends AppCompatActivity {

    TextView nickTv1;
    TextView nickTv2;
    TextView nickTv3;
    TextView nickTv4;
    TextView nickTv5;
    TextView nickTv6;
    TextView nickTv7;
    TextView nickTv8;
    TextView nickTv9;
    TextView nickTv10;

    TextView scoreTv1;
    TextView scoreTv2;
    TextView scoreTv3;
    TextView scoreTv4;
    TextView scoreTv5;
    TextView scoreTv6;
    TextView scoreTv7;
    TextView scoreTv8;
    TextView scoreTv9;
    TextView scoreTv10;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores__table);

        DatabaseForUsers database = new DatabaseForUsers(Highscores_Table.this);

        nickTv1 = findViewById(R.id.nickTv1);
        nickTv2 = findViewById(R.id.nickTv2);
        nickTv3 = findViewById(R.id.nickTv3);
        nickTv4 = findViewById(R.id.nickTv4);
        nickTv5 = findViewById(R.id.nickTv5);
        nickTv6 = findViewById(R.id.nickTv6);
        nickTv7 = findViewById(R.id.nickTv7);
        nickTv8 = findViewById(R.id.nickTv8);
        nickTv9 = findViewById(R.id.nickTv9);
        nickTv10 = findViewById(R.id.nickTv10);

        scoreTv1 = findViewById(R.id.scoreTv1);
        scoreTv2 = findViewById(R.id.scoreTv2);
        scoreTv3 = findViewById(R.id.scoreTv3);
        scoreTv4 = findViewById(R.id.scoreTv4);
        scoreTv5 = findViewById(R.id.scoreTv5);
        scoreTv6 = findViewById(R.id.scoreTv6);
        scoreTv7 = findViewById(R.id.scoreTv7);
        scoreTv8 = findViewById(R.id.scoreTv8);
        scoreTv9 = findViewById(R.id.scoreTv9);
        scoreTv10 = findViewById(R.id.scoreTv10);

        String nick = getIntent().getExtras().getString("nick");

        List<String> highscorelistNick = new ArrayList<>();
        List<String> highscorelistScore = new ArrayList<>();

        highscorelistNick = null;
        highscorelistScore = null;

        highscorelistNick = database.pullData(0);
        highscorelistScore = database.pullData(1);

        int listSize = highscorelistScore.size();

        switch (listSize) {
            case 10:
                if(!highscorelistScore.get(9).equals("0")) {
                    nickTv10.setText(highscorelistNick.get(9));
                    scoreTv10.setText(highscorelistScore.get(9));
                }
                break;
            case 9:
                if(!highscorelistScore.get(8).equals("0")) {
                    nickTv9.setText(highscorelistNick.get(8));
                    scoreTv9.setText(highscorelistScore.get(8));
                }
            case 8:
                if(!highscorelistScore.get(7).equals("0")) {
                    nickTv8.setText(highscorelistNick.get(7));
                    scoreTv8.setText(highscorelistScore.get(7));
                }
            case 7:
                if(!highscorelistScore.get(6).equals("0")) {
                    nickTv7.setText(highscorelistNick.get(6));
                    scoreTv7.setText(highscorelistScore.get(6));
                }
            case 6:
                if(!highscorelistScore.get(5).equals("0")) {
                    nickTv6.setText(highscorelistNick.get(5));
                    scoreTv6.setText(highscorelistScore.get(5));
                }
            case 5:
                if(!highscorelistScore.get(4).equals("0")) {
                    nickTv5.setText(highscorelistNick.get(4));
                    scoreTv5.setText(highscorelistScore.get(4));
                }
            case 4:
                if(!highscorelistScore.get(3).equals("0")) {
                    nickTv4.setText(highscorelistNick.get(3));
                    scoreTv4.setText(highscorelistScore.get(3));
                }
            case 3:
                if(!highscorelistScore.get(2).equals("0")) {
                    nickTv3.setText(highscorelistNick.get(2));
                    scoreTv3.setText(highscorelistScore.get(2));
                }
            case 2:
                if(!highscorelistScore.get(1).equals("0")) {
                    nickTv2.setText(highscorelistNick.get(1));
                    scoreTv2.setText(highscorelistScore.get(1));
                }
            case 1:
                if(!highscorelistScore.get(0).equals("0"))
                {
                    nickTv1.setText(highscorelistNick.get(0));
                    scoreTv1.setText(highscorelistScore.get(0));
                }
                break;
            case 0:
                break;
        }

    }

}