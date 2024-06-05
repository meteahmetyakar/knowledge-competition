package com.example.knocomy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

    Button sgnIn;
    EditText textOfNick;
    EditText textOfPsswd;
    EditText confirmPsswd;
    Button backBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sgnIn = findViewById(R.id.button2);
        textOfNick = findViewById(R.id.nickText);
        textOfPsswd = findViewById(R.id.psswdText);
        confirmPsswd = findViewById(R.id.confirmPsswd);
        backBtnRegister = findViewById(R.id.backBtnRegister);



        sgnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseForUsers database = new DatabaseForUsers(register.this);
                String nick = textOfNick.getText().toString();
                String psswd = textOfPsswd.getText().toString();
                String cnfrmPsswd = confirmPsswd.getText().toString();


                if(!nick.trim().equals(""))
                {
                    if(!psswd.trim().equals(""))
                    {
                        if(!psswd.equals(cnfrmPsswd))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                            builder.setMessage("You have entered incomplete or incorrectly, please check your password and verification.").show();
                        }
                        else
                        {
                            String nick2 = database.searchPsswd(nick, 0);
                            if(nick.equals(nick2))
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                builder.setMessage("This nickname is used.").show();
                            }
                            else
                            {
                                database.addData(nick,psswd);

                                textOfNick.setText("");
                                textOfPsswd.setText("");
                                confirmPsswd.setText("");
                                AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                builder.setMessage("Successful").show();
                                Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(toMainActivity);
                            }
                        }
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                        builder.setMessage("Please enter a valid password.").show();
                    }
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage("Please enter a valid nick.").show();
                }
            }
        });

        backBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMainActivity);
            }
        });

    }

    

}