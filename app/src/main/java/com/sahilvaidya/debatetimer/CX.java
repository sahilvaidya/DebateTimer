package com.sahilvaidya.debatetimer;

import android.os.Bundle;

public class CX extends Timer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        time = 3 * 60000;
        super.onCreate(savedInstanceState);
        cd = new countdown(time, butt, text, 3);
    }

    protected int getLayoutResourceId() {
        return R.layout.activity_constructive;
    }

}
