package com.jprathip.ssui2016.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our mViewPager
    private ViewPager mViewPager;

    //Fragments
    private ListFragment mListFragment;


    private FloatingActionButton mFABButton;

    String[] mTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);


        mTabTitle = getResources().getStringArray(R.array.titles);


        mFABButton = (FloatingActionButton) findViewById(R.id.addButton);
        mFABButton.setOnClickListener(mAddClickListener);

        //Initializing mViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        setupViewPager(mViewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

        try
        {
            setupTabIcons();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






    }


    

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.action_status:
//                Toast.makeText(this, "Home Status Click", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_settings:
//                Toast.makeText(this, "Home Settings Click", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_with_icon:
//                Intent withicon=new Intent(this,TabWithIconActivity.class);
//                startActivity(withicon);
//                finish();
//                return true;
//            case R.id.action_without_icon:
//                Intent withouticon=new Intent(this,TabWOIconActivity.class);
//                startActivity(withouticon);
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    private View.OnClickListener mAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);

            i.putExtra(getResources().getString(R.string.add_key), true);

            startActivity(i);
//            int[] values = new int[2];
//            view.getLocationOnScreen(values);
//
//            DisplayMetrics dm = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(dm);
//            int w = dm.widthPixels;
//            int h = dm.heightPixels;
//
//
//            startActivity(i, optionsCompat.toBundle());
        }
    };

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mListFragment = new ListFragment();
        GraphFragment listFragment2 = new GraphFragment();
        adapter.addFragment(mListFragment,"LIST");
        adapter.addFragment(listFragment2,"GRAPH");
        viewPager.setAdapter(adapter);
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(mTabTitle[pos]);

        return view;
    }

    private void setupTabIcons()
    {

        for(int i = 0; i< mTabTitle.length; i++)
        {
            /*TabLayout.Tab tabitem = tabLayout.newTab();
            tabitem.setCustomView(prepareTabView(i));
            tabLayout.addTab(tabitem);*/

            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }


    }
}
