package com.thousandfeeds.studytimer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

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
    private Uri currentTopicUri;
    private String currentTopicId;
    private TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Intent intent = getIntent();
        currentTopicUri = intent.getData();
        currentTopicId = currentTopicUri.getLastPathSegment();
        Toast.makeText(this,currentTopicId,Toast.LENGTH_SHORT).show();

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.addFragment( new StepsFragment(),"Steps");
        viewPagerAdapter.addFragment( new NotesFragment(),"Notes");
        viewPagerAdapter.addFragment( new DoubtsFragment(),"Doubts");


    }

    @Override
    public void onListFragmentInteraction(Uri uri) {

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
