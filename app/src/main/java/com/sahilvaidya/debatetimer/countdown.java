package com.sahilvaidya.debatetimer;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class countdown {
    private long totalmillis;
    private long remainingmillis;
    private Button butt;
    private CountDownTimer timer;
    boolean active;
    private String name;

    protected countdown(long millis, Button b){
        totalmillis = millis;
        butt = b;
        remainingmillis = millis;
        name = butt.getText().toString();
    }
    protected void onCreate() {
        active = false;
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!active){
                    startTimer();
                    active = true;
                } else {
                    pauseTimer();
                    active = false;
                }
            }
        });
        butt.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                resetTimer();
                return true;
            }
        });
    }

    protected void startTimer(){
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                butt.setText("" + (millisUntilFinished / 1000) / 60 + ":" + (millisUntilFinished / 1000) % 60);
                remainingmillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                butt.setText("done!");
            }
        };
        timer.start();
    }

    protected void pauseTimer(){
        timer.cancel();
    }

    protected void resetTimer(){
        if(timer != null) {
            timer.cancel();
        }
        remainingmillis = totalmillis;
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                butt.setText("" + (millisUntilFinished / 1000) / 60 + ":" + (millisUntilFinished / 1000) % 60);
                remainingmillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                butt.setText("done!");
            }
        };
        active = false;
        butt.setText(name);
    }
    protected void setTimer(int tensmins, int onesmins, int tenssecs, int onessecs){
        int mins = (tensmins * 10) + onesmins;
        int secs = (tenssecs * 10) + onessecs + (mins * 60);
        remainingmillis = secs * 60 * 10;
    }
}
