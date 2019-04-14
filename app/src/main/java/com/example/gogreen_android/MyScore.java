package com.example.gogreen_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gogreen_android.controllers.StatisticsController;
import com.example.gogreen_android.models.Statistics;
import com.example.gogreen_android.requests.GetRequests;
import com.example.gogreen_android.requests.UserRequests;

import java.io.IOException;
import java.util.ArrayList;

public class MyScore extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Statistics stats;

    class AsyncTaskConnection extends AsyncTask<ArrayList, Statistics, Statistics> {

        String username;

        @Override
        protected Statistics doInBackground(ArrayList... arrayLists) {
            username = (String) arrayLists[0].get(0);
            init(username);
            System.out.println("Initialised with username: " + username);
            return init(username);
        }

        @Override
        protected void onPostExecute(Statistics statistics) {
            stats = statistics;
            System.out.println("Retrieved username from StatisticsController; " + stats.getUsername());
            getStats();
        }
    }
    private Statistics init(String username){
        StatisticsController.setUserName(username);
//        ProfileController.setUserName(username);
//        StatisticsController.postRequests = new PostRequests();
        StatisticsController.getRequests = new GetRequests();
//        StatisticsController.transport = new Transport(username, 0, 0);
//        StatisticsController.lifestyle = new Lifestyle(username, false, false,
//                0);
//        StatisticsController.vegetarianMeal = StatisticsController.getRequests.getVegetarianMeal(username);
        try{
            StatisticsController.profile = UserRequests.getProfileConnection(username);
            StatisticsController.statistics = StatisticsController.getRequests.getStatsConnection(username);
            System.out.println("StatisticsController.statistics at init() is: " + StatisticsController.statistics);
            System.out.println(StatisticsController.statistics.getClass() + " is the Class");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("IO Error occured at init()");
        }
        return StatisticsController.statistics;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Score");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView score = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            ArrayList array = new ArrayList(1);
            array.add(0, username);
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            taskConnection.execute(array);
//            System.out.println("Retrieved username from StatisticsController; " + stats.getUsername());
//        System.out.println("Initialised username:" + username);
        } else {
            System.out.println("Intent passed to myScore.java is null");
        }

//        StatisticsController.setUserName(username);
//        StatisticsController.statistics = StatisticsController.getRequests.getStatistics(username);
//        StatisticsController.vegMeal = StatisticsController.getRequests.getVegetarianMeal(username);

//        StatisticsController.initialise();
        //TODO Get score of user
//        score.setText(user.getScore());
    }

    public void getStats(){
        System.out.println("Retrieved score from StatisticsController; " + stats.getScore());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_score, menu);
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

        if (id == R.id.nav_my_score) {
            Intent intent = new Intent(this, MyScore.class);
            startActivity(intent);
        } else if (id == R.id.nav_reduced_emssion) {
            Intent intent = new Intent(this, ReducedEmission.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_basecase) {

        } else if (id == R.id.nav_logout){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
