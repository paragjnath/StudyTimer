package com.thousandfeeds.studytimer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thousandfeeds.studytimer.database.TopicsContract;
import com.thousandfeeds.studytimer.fragments.DoubtsFragment;
import com.thousandfeeds.studytimer.fragments.NotesFragment;
import com.thousandfeeds.studytimer.fragments.TopicsFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TopicsFragment.OnListFragmentInteractionListener,
        NotesFragment.OnListFragmentInteractionListener, DoubtsFragment.OnListFragmentInteractionListener {

    private BottomNavigationView mBotttomNav;
    private Button addButton;
    private EditText addTopicEditText;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new TopicsFragment();
        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.getTag());
            fragmentTransaction.commit();
        }

        addTopicEditText = (EditText) findViewById(R.id.addTopicEditText);

        addButton = (Button) findViewById(R.id.addTopicButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                Calendar c = Calendar.getInstance();
                long time = c.getTimeInMillis();
                values.put(TopicsContract.TopicsTable.COLUMN_TOPIC_TITLE, addTopicEditText.getText().toString());
                values.put(TopicsContract.TopicsTable.COLUMN_TOPIC_TIME_STAMP, time);
                Uri newUri = getContentResolver().insert(TopicsContract.TopicsTable.CONTENT_URI, values);

                Intent intent = new Intent(getApplicationContext(), TopicActivity.class);
                intent.setData(newUri);
                startActivity(intent);
            }
        });

        mBotttomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBotttomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });




    }

    private void selectFragment(MenuItem item){

        Fragment fragment = null;

        switch (item.getItemId()){

            case R.id.bottom_menu_topics:
                fragment = new TopicsFragment();
                break;

            case R.id.bottom_menu_notes:
                fragment = new NotesFragment();
                break;

            case R.id.bottom_menu_doubts:
                fragment = new DoubtsFragment();
                break;

        }

        mSelectedItem = item.getItemId();

        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.getTag());
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onListFragmentInteraction(Uri uri) {

        Intent intent = new Intent(this, TopicActivity.class);
        intent.setData(uri);
        startActivity( intent);
    }


}
