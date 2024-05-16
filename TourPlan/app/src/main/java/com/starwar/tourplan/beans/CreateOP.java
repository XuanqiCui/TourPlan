package com.starwar.tourplan.beans;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.starwar.tourplan.utils.TourNameInfo;

import androidx.appcompat.app.AppCompatActivity;

import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MySqliteOpenHelper;

public class CreateOP extends AppCompatActivity {


    private MySqliteOpenHelper helper;
    private TourNameInfo tourNameInfo;
    private EditText et_tourname;
    private EditText et_daynum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_main);


        helper = new MySqliteOpenHelper(this);
        tourNameInfo = new TourNameInfo();


        initView();


    }

    private void initView(){

        Button btn_shoot = (Button) findViewById(R.id.btn_shoot);

        et_tourname = findViewById(R.id.et_tourname);
        et_daynum = (EditText) findViewById(R.id.et_daynum);

    }

    public void addtimeandname(View view){


        String daynum = et_daynum.getText().toString().trim();
        String tourname = et_tourname.getText().toString().trim();
        String tourname2 = et_tourname.getText().toString().trim();
        String text = "1";

        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("daynum",daynum);
        values.put("name",tourname);
        values.put("text",text);
        long result = db.insert("tourinfo", null, values);


        if(result > 0){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
        db.close();


        Intent intent = new Intent(CreateOP.this,TourText.class);

        tourNameInfo.setTourname(tourname2.toString());
        intent.putExtra("tourname",tourNameInfo);

        startActivity(intent);


    }







}
