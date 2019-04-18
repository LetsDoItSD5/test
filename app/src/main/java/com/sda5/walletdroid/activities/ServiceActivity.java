package com.sda5.walletdroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.sda5.walletdroid.R;
import com.sda5.walletdroid.activities.Graphs.ListViewMultiChartActivity;
import com.sda5.walletdroid.activities.Graphs.MultiLineChartActivity;
import com.sda5.walletdroid.activities.Graphs.PieChartActivity;
import com.sda5.walletdroid.fragments.ExpenseFragment;
import com.sda5.walletdroid.fragments.GroupFragment;
import com.sda5.walletdroid.fragments.InvestFragment;
import com.sda5.walletdroid.fragments.QueyFragment;
import com.sda5.walletdroid.fragments.SettleFragment;
import com.sda5.walletdroid.fragments.fragments_navigation.AppDetails;
import com.sda5.walletdroid.fragments.fragments_navigation.Feedbacknav;
import com.sda5.walletdroid.fragments.fragments_navigation.GraphFragment;
import com.sda5.walletdroid.fragments.fragments_navigation.ShareNav;
import com.sda5.walletdroid.fragments.fragments_navigation.userprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

//import android.widget.Toolbar;

public class ServiceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;

    public NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_group:
                    fragment = new GroupFragment();
                    break;
                case R.id.navigation_expense:
                    fragment = new ExpenseFragment();
                    break;
                case R.id.navigation_query:
                    fragment = new QueyFragment();
                    break;
                case R.id.navigation_settle:
                    fragment = new SettleFragment();
                    break;
                case R.id.navigation_stock:
                    fragment = new InvestFragment();


            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        //Bottom navigation view listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Navigation view
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //navigation drawer listener
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new GroupFragment());
    }

    // FOR NAVIGATION DRAWER
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    // FOR NAVIGATION DRAWER
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

    //FOR NAVIGATION DRAWER
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_userProfile:
                fragment = new userprofile();
                break;
            case R.id.nav_expenseSetting:
                fragment = new GraphFragment();
                break;

            case R.id.nav_graph:
                fragment = new GraphFragment();


            case R.id.nav_feedback:
                fragment = new Feedbacknav();
                break;

            case R.id.nav_phoneNumber:
                fragment = new AppDetails();
                break;
            case R.id.nav_share:
                fragment = new ShareNav();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return loadFragment(fragment);
    }




    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
            return false;
    }


    /**
     * Method to invoke multi line Chart
     * @param v
     */
    public void getLineChart(View v) {
        switch(v.getId()) {
            case R.id.buttonLineChart:
                Intent myIntent = new Intent(getApplicationContext(), MultiLineChartActivity.class);
                //myIntent.SerciceActivity.class, PieChartActivity.class);
                // for ex: your package name can be "com.example"
                // your activity name will be "com.example.Contact_Developer"
                startActivity(myIntent);
                break;
        }
    }

    /**
     * Method to invoke Graph as List
     * @param v
     */
    public void getGraphList(View v) {
        switch(v.getId()) {
            case R.id.buttonGraphList:
                Intent myIntent = new Intent(getApplicationContext(), ListViewMultiChartActivity.class);
                //myIntent.SerciceActivity.class, PieChartActivity.class);
                // for ex: your package name can be "com.example"
                // your activity name will be "com.example.Contact_Developer"
                startActivity(myIntent);
                break;
        }
    }

    /**
     * Method to invoke PieChartActivity
     * @param v
     */
    public void getPieChart(View v) {
        switch(v.getId()) {
            case R.id.buttonPieChart:
                Intent myIntent = new Intent(getApplicationContext(), PieChartActivity.class);
                //myIntent.SerciceActivity.class, PieChartActivity.class);
                // for ex: your package name can be "com.example"
                // your activity name will be "com.example.Contact_Developer"
                startActivity(myIntent);
                break;
        }
    }




    public void signOut(View view) {
        mAuth.signOut();
        finish();
        startActivity(getIntent());
    }
}
