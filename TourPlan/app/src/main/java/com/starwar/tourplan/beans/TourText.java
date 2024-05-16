package com.starwar.tourplan.beans;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MySqliteOpenHelper;
import com.starwar.tourplan.utils.TourNameInfo;

public class TourText extends AppCompatActivity {


    private MySqliteOpenHelper helper;
    private EditText et_start_write;

    private FloatingActionButton btn_save;

    private FloatingActionButton btn_delete;
    private ContentValues values;
    private SQLiteDatabase db;
    private TextView tv_your_tourname;
    private String tourname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tourtext);

        initView();

        helper = new MySqliteOpenHelper(this);



        Intent intent = getIntent();
        TourNameInfo tourNameInfo = (TourNameInfo) intent.getParcelableExtra("tourname");
        tourname = tourNameInfo.getTourname();
        tv_your_tourname.setText(tourname);

    }

    private  void initView(){


        et_start_write = findViewById(R.id.et_start_write);
        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);
        tv_your_tourname = findViewById(R.id.tv_your_tourname);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = et_start_write.getText().toString().trim();
                if(input.isEmpty()){
                    Toast.makeText(TourText.this, "文本不能空啊", Toast.LENGTH_SHORT).show();
                }
                else {
                    addData(input);
                }

            }
        });

    }
    private void addData(String content){
        db = helper.getReadableDatabase();
        values = new ContentValues();
        values.put("text",content);

        int result = db.update("tourinfo", values, "text = ?",new String[]{"1"});

        if (result > 0){
            Toast.makeText(this, "文本已存入数据库", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "完了，寄", Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    public void deleteplan(View view){

        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("delete from tourinfo where name = ? ", new String[]{tourname});
        db.close();

    }

}
