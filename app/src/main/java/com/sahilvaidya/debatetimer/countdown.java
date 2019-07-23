package com.sahilvaidya.debatetimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class countdown {
    long remainingmillis;
    private Button butt;
    private CountDownTimer timer;
    boolean active;
    private String name;
    private EditText text;
    int resettime;

    protected countdown(long millis, Button b, EditText t, int reset){
        butt = b;
        remainingmillis = millis;
        name = butt.getText().toString();
        text = t;
        printHint(remainingmillis);
        resettime = reset;
    }

    protected void startTimer(){
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                printTime(remainingmillis);
                remainingmillis = millisUntilFinished;
                printHint(remainingmillis);
            }

            @Override
            public void onFinish() {
                text.setText("done!");
            }
        };
        timer.start();
    }

    protected void pauseTimer(){
        if(timer != null){
            timer.cancel();
        }
    }

    protected void resetTimer(){
        if(timer != null) {
            timer.cancel();
        }
        remainingmillis = (resettime * 60000);
        printHint(remainingmillis);
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                printTime(remainingmillis);
                remainingmillis = millisUntilFinished;
                printHint(remainingmillis);
            }

            @Override
            public void onFinish() {
                text.setText("done!");
            }
        };
        active = false;
        text.setText(name);
    }
    protected void setTimer(int mins, int secs){
        remainingmillis = ((mins * 60) + secs) * 1000;
        printTime(mins,secs);
        active = false;
    }
    public void setTime(int millis){
        remainingmillis = millis;
        printTime(remainingmillis);
    }

    public void printTime(long millis){
        text.setText("" + String.format("%02d",(remainingmillis / 60000)) + ":" + String.format("%02d",(remainingmillis / 1000) % 60));
    }
    public void printTime(int mins, int secs){
        text.setText("" + String.format("%02d",mins) + ":" + String.format("%02d",secs));
    }
    public void printHint(long millis){
        text.setHint("" + String.format("%02d",(remainingmillis / 60000)) + ":" + String.format("%02d",(remainingmillis / 1000) % 60));
    }
}
