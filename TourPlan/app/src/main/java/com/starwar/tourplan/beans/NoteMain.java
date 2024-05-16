package com.starwar.tourplan.beans;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MyAdapter;
import com.starwar.tourplan.utils.MySqliteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NoteMain extends AppCompatActivity {


    private MySqliteOpenHelper helper;
    private ListView lv_content;
    private Intent intent;
    private SwipeRefreshLayout swipe_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_main);



        lv_content = findViewById(R.id.lv_content);

        helper = new MySqliteOpenHelper(this);

        initView();

        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        pullrefresh();

    }




    private void pullrefresh(){

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("tourinfo",new String[]{"daynum","name","text"},null,null ,null,null,null);

       List<NoteInfo> noteList = new ArrayList<>();

       if (cursor != null && cursor.getCount() >0){

           while (cursor.moveToNext()){

               String daynum = cursor.getString(0);
               String name = cursor.getString(1);

               NoteInfo noteInfo = new NoteInfo();
               noteInfo.setName(name);
               noteInfo.setTime(daynum);
               noteList.add(noteInfo);

           }


       }

        MyAdapter adapter = new MyAdapter(NoteMain.this, noteList);
        lv_content.setAdapter(adapter);
        cursor.close();
        db.close();
        swipe_refresh.setRefreshing(false);

            }


        });



    }







    private void initView() {
        FloatingActionButton btn_create = (FloatingActionButton) findViewById(R.id.btn_create);
        View view_botm = findViewById(R.id.view_botm);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteMain.this, CreateOP.class);

                startActivity(intent);
            }
        });


//        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            private String text;
//            private String name;
//            private String daynum;
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//
//
//                SQLiteDatabase db = helper.getReadableDatabase();
//                Cursor cursor = db.query("tourinfo",new String[]{"daynum","name","text"},null,null ,null,null,null);
//                if (cursor != null && cursor.getCount()>0){
//                    while (cursor.moveToNext()){
//                        daynum = cursor.getString(0);
//                        name = cursor.getString(1);
//                        text = cursor.getString(2);
//
//                    }
//
//                }
//                db.execSQL("delete from tourinfo where daynum =?",new String[]{daynum});
//                db.execSQL("delete from tourinfo where name =?",new String[]{name});
//                int result = db.delete("tourinfo", "text = ?", new String[]{text});
//
//                if (result > 0)
//                    Toast.makeText(NoteMain.this, "success", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(NoteMain.this, "failed", Toast.LENGTH_SHORT).show();
//                cursor.close();
//                db.close();
//
//                return false;
//            }
//        });


        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(NoteMain.this, ShowNote.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });



    }


    public void clearDB(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("delete from tourinfo");

        Toast.makeText(this, "已清空", Toast.LENGTH_SHORT).show();

        /*刷新一下*/

        Cursor cursor = db.query("tourinfo",new String[]{"daynum","name","text"},null,null ,null,null,null);

        List<NoteInfo> noteList = new ArrayList<>();

        if (cursor != null && cursor.getCount() >0){

            while (cursor.moveToNext()){

                String daynum = cursor.getString(0);
                String name = cursor.getString(1);

                NoteInfo noteInfo = new NoteInfo();
                noteInfo.setName(name);;
                noteInfo.setTime(daynum);
                noteList.add(noteInfo);

            }


        }

        MyAdapter adapter = new MyAdapter(NoteMain.this, noteList);
        lv_content.setAdapter(adapter);
        cursor.close();
        db.close();
    }
}
