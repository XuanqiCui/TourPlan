package com.starwar.tourplan.beans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.MySqliteOpenHelper;

public class Bills extends AppCompatActivity {

    private ListView lv_bills;
    private TextView tv_bills_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        initView();

    }

    private void initView() {
        tv_bills_title = findViewById(R.id.tv_bills_title);
        lv_bills = findViewById(R.id.lv_bills);
    }

    public void newItem(View view) {

        lv_bills.setAdapter(new EveryAdapter());
    }

    public void showbills(View view) {

        Intent intent = new Intent(Bills.this, ShowBills.class);
        startActivity(intent);

    }


    private class EveryAdapter extends BaseAdapter{

        private View convertView;

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parents) {
            if(convertView == null){

                convertView = View.inflate(Bills.this, R.layout.item_bills,null);
            }


            Button btn_bill_save = (Button)convertView.findViewById(R.id.btn_bill_save);
            EditText et_bill_name = (EditText) convertView.findViewById(R.id.et_bill_name);
            EditText et_bill_cost =(EditText) convertView.findViewById(R.id.et_bill_cost);

            btn_bill_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    MySqliteOpenHelper helper = new MySqliteOpenHelper(Bills.this);

                        String billname = et_bill_name.getText().toString().trim();
                        String billcost = et_bill_cost.getText().toString().trim();

                    SQLiteDatabase db =helper.getReadableDatabase();
                        ContentValues values = new ContentValues();

                        values.put("name",billname);
                        values.put("cost",billcost);
                        long result = db.insert("everynote", null, values);

                        if(result > 0){
                            Toast.makeText(Bills.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Bills.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                }
            });

            return convertView;
        }

    }
}