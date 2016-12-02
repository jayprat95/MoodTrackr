package com.jprathip.ssui2016.finalproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jprathip.ssui2016.finalproject.Database.Question;
import com.jprathip.ssui2016.finalproject.R;

/**
 * Created by jayanthprathipati on 12/1/16.
 */

public class QuestionDetailAdapter extends BaseAdapter {



    private Question mQuestion;
    private Context mContext;


    public QuestionDetailAdapter(Context context, Question data) {

       super();
        mQuestion = data;
        mContext = context;
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return mQuestion;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        if( convertView == null ){
            //We must create a View:
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }


        TextView question = (TextView) convertView.findViewById(android.R.id.text1);
        TextView detail = (TextView) convertView.findViewById(android.R.id.text2);


        switch (position) {
            case 0:
                question.setText(R.string.hello_blank_fragment);
                detail.setText(getFeelingText(mQuestion.getMood()));
                break;
            case 1:
                question.setText(R.string.focus_question);
                detail.setText(getFeelingText(mQuestion.getMood()));
                break;
            case 2:
                question.setText(R.string.activity_question);
                detail.setText(mQuestion.getActivity());
                break;
            case 3:
                question.setText(R.string.alone_question);
                String yesOrNo = mQuestion.isAlone() == true ? "Yes" : "No";
                detail.setText(yesOrNo);
                break;
            case 4:
                question.setText(R.string.location_question);
                detail.setText("TODO");
                break;

        }

        return convertView;
    }


    private String getFeelingText(int num) {
        String s = "";
        switch (num) {
            case 0:
                s = "Very Unhappy";
                break;
            case 1:
                s = "Unhappy";
                break;
            case 2:
                s = "Neutral";
                break;
            case 3:
                s = "Happy";
                break;
            case 4:
                s = "Very Happy";
                break;

        }
        return s;
    }

    private String getFocusedTest(int num) {
        String s = "";
        switch (num) {
            case 0:
                s = "Very Unfocused";
                break;
            case 1:
                s = "Unfocused";
                break;
            case 2:
                s = "Neutral";
                break;
            case 3:
                s = "Focused";
                break;
            case 4:
                s = "Very Focused";
                break;

        }
        return s;
    }

}
