package com.starwar.tourplan.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.starwar.tourplan.R;
import com.starwar.tourplan.beans.NoteInfo;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private  Context context;
    private List<NoteInfo> noteList;

    public  MyAdapter(){

    }



    public MyAdapter(Context context, List<NoteInfo> noteList) {
        this.context =context;
        this.noteList =noteList;

    }

    @Override
    public int getCount() {
        return noteList.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (convertView == null){

            convertView =View.inflate(this.context, R.layout.item_note,null);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            tv_name.setText(this.noteList.get(position).getName());
            tv_time.setText(this.noteList.get(position).getTime());

        }

        return  convertView;
    }
}
