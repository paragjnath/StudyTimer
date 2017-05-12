package com.thousandfeeds.studytimer;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.thousandfeeds.studytimer.fragments.DoubtsFragment;
import com.thousandfeeds.studytimer.fragments.NotesFragment;
import com.thousandfeeds.studytimer.fragments.StepsFragment;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity implements StepsFragment.OnListFragmentInteractionListener ,
        DoubtsFragment.OnListFragmentInteractionListener, NotesFragment.OnListFragmentInteractionListener{

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.addFragment( new StepsFragment(),"Steps");
        viewPagerAdapter.addFragment( new NotesFragment(),"Notes");
        viewPagerAdapter.addFragment( new DoubtsFragment(),"Doubts");


    }

    @Override
    public void onListFragmentInteraction(Cursor cursor) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
