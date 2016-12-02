package com.jprathip.ssui2016.finalproject.Database;

import com.jprathip.ssui2016.finalproject.Generator;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jayanthprathipati on 11/15/16.
 */

public class Question extends RealmObject {



    public Question() {

    }


    public Question(Boolean create) {
        this();
        setId(System.currentTimeMillis());
        setFocus(2);
        setMood(2);
        setAlone(true);
        setPersonInContact("Foo");
        setActivity("Studying");
    }

    @PrimaryKey
    private long id;

    private int focus;

    private int mood;

    private boolean isAlone;

    private String personInContact;


    private String activity;


    public String getPersonInContact() {
        return personInContact;
    }

    public void setPersonInContact(String personInContact) {
        this.personInContact = personInContact;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public boolean isAlone() {
        return isAlone;
    }

    public void setAlone(boolean alone) {
        isAlone = alone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }







}
