package com.example.knocomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.knocomy.model.Questions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Question_Page extends AppCompatActivity {

    TextView AnswerA;
    TextView AnswerB;
    TextView AnswerC;
    TextView AnswerD;
    Button scoreTxt;
    Button toHome;
    TextView Question;
    TextView SubjectForUI;
    TextView level;
    String subject;
    String nick;
    TextView time;
    CountDownTimer countDownTimer;

    LinearLayout AnswerALayout;
    LinearLayout AnswerBLayout;
    LinearLayout AnswerCLayout;
    LinearLayout AnswerDLayout;

    List<String> QuestionData = new ArrayList<>();
    List<String> TrueAnswerData = new ArrayList<>();
    List<String> Answer1Data = new ArrayList<>();
    List<String> Answer2Data = new ArrayList<>();
    List<String> Answer3Data = new ArrayList<>();
    List<String> MixAnswers = new ArrayList<>();

    int index=0;
    int score =0;
    int isOver = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question__page);

        Question = findViewById(R.id.monitor);

        AnswerA = findViewById(R.id.AnswerA);
        AnswerB = findViewById(R.id.AnswerB);
        AnswerC = findViewById(R.id.AnswerC);
        AnswerD = findViewById(R.id.AnswerD);

        AnswerALayout = findViewById(R.id.AnswerALayout);
        AnswerBLayout = findViewById(R.id.AnswerBLayout);
        AnswerCLayout = findViewById(R.id.AnswerCLayout);
        AnswerDLayout = findViewById(R.id.AnswerDLayout);

        scoreTxt = findViewById(R.id.scoreTxt);
        SubjectForUI = findViewById(R.id.Kategori);
        time =  findViewById(R.id.time);
        level = findViewById(R.id.level);
        toHome = findViewById(R.id.toHome);


        if(getIntent().getExtras()!=null){
            index = getIntent().getExtras().getInt("index");
            subject = getIntent().getExtras().getString("subject");
            score = getIntent().getExtras().getInt("score");
            nick = getIntent().getExtras().getString("nick");
        }

        Questions questionLists = new Questions();
        try {
            //Load File
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.questiondatabase))); //burada raw klasöründeki questiondatabase json dosyasının içindeki her şeyi jsonReader isimli nesneye aktarıyoruz
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) { //Burada line diye bir string değişkeni oluşturuyoruz ve line = jsonReader.readLine() null olmadığı sürece devam edecek bir döngü başlatıyoruz.
                jsonBuilder.append(line).append("\n");                            //readLine adı üstünde satır okumaya yarıyor ve her çağrılışında bir satır okuyup bir sonraki satıra geçiyor yani ilk çağırdığınızda 1. satırı okuduysa ikinci çağırdığınızda 2. satırı okuyor ve en sondaki satır da null oluyor bu yüzden
            }                                                                       //(line = jsonReader.readLine()) != null diye bir koşul koyuyoruz en son satıra geldiğinde line değişkeninin içi null olacak ve döngü duracak alt satırda da bu okuduğumuz değerleri jsonBuildera ekliyoruz, böylece dosyadan okuma işlemini yapmış oluyoruz.
            Gson gson = new Gson(); //json’u parse etmek için Gson kütüphanesini kullanıyoruz, parse dönüştürmek demek, yani json dosyasını stringe dönüştürücez dönüştürme içinde bu gerekiyor
            questionLists = gson.fromJson(jsonBuilder.toString(), Questions.class); //Burada da jsonBuilder içindeki her şeyi Question classından ürettiğimiz questionLists nesnesine atıyoruz. Bu classında tek işlevi var zaten o da getQuestiondatabase() bununla da bu nesne içerisinden eleman çekebiliyoruz.
        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        } //try-catch kullanmamızın tek sebebi de readLine ı try catch ile kullanmadığımızda hata veriyor o kadar.

        if(getIntent().getExtras()!=null){
            subject = getIntent().getExtras().getString("subject");
        }

        SubjectForUI.setText(subject);


        for (int i = 0; i < questionLists.getQuestiondatabase().size(); i++) {
            if (subject.equals(questionLists.getQuestiondatabase().get(i).getSubject())) {
                QuestionData.add(questionLists.getQuestiondatabase().get(i).getQuestion());
                TrueAnswerData.add(questionLists.getQuestiondatabase().get(i).getAnswerTrue());
                Answer1Data.add((questionLists.getQuestiondatabase().get(i).getAnswer1()));
                Answer2Data.add((questionLists.getQuestiondatabase().get(i).getAnswer2()));
                Answer3Data.add((questionLists.getQuestiondatabase().get(i).getAnswer3()));
            }
        }
        nextQuestion(index);
        DatabaseForUsers database = new DatabaseForUsers(Question_Page.this);

        AnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MixAnswers.get(0).equals(TrueAnswerData.get(index)))
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerALayout.setBackgroundResource(R.drawable.trueanswerbox);
                    score = score + (mul*5);
                }
                else
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerALayout.setBackgroundResource(R.drawable.wronganswerbox);
                    score = score - (mul*2);
                }

                if(score < 0) score = 0;

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                index++;
                if(index == 5)
                {

                    database.veriGuncelle(nick, score);
                    Intent toFinishScene = new Intent(getApplicationContext(), FinishScene.class);
                    toFinishScene.putExtra("score", score);
                    toFinishScene.putExtra("nick", nick);
                    isOver = 1;
                    startActivity(toFinishScene);
                }
                else
                {
                    toQuestions.putExtra("index", index);
                    toQuestions.putExtra("subject", subject);
                    toQuestions.putExtra("score", score);
                    toQuestions.putExtra("nick", nick);
                    waitAndContinue(index);

                }

            }
        });

        AnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MixAnswers.get(1).equals(TrueAnswerData.get(index)))
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerBLayout.setBackgroundResource(R.drawable.trueanswerbox);
                    score = score + (mul*5);
                }
                else
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerBLayout.setBackgroundResource(R.drawable.wronganswerbox);
                    score = score - (mul*2);
                }

                if(score < 0) score = 0;

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                index++;
                if(index == 5)
                {
                    database.veriGuncelle(nick, score);
                    Intent toFinishScene = new Intent(getApplicationContext(), FinishScene.class);
                    toFinishScene.putExtra("score", score);
                    toFinishScene.putExtra("nick", nick);
                    isOver = 1;

                    startActivity(toFinishScene);
                }
                else
                {
                    toQuestions.putExtra("index", index);
                    toQuestions.putExtra("subject", subject);
                    toQuestions.putExtra("score", score);
                    toQuestions.putExtra("nick", nick);
                    waitAndContinue(index);

                }
            }
        });

        AnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MixAnswers.get(2).equals(TrueAnswerData.get(index)))
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerCLayout.setBackgroundResource(R.drawable.trueanswerbox);
                    score = score + (mul*5);

                }
                else
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerCLayout.setBackgroundResource(R.drawable.wronganswerbox);
                    score = score - (mul*2);
                }

                if(score < 0) score = 0;

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                index++;
                if(index == 5)
                {

                    database.veriGuncelle(nick, score);
                    Intent toFinishScene = new Intent(getApplicationContext(), FinishScene.class);
                    toFinishScene.putExtra("score", score);
                    toFinishScene.putExtra("nick", nick);
                    isOver = 1;

                    startActivity(toFinishScene);
                }
                else
                {
                    toQuestions.putExtra("index", index);
                    toQuestions.putExtra("subject", subject);
                    toQuestions.putExtra("score", score);
                    toQuestions.putExtra("nick", nick);
                    waitAndContinue(index);
                }
            }
        });

        AnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MixAnswers.get(3).equals(TrueAnswerData.get(index)))
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerDLayout.setBackgroundResource(R.drawable.trueanswerbox);
                    score = score + (mul*5);
                }
                else
                {
                    int mul = Integer.parseInt(time.getText().toString())/20;
                    AnswerDLayout.setBackgroundResource(R.drawable.wronganswerbox);
                    score = score - (mul*2);
                }

                if(score < 0) score = 0;

                Intent toQuestions = new Intent(getApplicationContext(), Question_Page.class);
                index++;
                if(index == 5)
                {

                    database.veriGuncelle(nick, score);
                    Intent toFinishScene = new Intent(getApplicationContext(), FinishScene.class);
                    toFinishScene.putExtra("score", score);
                    toFinishScene.putExtra("nick", nick);
                    isOver = 1;

                    startActivity(toFinishScene);
                }
                else
                {
                    toQuestions.putExtra("index", index);
                    toQuestions.putExtra("subject", subject);
                    toQuestions.putExtra("score", score);
                    toQuestions.putExtra("nick", nick);
                    waitAndContinue(index);
                }
            }
        });


        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(getApplicationContext(), LoginMenu.class);
                toHome.putExtra("nick", nick);
                isOver = 1;
                startActivity(toHome);
            }
        });


    }

    protected void onStart(){
        super.onStart();

        time.setText("100");
        countDownTimer = new CountDownTimer(100*1000, 1000) {
            @Override
            public void onTick(long millSecondsLeftToFinish){
                String time2 = String.valueOf(millSecondsLeftToFinish / 1000);
                time.setText(time2);
            }

            @Override
            public void onFinish(){
                if(isOver != 1)
                {
                    Intent toFinishScene = new Intent(getApplicationContext(), FinishScene.class);
                    toFinishScene.putExtra("score", score);
                    toFinishScene.putExtra("nick", nick);
                    startActivity(toFinishScene);
                }

            }
        };
        countDownTimer.start();


    }

    void waitAndContinue(int index){ //1 saniye bekleyip cevap doğruysa, tamam mı devam mı diye soruyor. Yanlışsa, direkt gameover sahnesine atıyor.

        countDownTimer = new CountDownTimer(1*1000, 1000) {
            @Override
            public void onTick(long millSecondsLeftToFinish){
                AnswerA.setClickable(false);
                AnswerB.setClickable(false);
                AnswerC.setClickable(false);
                AnswerD.setClickable(false);
            }

            @Override
            public void onFinish(){
                AnswerA.setClickable(true);
                AnswerB.setClickable(true);
                AnswerC.setClickable(true);
                AnswerD.setClickable(true);

                nextQuestion(index);
            }
        };
        countDownTimer.start();
    }

    void setDefaultBoxes()
    {
        AnswerALayout.setBackgroundResource(R.drawable.answerbox);
        AnswerBLayout.setBackgroundResource(R.drawable.answerbox);
        AnswerCLayout.setBackgroundResource(R.drawable.answerbox);
        AnswerDLayout.setBackgroundResource(R.drawable.answerbox);

    }

    void nextQuestion(int index)
    {
        MixAnswers.clear();

        MixAnswers.add(Answer1Data.get(index));
        MixAnswers.add(Answer2Data.get(index));
        MixAnswers.add(Answer3Data.get(index));
        MixAnswers.add(TrueAnswerData.get(index));

        Random rand = new Random();

        for (int i = 0; i < MixAnswers.size(); i++) {
            int randomIndexToSwap = rand.nextInt(MixAnswers.size());
            String temp = MixAnswers.get(randomIndexToSwap);
            MixAnswers.set(randomIndexToSwap, MixAnswers.get(i));
            MixAnswers.set(i, temp);
        }

        setDefaultBoxes();

        Question.setText(QuestionData.get(index));
        AnswerA.setText("A-) "+ MixAnswers.get(0));
        AnswerB.setText("B-) "+ MixAnswers.get(1));
        AnswerC.setText("C-) "+ MixAnswers.get(2));
        AnswerD.setText("D-) "+ MixAnswers.get(3));

        level.setText("Level - "+(index+1));
        scoreTxt.setText(String.valueOf(score));
    }

}

