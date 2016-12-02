package com.jprathip.ssui2016.finalproject.Adapters;

import android.content.Context;

import com.jprathip.ssui2016.finalproject.Database.Question;

import io.realm.RealmResults;

/**
 * Created by jayanthprathipati on 11/16/16.
 */

public class RealmQuestionAdapter extends RealmModelAdapter<Question> {

    public RealmQuestionAdapter(Context context, RealmResults<Question> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }

}
