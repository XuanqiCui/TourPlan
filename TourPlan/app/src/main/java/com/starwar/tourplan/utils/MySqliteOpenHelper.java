package com.starwar.tourplan.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class MySqliteOpenHelper extends SQLiteOpenHelper {



    private static final String CREATE_TABLE_SQL_STATEMENT ="create table tourinfo (_id integer primary key autoincrement,daynum varchar(20),name varchar(20),text varchar(1000) )";
    private static final String CREATE_EVERYNOTE_TABLE = "create table everynote (_id integer primary key autoincrement, name varchar(20),cost varchar(20) )";


    public MySqliteOpenHelper(@Nullable Context context) {
        super(context,"tourplan.db",null,4);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_SQL_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_EVERYNOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_EVERYNOTE_TABLE);


    }


}
