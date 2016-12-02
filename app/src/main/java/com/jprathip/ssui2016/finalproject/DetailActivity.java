package com.jprathip.ssui2016.finalproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jprathip.ssui2016.finalproject.Database.Question;

import io.realm.Realm;

public class DetailActivity extends Activity {


    private Question mQuestion;
    private TextView mTitleTextView;
    private FloatingActionButton mFloatingActionButton;
    private Boolean mIsEditing;
    private Boolean mIsNew;
    private EditFragment mEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        mTitleTextView = (TextView) findViewById(R.id.activity_title);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_button);


        mIsNew = getIntent().getExtras().getBoolean(getResources().getString(R.string.add_key));

        mIsEditing = mIsNew;

        if(mIsEditing == true) {
            if(mIsNew) {
                mQuestion = new Question(true);
            }
        }
        else {
            //deal with this
        }
        mTitleTextView.setText(mQuestion.getActivity() + " At " + "TODO");



        if(mIsEditing) {
            mEditFragment = new EditFragment();
            mEditFragment.mQuestion = mQuestion;
            getFragmentManager().beginTransaction().replace(R.id.FragmentContainer,
                    mEditFragment, "stuff").commit();

            mFloatingActionButton.setImageResource(R.mipmap.ic_navigation_check);

        }
        else {
            QuestionFragment myFrag = new QuestionFragment();
            myFrag.mQuestion = mQuestion;
            getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, myFrag, "stuff").commit();
            mFloatingActionButton.setImageResource(R.mipmap.ic_image_edit);
        }


        mFloatingActionButton.setOnClickListener(FABClick);

    }


    private View.OnClickListener FABClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(mIsEditing  && mIsNew) {
                mEditFragment.save();
                mQuestion = mEditFragment.mQuestion;
                Realm realm = RealmController.with(DetailActivity.this).getRealm();
                RealmController.with(DetailActivity.this).getQuestions();
                realm.beginTransaction();
                    realm.copyToRealm(mQuestion);
                realm.commitTransaction();

            }
            finish();
        }
    };





}
