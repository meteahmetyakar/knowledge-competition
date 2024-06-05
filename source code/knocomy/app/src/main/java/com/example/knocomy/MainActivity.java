package com.example.knocomy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button registerBtn;
    Button lgnButton;
    EditText psswdText;
    EditText nickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        int isWarningShow = SharedPrefsUtils.getIntegerPreference(MainActivity.this, "isShow", 0);

        if(isWarningShow == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("");

            builder.setMessage("Emülatörden kaynaklı olarak bazı yerlerde taşmalar olabiliyor bunun için kusura bakmayın, iyi eğlenceler :)");
            builder.show();

            builder.setMessage("Merhaba, aşağıdan %100'ü seçerseniz daha yüksek çözünürlükte oynayabilirsiniz.");
            builder.show();

            SharedPrefsUtils.setIntegerPreference(MainActivity.this, "isShow", 1);
        }*/


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBtn = findViewById(R.id.button3);
        lgnButton = findViewById(R.id.lgnButton);
        psswdText = findViewById(R.id.psswdText);
        nickText = findViewById(R.id.nickText);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), register.class);
                startActivity(activity2Intent);
            }
        });

        lgnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseForUsers veritabani = new DatabaseForUsers(MainActivity.this);

                EditText a = findViewById(R.id.nickText);
                String nick = a.getText().toString();
                EditText b = findViewById(R.id.psswdText);
                String pass = b.getText().toString();

                String password = veritabani.searchPsswd(nick, 1);
                if(pass.equals(password))
                {
                    Intent toKonu = new Intent(getApplicationContext(), LoginMenu.class);
                    toKonu.putExtra("nick", nick);
                    startActivity(toKonu);
                }
                else
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainActivity.this);
                    dlgAlert.setTitle("ERROR!");
                    dlgAlert.setMessage("You have entered an invalid input or you are not register. If you are not registered, register from the register button.");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.show();
                }
            }
        });

    }

}