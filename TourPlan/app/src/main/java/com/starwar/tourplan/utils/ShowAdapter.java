package com.starwar.tourplan.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.starwar.tourplan.R;
import com.starwar.tourplan.beans.NoteInfo;

import java.util.List;

public class ShowAdapter extends BaseAdapter {

    private  Context context;
    private List<NoteInfo> billList;


    /*构造方法，传参用的*/
    public ShowAdapter(Context context, List<NoteInfo> billList) {
        this.context = context;
        this.billList = billList;
    }

    @Override
    public int getCount() {
        return billList.size();
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


            convertView =View.inflate(this.context, R.layout.item_show_bills,null);

            TextView tv_show_bills = (TextView)convertView.findViewById(R.id.tv_show_bills);
            TextView tv_bill_cost = (TextView) convertView.findViewById(R.id.tv_bill_cost);
            TextView tv_bill_yuan = (TextView)convertView.findViewById(R.id.tv_bill_yuan);

            tv_show_bills.setText(billList.get(position).getBillname());
            tv_bill_cost.setText(billList.get(position).getCost());

        }

        return convertView;
    }
}
