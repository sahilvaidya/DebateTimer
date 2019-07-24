package com.sahilvaidya.debatetimer;

import android.os.Bundle;

public class Rebuttal extends Timer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        time = HomeScreen.getTime("Rebuttal") * 60000;
        super.onCreate(savedInstanceState);
        cd = new countdown(time, butt, text, 5);
    }

    protected int getLayoutResourceId() {
        return R.layout.activity_rebuttal;
    }

}
