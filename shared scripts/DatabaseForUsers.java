package com.example.knocomy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseForUsers extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE = "userInfo";
    public static final String ROW_NICK = "nick";
    public static final String ROW_PSSWD = "password";
    public static final String ROW_SCORE = "score";


    public DatabaseForUsers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE + " ("+ROW_NICK+" TEXT, "+ROW_PSSWD+" TEXT, "+ROW_SCORE+" TEXT DEFAULT '0' )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        db.close();
        onCreate(db);
    }

    public void addData(String nick, String psswd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_NICK, nick.trim());
        cv.put(ROW_PSSWD, psswd.trim());
        db.insert(USER_TABLE, null, cv);
        db.close();
    }


    public String searchPsswd(String nick, int columnIndex)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT nick, password from userInfo";
        Cursor cursor = db.rawQuery(query, null);

        String a,b;

        b = "Not Found";

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(nick))
                {
                    b = cursor.getString(columnIndex);
                    break;
                }
            }while (cursor.moveToNext());


        }
        return b;
    }

    public List<String> pullData(int id)
    {
        List<String> table = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT nick, score FROM userInfo ORDER BY score DESC LIMIT 10";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                    table.add(cursor.getString(id));
            }while(cursor.moveToNext());
        }

        return table;

    }

    public void upgradeData(String nick, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT score FROM userInfo Where nick = "+"'"+nick+"'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if(Integer.parseInt(cursor.getString(0)) < score)
        {
            String query2 = "UPDATE userInfo SET score = "+"'"+score+"' "+ "WHERE nick = "+"'"+nick+"'";
            db.execSQL(query2);
        }
        db.close();
    }
}
