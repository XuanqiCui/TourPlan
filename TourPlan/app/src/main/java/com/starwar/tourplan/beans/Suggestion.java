package com.starwar.tourplan.beans;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.starwar.tourplan.R;
import com.starwar.tourplan.utils.SuggestionAdapter;
import com.starwar.tourplan.utils.SuggestionInfo;

import java.util.ArrayList;

public class Suggestion extends Fragment {


    private ListView lv_suggestion;
    private ArrayList<SuggestionInfo> suggestionList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_suggestion,container,false);
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv_suggestion = getActivity().findViewById(R.id.lv_suggestion);


        initView();

        SuggestionAdapter adapter = new SuggestionAdapter(getActivity(), R.layout.item_suggestion, R.id.tv_suggestion,R.id.iv_suggestion, suggestionList);
       lv_suggestion.setAdapter(adapter);


       lv_suggestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

               whichCity(position);
           }
       });

    }






    private void initView() {

        suggestionList = new ArrayList<>();
        suggestionList.add(new SuggestionInfo("BeiJing",R.mipmap.beijing));
        suggestionList.add(new SuggestionInfo("ChongQing",R.mipmap.chongqing));
        suggestionList.add(new SuggestionInfo("QingDao",R.mipmap.qingdao));
        suggestionList.add(new SuggestionInfo("Paris",R.mipmap.paris));
        suggestionList.add(new SuggestionInfo("LiaoYang",R.mipmap.liaoyang));
        suggestionList.add(new SuggestionInfo("Onomichi",R.mipmap.onomichi));
        suggestionList.add(new SuggestionInfo("Tokyo",R.mipmap.tokyo));
        suggestionList.add(new SuggestionInfo("New York",R.mipmap.newyork));
        suggestionList.add(new SuggestionInfo("L.A.",R.mipmap.la));

    }


    private void  whichCity(int position){
        Intent intent = null;
        switch (position){
            case 0:
                intent = new Intent(getActivity(),BeijingDetail.class);
                break;
            case 1:
                intent = new Intent(getActivity(),ChongQingDetail.class);
                break;
            case 2:
                intent =new Intent(getActivity(),QingdaoDetail.class);
                break;

        }
        startActivity(intent);

    }

}
