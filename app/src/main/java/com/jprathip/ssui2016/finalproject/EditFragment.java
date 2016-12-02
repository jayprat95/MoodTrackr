package com.jprathip.ssui2016.finalproject;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.jprathip.ssui2016.finalproject.Database.Question;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    public Question mQuestion;
    private SeekBar mMoodbar;
    private SeekBar mFocusBar;
    private Switch mAloneToggle;
    private Spinner mActivitySpinner;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        mMoodbar = (SeekBar) view.findViewById(R.id.mood_bar);
        mFocusBar = (SeekBar) view.findViewById(R.id.focus_bar);
        mAloneToggle = (Switch) view.findViewById(R.id.alone_toggle);
        mActivitySpinner = (Spinner) view.findViewById(R.id.activity_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.activities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mActivitySpinner.setAdapter(adapter);




        if(mQuestion != null) {
            mMoodbar.setProgress(mQuestion.getMood());
            mFocusBar.setProgress(mQuestion.getFocus());
            mAloneToggle.setChecked(mQuestion.isAlone());
            mActivitySpinner.setSelection(getIndex(mActivitySpinner, mQuestion.getActivity()));
        }


        mAloneToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mQuestion.setAlone(b);
            }
        });


        return view;
    }



    //private method of your class
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    private String getItem(Spinner spinner, int index) {
        return spinner.getItemAtPosition(index).toString();
    }


    public void save() {
        mQuestion.setMood(mMoodbar.getProgress());
        mQuestion.setFocus(mMoodbar.getProgress());
        mQuestion.setActivity(getItem(mActivitySpinner,
                mActivitySpinner.getSelectedItemPosition()));
    }


}
