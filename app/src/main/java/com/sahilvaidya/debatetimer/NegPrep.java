package com.sahilvaidya.debatetimer;

import android.content.Intent;
import android.os.Bundle;

public class NegPrep extends Prep {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NegPrep.this,HomeScreen.class);
        intent.putExtra("Time_Remaining",Long.toString(cd.remainingmillis));
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_neg_prep;
    }
}
