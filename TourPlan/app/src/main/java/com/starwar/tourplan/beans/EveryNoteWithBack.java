package com.starwar.tourplan.beans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.starwar.tourplan.R;

public class EveryNoteWithBack extends AppCompatActivity {

    private Button btn_back;
    private Button btn_little_plan;
    private Button btn_bill;
    private BottomNavigationView bom_navig;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_note_with_back);

        initView();

        bom_navig.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                fragment = null;
                switch (menuItem.getItemId()){

                    case R.id.bom_btn_bills:
                        Intent intent = new Intent(EveryNoteWithBack.this,Bills.class);
                        startActivity(intent);
                        break;
                    case R.id.bom_btn_suggestion:
                        fragment = new Suggestion();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout,fragment).commit();
                        break;
                }
                return true;
            }
        });



    }

    private void initView() {
        bom_navig = (BottomNavigationView) findViewById(R.id.bom_navig);
    }









}