package com.sahilvaidya.debatetimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class AffPrep extends AppCompatActivity {

    Button butt, set;
    countdown cd;
    int time;
    int spot;
    int tensmins;
    int onesmins;
    int tenssecs;
    int onessecs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_prep);
        butt = (Button) findViewById(R.id.timer);
        time = Integer.parseInt(getIntent().getStringExtra("Time_Remaining"));
        cd  = new countdown(time,butt);
        set = (Button) findViewById(R.id.set);
        spot = 1;

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cd.active) {
                    cd.startTimer();
                    cd.active = true;
                } else {
                    cd.pauseTimer();
                    cd.active = false;
                }
            }
        });
        butt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cd.resetTimer();
                return true;
            }
        });
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                cd.resetTimer();
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(spot){
            case 1:
                tensmins = event.getNumber();
                butt.setText("" + tensmins + "000");
            case 2:
                onesmins = event.getNumber();
                butt.setText("" + tensmins + "" + onesmins + ":00");
            case 3:
                tenssecs = event.getNumber();
                butt.setText("" + tensmins + "" + onesmins + ":" + tenssecs + "0");
            case 4:
                onessecs = event.getNumber();
                butt.setText("" + tensmins + "" + onesmins + ":"+tenssecs + "" + onessecs);
                cd.setTimer(tensmins,onesmins,tenssecs,onessecs);
                spot = 1;
        }
        spot++;
        return super.onKeyDown(keyCode,event);
    }
}
