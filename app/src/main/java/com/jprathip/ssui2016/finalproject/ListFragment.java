package com.jprathip.ssui2016.finalproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jprathip.ssui2016.finalproject.Adapters.QuestionAdapter;
import com.jprathip.ssui2016.finalproject.Adapters.RealmQuestionAdapter;
import com.jprathip.ssui2016.finalproject.Database.Question;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jayanthprathipati on 11/30/16.
 */

public class ListFragment extends Fragment {

    private Realm realm;
    private RecyclerView recycler;
    private QuestionAdapter adapter;

    public ListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.list_layout, container, false);



        recycler = (RecyclerView) view.findViewById(R.id.recycler);


        //get realm instance
        this.realm = RealmController.with(getActivity()).getRealm();


        setupRecycler();

        setRealmData();


        // refresh the realm instance
        RealmController.with(getActivity()).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(getActivity()).getQuestions());

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }





    public void setRealmAdapter(RealmResults<Question> questions) {

        RealmQuestionAdapter realmAdapter = new RealmQuestionAdapter(getActivity().getApplicationContext(), questions, true);
        // Set the data and tell the RecyclerView to draw
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler.setHasFixedSize(true);

        // use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // create an empty adapter and add it to the recycler view
        adapter = new QuestionAdapter(getActivity());
        recycler.setAdapter(adapter);
    }



    private void setRealmData() {

        ArrayList<Question> questions = new ArrayList<>();


        for (Question question : questions) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(question);
            realm.commitTransaction();
        }

    }


}
