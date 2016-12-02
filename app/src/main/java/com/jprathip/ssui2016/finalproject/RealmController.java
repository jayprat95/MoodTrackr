package com.jprathip.ssui2016.finalproject;

/**
 * Created by jayanthprathipati on 11/15/16.
 */

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.jprathip.ssui2016.finalproject.Database.Question;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Question.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<Question> getQuestions() {

        return realm.where(Question.class).findAll();
    }

    //query a single item with the given id
    public Question getQuestion(Long id) {

        return realm.where(Question.class).equalTo("id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean hasQuestions() {

        return !realm.allObjects(Question.class).isEmpty();
    }


}
