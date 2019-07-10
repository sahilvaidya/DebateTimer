package com.sahilvaidya.debatetimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean active;
    boolean created;
    long totalmillis;
    CountDownTimer time;
    Button ConstructiveTimer, RebuttalTimer, CXTimer, AffTimer, NegTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConstructiveTimer = (Button) findViewById(R.id.ConstructiveTimer);
        RebuttalTimer = (Button) findViewById(R.id.RebuttalTimer);
        CXTimer = (Button) findViewById(R.id.CXTimer);
        AffTimer = (Button) findViewById(R.id.AffTimer);
        NegTimer = (Button) findViewById(R.id.NegTimer);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        active = false;
        created = false;
        totalmillis = 480000;



        ConstructiveTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!active){
                    start(time);
                    active = true;
                }
                else {
                    pause();
                    active = false;
                }
            }
        });
        ConstructiveTimer.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                resetTimer();
                return true;
            }
        });
    }

    public void pause(){
        time.cancel();
    }
    public void start(CountDownTimer time){
        time = new CountDownTimer(totalmillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ConstructiveTimer.setText("" + (millisUntilFinished / 1000) / 60 + ":" + (millisUntilFinished / 1000) % 60);
                totalmillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                ConstructiveTimer.setText("done!");
            }
        };
        time.start();
    }
    public void resetTimer(){
        totalmillis = 480000;
        time.cancel();
        time = new CountDownTimer(totalmillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ConstructiveTimer.setText("" + (millisUntilFinished / 1000) / 60 + ":" + (millisUntilFinished / 1000) % 60);
                totalmillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                ConstructiveTimer.setText("done!");
            }
        };
        ConstructiveTimer.setText("Constructive");
        active = false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
