package com.starwar.tourplan.beans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MySqliteOpenHelper;
import com.starwar.tourplan.utils.ShowAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowBills extends AppCompatActivity {

    private ListView lv_show_bills;
    private MySqliteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);

        lv_show_bills = (ListView)findViewById(R.id.lv_show_bills);
        FloatingActionButton btn_clear_bills = (FloatingActionButton) findViewById(R.id.btn_clear_bills);

        helper = new MySqliteOpenHelper(this);

        initView();




    }

    private void initView() {

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("everynote", new String[]{"name","cost"}, null, null, null, null, null);


        List<NoteInfo> billList = new ArrayList<>();

        if (cursor != null && cursor.getCount() >0){

            while (cursor.moveToNext()){


                String name = cursor.getString(0);
                String cost = cursor.getString(1);

                NoteInfo noteInfo = new NoteInfo();

                noteInfo.setBillname(name);
                noteInfo.setCost(cost);

                billList.add(noteInfo);


            }
            db.close();
        }

        ShowAdapter adapter = new ShowAdapter(this, billList);
        lv_show_bills.setAdapter(adapter);


    }


    public void clear(View view) {

        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("delete from everynote");
        db.close();
        initView();
    }
}