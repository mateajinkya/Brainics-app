package com.example.brainics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton button;
    ImageButton button4;
    ImageButton button6;
    ImageButton button7;
    Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BrainicsScheduler.class);
                startActivity(intent);
            }
        });

        button4 = findViewById(R.id.imageButton4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View d) {
                Intent intent2 = new Intent(getApplicationContext(), BrainicsStudyTips.class);
                startActivity(intent2);
            }
        });

        button5 = findViewById(R.id.brainicsbutton);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View i) {
                Intent intent3 = new Intent(getApplicationContext(), meetbrainics.class);
                startActivity(intent3);
            }
        });

        button6 = findViewById(R.id.musicbutton);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View j) {
                Intent intent4 = new Intent(getApplicationContext(), relaxingmusic.class);
                startActivity(intent4);
            }
        });

        button7 = findViewById(R.id.imageButton3);
        button7.setOnClickListener(new View.OnClickListener() {
           public void onClick(View h) {
               Intent intent7 = new Intent(getApplicationContext(), pomodorotimer.class);
               startActivity(intent7);
           }
        });
    }
}