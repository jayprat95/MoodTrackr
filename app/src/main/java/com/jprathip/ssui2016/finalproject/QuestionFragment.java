package com.jprathip.ssui2016.finalproject;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jprathip.ssui2016.finalproject.Adapters.QuestionDetailAdapter;
import com.jprathip.ssui2016.finalproject.Database.Question;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private ListView mListView;
    public Question mQuestion;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_question, container, false);


        mListView = (ListView) v.findViewById(R.id.list_view);
        QuestionDetailAdapter adapter = new QuestionDetailAdapter(getActivity(), mQuestion);
        mListView.setAdapter(adapter);

        return v;
    }

}
