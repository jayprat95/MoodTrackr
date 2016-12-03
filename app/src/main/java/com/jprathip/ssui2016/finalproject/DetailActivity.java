package com.jprathip.ssui2016.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jprathip.ssui2016.finalproject.Database.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class DetailActivity extends Activity implements LocationListener {


    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;


    private Question mQuestion;
    private TextView mTitleTextView;
    private FloatingActionButton mFloatingActionButton;
    private Boolean mIsEditing;
    private Boolean mIsNew;
    private EditFragment mEditFragment;

    private LocationManager locationManager;
    private String provider;

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



        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(DetailActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(DetailActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(DetailActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else {

            // Get the location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
                onLocationChanged(location);
            } else {
            }


        }








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


    @Override
    public void onLocationChanged(Location location) {
        getAddressFromLocation(location);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    public void getAddressFromLocation(Location location) {

        Geocoder coder = new Geocoder(DetailActivity.this, Locale.getDefault());
        List<Address> address;

        try {
            address = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 3);
            address.size();
            mTitleTextView.setText(mQuestion.getActivity() + " At " + address.get(0).getThoroughfare());
        } catch (IOException e) {
            e.printStackTrace();
        }




    }




}
