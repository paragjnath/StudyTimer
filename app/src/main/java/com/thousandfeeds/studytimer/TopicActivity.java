package com.thousandfeeds.studytimer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.thousandfeeds.studytimer.database.TopicsContract;
import com.thousandfeeds.studytimer.fragments.DoubtsFragment;
import com.thousandfeeds.studytimer.fragments.NotesFragment;
import com.thousandfeeds.studytimer.fragments.StepsFragment;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,StepsFragment.OnListFragmentInteractionListener ,
        DoubtsFragment.OnListFragmentInteractionListener, NotesFragment.OnListFragmentInteractionListener{

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Uri currentTopicUri;
    private String currentTopicId;
    private TextView toolbarTitle;
    private Bundle mBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Intent intent = getIntent();
        currentTopicUri = intent.getData();
        currentTopicId = currentTopicUri.getLastPathSegment();

        mBundle = new Bundle();
        mBundle.putString("TOPIC-ID",currentTopicId);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        getSupportLoaderManager().initLoader(0, null, this);


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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // This loader will execute the ContentProvider's query method on a background thread
        String[] projection = {
                TopicsContract.TopicsTable._ID,
                TopicsContract.TopicsTable.COLUMN_TOPIC_TITLE,
                TopicsContract.TopicsTable.COLUMN_TOPIC_TIME_STAMP,
        };


        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                currentTopicUri,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {

            return;
        }

        if(cursor.moveToFirst()){

            // find the columns of the topic attributes
            int titleColumnIndex = cursor.getColumnIndex(TopicsContract.TopicsTable.COLUMN_TOPIC_TITLE);
            //int timeStampColumnIndex = cursor.getColumnIndex(TopicsContract.TopicsTable.COLUMN_TOPIC_TIME_STAMP);

            //get data from the columns
            String title = cursor.getString(titleColumnIndex);
            //long timeStamp = cursor.getLong(timeStampColumnIndex);

            toolbarTitle.setText(title);
            Toast.makeText(getApplicationContext(),"notnonono",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

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

            //send the current topic id to fragment
            fragment.setArguments(mBundle);
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
