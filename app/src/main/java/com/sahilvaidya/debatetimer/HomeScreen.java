package com.sahilvaidya.debatetimer;

/*Random Ideas
Make the time flash
 */

import android.content.Intent;
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
    //
//    boolean active;
//    boolean created;
//    long totalmillis;
//    CountDownTimer time;
    Button ConstructiveTimer, RebuttalTimer, CXTimer, AffTimer, NegTimer;
    countdown Constructive, Rebuttal, CX;
    String afftime;
    String negtime;
    long affmilis;
    long negmilis;

    TextView atl,ntl;
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
        atl = (TextView) findViewById(R.id.afftimeleft);
        ntl = (TextView) findViewById(R.id.negtimeleft);

        afftime = "480000";
        affmilis = Long.parseLong(afftime);

        negtime = "480000";
        negmilis = Long.parseLong(negtime);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        atl.setText(printTime(affmilis));
        ntl.setText(printTime(negmilis));

        AffTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this,AffPrep.class);
                intent.putExtra("Time_Remaining",afftime);
                startActivityForResult(intent,1);
            }
        });
        NegTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this,NegPrep.class);
                intent.putExtra("Time_Remaining",negtime);
                startActivityForResult(intent,2);
            }
        });
        ConstructiveTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this,Constructive.class);
                startActivity(intent);
            }
        });
        RebuttalTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this,Rebuttal.class);
                startActivity(intent);
            }
        });
        CXTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this,CX.class);
                startActivity(intent);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                afftime = data.getStringExtra("Time_Remaining");
                affmilis = Long.parseLong(afftime);
                atl.setText(printTime(affmilis));
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                negtime = data.getStringExtra("Time_Remaining");
                negmilis = Long.parseLong(negtime);
                ntl.setText(printTime(negmilis));
            }
        }
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

    public String printTime(long millis){
        return "" + String.format("%02d",(millis / 60000)) + ":" + String.format("%02d",(millis / 1000) % 60);
    }

}
