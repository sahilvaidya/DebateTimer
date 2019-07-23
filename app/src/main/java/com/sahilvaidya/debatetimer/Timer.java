package com.sahilvaidya.debatetimer;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class Timer extends AppCompatActivity {
    Button butt;
    countdown cd;
    int time;
    EditText text;
    boolean clear;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        butt = (Button) findViewById(R.id.timer);
        text = (EditText) findViewById(R.id.currenttime);
        cd = new countdown(time, butt, text, 8);
        clear = true;
        cd.setTime(time);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (!cd.active) {
                    cd.startTimer();
                    cd.active = true;
                    vib.vibrate(200);
                } else {
                    cd.pauseTimer();
                    cd.active = false;
                    vib.vibrate(100);
                }

            }
        });
        butt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cd.resetTimer();
                clear = true;
                return true;
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd.pauseTimer();
//                Log.e("Edit",text.getText().toString());
//                text.getText().clear();
//                Log.e("EditText",text.getText().toString());
            }
        });
        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(clear){
                    text.getText().clear();
                }
                clear = false;
                return false;
            }
        });

        text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    //Clear focus here from edittext
                    text.clearFocus();
                    String val = text.getText().toString();
                    int mins;
                    int secs;
                    if(val.equals("")){
                        mins = (int) cd.remainingmillis / 60000;
                        secs = (int) (cd.remainingmillis / 1000) % 60;
                    }
                    else {
                        mins = val.indexOf(':') != 0 ? val.indexOf(':') != -1 ? Integer.parseInt(val.substring(0, val.indexOf(':'))) : Integer.parseInt(val) : 0;
                        secs = val.indexOf(':') > -1 ? Integer.parseInt(val.substring(val.indexOf(':') + 1)) : 0;
                    }
                    if(mins > 99 || secs > 99){
                        Toast.makeText(getApplicationContext(),"Max Value 99:99", Toast.LENGTH_SHORT).show();
                        mins = 8;
                        secs = 0;
                    }
                    cd.setTimer(mins,secs);
                    clear = true;
                }
                return false;
            }
        });

    }


    protected abstract int getLayoutResourceId();
}
