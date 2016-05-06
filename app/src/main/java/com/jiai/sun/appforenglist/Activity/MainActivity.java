package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout relativeLayout;
    private FloatingActionButton fab;
    private TextView txvCountLearn;
    private TextView txvCountHasMaster;
    private TextView txvFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.flt_main);
        setSupportActionBar(toolbar);
        txvCountLearn = (TextView) findViewById(R.id.txt_courntLearn);
        txvCountHasMaster = (TextView) findViewById(R.id.txv_hasMaster_main);
        txvFinish = (TextView) findViewById(R.id.txv_finish);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initDate();
    }
    private  void  initDate(){
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        double n  = wordsDBManager.getCount();
        double countLearn = wordsDBManager.getCountBylearn();
        txvCountLearn.setText((int)countLearn+".");
        double countOk = wordsDBManager.getCountByOk();
        txvCountHasMaster.setText((int)countOk+".");
        double fin = ((countLearn/n)*2+(countOk/n))/3;
        fin=((int)(fin*1000))/10;
        txvFinish.setText(fin+"%");
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(),ReadyToLearnActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_learning_record) {
            Intent intent = new Intent(getApplicationContext(),LearningRecordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void startLearn(View view){
        Intent intent = new Intent(getApplicationContext(),ReadyToLearnActivity.class);
        startActivity(intent);
        this.relativeLayout.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
    }
    public void startReview(View view){
        Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
        startActivity(intent);
        this.relativeLayout.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
    }
    public void disappear(View view){
        this.relativeLayout.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDate();
    }

    @Override
    public void finish() {

        System.exit(0);
    }
}
