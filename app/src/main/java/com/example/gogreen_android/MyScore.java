package com.example.gogreen_android;

import android.content.Intent;
import android.graphics.Color;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MyScore extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Statistics stats;
    int Score;
    CustomGauge veggieMealGauge;
    CustomGauge travelGauge;
    PieChartView travelChart;

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

        TextView score = (TextView) findViewById(R.id.my_Score);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            System.out.println("Username retrieved from intent at myScore is: " + username);
            ArrayList array = new ArrayList(1);
            array.add(0, username);
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            taskConnection.execute(array);

        } else {
            System.out.println("Intent passed to myScore.java is null");
        }
    }

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
            setStats();
        }
    }
    private Statistics init(String username){
        StatisticsController.setUserName(username);
        StatisticsController.getRequests = new GetRequests();

        try{
            StatisticsController.statistics = StatisticsController.getRequests.getStatsConnection(username);
            System.out.println("StatisticsController.statistics at init() is: " + StatisticsController.statistics);
            System.out.println(StatisticsController.statistics.getClass() + " is the Class");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("IO Error occured at init()");
        }
        return StatisticsController.statistics;
    }

    public void setStats(){
        setScore();
        setGauge1();
        setGauge2();
    }

    public void setScore(){
        Score = stats.getScore();
        System.out.println("Retrieved score from StatisticsController; " + Score);
        TextView score = findViewById(R.id.my_Score);
        score.setText(Integer.toString(Score));
    }

    public void setGauge1(){
        veggieMealGauge = findViewById(R.id.veg_meals_eaten);
        int mealsEaten = stats.getNumberOfVegetarianMeals();

        int g1StartValue = (mealsEaten - (mealsEaten%50));
        System.out.println("g1StartValue is: " + g1StartValue);
        veggieMealGauge.setStartValue(g1StartValue);

        int g1EndValue = ((mealsEaten+50) - (mealsEaten%50));
        System.out.println("g1EndValue is: " + g1EndValue);
        veggieMealGauge.setEndValue(g1EndValue);

        int g1Value = (mealsEaten % 50);
        System.out.println("g1Value is: " + g1Value);
        veggieMealGauge.setValue(g1Value);

        TextView idMealsEaten = findViewById(R.id.nbVeggieMeals);
        idMealsEaten.setText(Integer.toString(mealsEaten));
        TextView outOfMod50 = findViewById(R.id.outOfMod50);
        outOfMod50.setText(Integer.toString(g1EndValue));
        TextView inOfMod50 = findViewById(R.id.inOfMod50);
        inOfMod50.setText(Integer.toString(g1StartValue));
    }

    public void setGauge2(){
        List<SliceValue> pieData = new ArrayList<>();
        int byBike = stats.getReducedEmissionByTravelingByBike();
        int byPT = stats.getReducedEmissionByTravelingByPublicTransport();
        System.out.println(byBike + "||" + byPT);

        pieData.add(new SliceValue(byBike, Color.rgb(38,102,125)).setLabel("By Bike: " + byBike));
        pieData.add(new SliceValue(byPT, Color.rgb(178,28,26)).setLabel("By PT: " + byPT));


        PieChartData chartData = new PieChartData(pieData);
        chartData.setHasCenterCircle(true);
        chartData.setHasLabels(true).setValueLabelTextSize(9);

        travelChart = findViewById(R.id.travelChart);
        travelChart.setPieChartData(chartData);

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
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_reduced_emssion) {
            Intent intent = new Intent(this, ReducedEmission.class);
            intent.putExtra("username", stats.getUsername());
            System.out.println("username sent in intent from myScore.java is: " + stats.getUsername());
            startActivity(intent);
        } else if (id == R.id.nav_logout){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
