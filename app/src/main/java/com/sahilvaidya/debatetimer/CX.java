package com.sahilvaidya.debatetimer;

import android.os.Bundle;

public class CX extends Timer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        time = HomeScreen.getTime("CX") * 60000;
        super.onCreate(savedInstanceState);
        cd = new countdown(time, butt, text, 3);
    }

    protected int getLayoutResourceId() {
        return R.layout.activity_constructive;
    }

}
