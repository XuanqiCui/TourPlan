package com.starwar.tourplan.beans;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MySqliteOpenHelper;

import java.nio.charset.StandardCharsets;

public class ShowNote extends AppCompatActivity {


    private MySqliteOpenHelper helper;
    private String text;
    private SQLiteDatabase db;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shownote);
        tv_show = (TextView) findViewById(R.id.tv_show);


        Intent intent = getIntent();
        int position = (Integer)intent.getIntExtra("position",0);

        helper =new MySqliteOpenHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tourinfo", new String[]{"daynum","name","text"},null,null,null,null,null);
        if (cursor != null && cursor.getCount() > 0){

                cursor.moveToFirst();
                cursor.moveToPosition(position);
                text = cursor.getString(2);
                tv_show.setText(text);
                cursor.close();
                db.close();
        }


    }
}
