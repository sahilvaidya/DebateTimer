package com.sahilvaidya.debatetimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class countdown {
    private long totalmillis;
    long remainingmillis;
    private Button butt;
    private CountDownTimer timer;
    boolean active;
    private String name;
    private EditText text;

    protected countdown(long millis, Button b, EditText t){
        totalmillis = millis;
        butt = b;
        remainingmillis = millis;
        name = butt.getText().toString();
        text = t;
        text.setHint("" + String.format("%02d",getMins()) + ":" + String.format("%02d",getSecs()));
    }

    protected void startTimer(){
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                text.setText("" + String.format("%02d",getMins()) + ":" + String.format("%02d",getSecs()));
                remainingmillis = millisUntilFinished;
                text.setHint("" + String.format("%02d",getMins()) + ":" + String.format("%02d",getSecs()));
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
        remainingmillis = totalmillis;
        timer = new CountDownTimer(remainingmillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                text.setText("" + String.format("%02d",getMins()) + ":" + String.format("%02d",getSecs()));
                remainingmillis = millisUntilFinished;
                text.setHint("" + getMins() + ":" + getSecs());
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
        text.setText("" + String.format("%02d",mins) + ":" + String.format("%02d",secs));
        active = false;
    }
    public void setTime(int millis){
        remainingmillis = millis;
        text.setText("" + String.format("%02d",getMins()) + ":" + String.format("%02d",getSecs()));
    }
    public int getMins(){
        return (int)(remainingmillis / 1000) / 60;
    }
    public int getSecs(){
        return (int)(remainingmillis / 1000) % 60;
    }
}
