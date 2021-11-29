package com.example.brainics;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class relaxingmusic extends AppCompatActivity {

    VideoView videoview1;
    VideoView videoview2;
    VideoView videoview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxingmusic);

        videoview1 = findViewById(R.id.video1);
        MediaController mediacontroller1 = new MediaController(this);
        mediacontroller1.setMediaPlayer(videoview1);
        videoview1.setMediaController(mediacontroller1);
        videoview1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.coffeejazz));


        videoview2 = findViewById(R.id.video2);
        MediaController mediacontroller2 = new MediaController(this);
        mediacontroller2.setMediaPlayer(videoview2);
        videoview2.setMediaController(mediacontroller2);
        videoview2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alwaves));

        videoview3 = findViewById(R.id.video3);
        MediaController mediacontroller3 = new MediaController(this);
        mediacontroller3.setMediaPlayer(videoview3);
        videoview3.setMediaController(mediacontroller3);
        videoview3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nativeamerican));


    }
}