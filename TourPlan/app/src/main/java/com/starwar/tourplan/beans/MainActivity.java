package com.starwar.tourplan.beans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MyToast;


public class MainActivity extends AppCompatActivity{


    private EditText et_username;
    private EditText et_pd;
    private ImageButton btn_login;
    private CheckBox cb_rm;
    private SharedPreferences sp;
    private Button btn_everynote;
    private TextView tv_everynote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_everynote = findViewById(R.id.btn_everynote);

        sp = getSharedPreferences("login", Context.MODE_PRIVATE);


        initView();

        showUserInfo();


    }
    private void showUserInfo() {
        String username = sp.getString("username", "");
        String pw = sp.getString("pw", "");
        boolean isChecked = sp.getBoolean("isChecked", false);
        et_username.setText(username);
        et_pd.setText(pw);
        cb_rm.setChecked(isChecked);

    }




    private void initView(){
        et_username = (EditText) findViewById(R.id.et_username);
        et_pd = (EditText)findViewById(R.id.et_pd);
        btn_login = (ImageButton)findViewById(R.id.btn_login);
        cb_rm = (CheckBox) findViewById(R.id.cb_rm);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString().trim();
                String pw = et_pd.getText().toString().trim();


                if(username.isEmpty() || TextUtils.isEmpty(pw)){
                    Toast.makeText(MainActivity.this, "username or password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;

                }
                boolean isChecked = cb_rm.isChecked();
                if (!isChecked ){/*!是取反的意思*/
                    Toast.makeText(MainActivity.this, "plz remember urself", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*sp操作*/
                boolean isSuccess = saveUserInfo(username,pw,isChecked);
                if (isSuccess){
                    MyToast.show(MainActivity.this,"SUCCESS");

                    /*页面跳转*/
                    Intent intent = new Intent(MainActivity.this, NoteMain.class);
                    startActivity(intent);

                }
                else{
                    MyToast.show(MainActivity.this,"Failed");
                }



            }

        });

        btn_everynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EveryNoteWithBack.class);
                startActivity(intent);
            }



        });


    }

    private boolean saveUserInfo(String username, String pw,boolean isChecked) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("pw",pw);
        editor.putBoolean("isChecked",isChecked);
        boolean isSuccess = editor.commit();

        return  isSuccess;
    }









}