package com.starwar.tourplan.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.SuggestionsInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.FragmentActivity;

import com.starwar.tourplan.R;

import java.util.ArrayList;
import java.util.List;


public class SuggestionAdapter extends BaseAdapter {

    private Context context;
    private int resource;

    private int textViewResourceId ;
    private  int imageViewResourceId;
    private ArrayList<SuggestionInfo> suggestionList;


    public SuggestionAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @IdRes int imageViewResourceId, ArrayList<SuggestionInfo> suggestionList) {
        this.context = context;
        this.resource = resource;

        this.textViewResourceId =textViewResourceId;
        this.imageViewResourceId = imageViewResourceId;
        this.suggestionList = suggestionList;

    }


    @Override
    public int getCount() {
        return   suggestionList.size();
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
            convertView = View.inflate(this.context, this.resource, null);

                TextView tv_content =(TextView) convertView.findViewById(this.textViewResourceId);
                ImageView iv_suggestion = (ImageView) convertView.findViewById(this.imageViewResourceId);

                tv_content.setText(this.suggestionList.get(position).getName());
                iv_suggestion.setImageResource(this.suggestionList.get(position).getImgId());

        }
        return convertView;
    }
}
