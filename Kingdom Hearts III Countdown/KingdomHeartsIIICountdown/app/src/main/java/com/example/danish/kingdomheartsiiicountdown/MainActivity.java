package com.example.danish.kingdomheartsiiicountdown;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond;
    private Handler handler;
    private Runnable runnable;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.dearlybeloved);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 50, 0);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        txtTimerDay = (TextView) findViewById(R.id.txt_TimerDay);
        txtTimerHour = (TextView) findViewById(R.id.txt_TimerHour);
        txtTimerMinute = (TextView) findViewById(R.id.txt_TimerMinute);
        txtTimerSecond = (TextView) findViewById(R.id.txt_TimerSecond);
        countDownStart();
    }

    public void countDownStart(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                try {
                    Date futureDate = dateFormat.parse("01-29-2019");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        String day = Long.toString(days);
                        String hour = Long.toString(hours);
                        String minute = Long.toString(minutes);
                        String second = Long.toString(seconds);
                        txtTimerDay.setText(day);
                        txtTimerHour.setText(hour);
                        txtTimerMinute.setText(minute);
                        txtTimerSecond.setText(second);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public void textViewGone(){
        findViewById(R.id.linearLayout1).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);


    }


}
