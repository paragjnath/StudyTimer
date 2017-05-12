package com.thousandfeeds.studytimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button buttonAddNewTopic;
    LinearLayout topicLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonAddNewTopic = (Button) findViewById(R.id.button_add_topic);
        buttonAddNewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StudyTimerHome.class);
                startActivity(intent);
            }
        });

        topicLayout = (LinearLayout) findViewById(R.id.topicLayout);
        topicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TopicActivity.class);
                startActivity(intent);
            }
        });



    }
}
