package com.sahilvaidya.debatetimer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Constructive extends Timer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        time = HomeScreen.getTime("Constructive") * 60000;
        super.onCreate(savedInstanceState);
        cd = new countdown(time, butt, text, 8);
    }

    protected int getLayoutResourceId() {
        return R.layout.activity_constructive;
    }

}
