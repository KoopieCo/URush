package com.csce4623.rynolan.urush;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.csce4623.rynolan.urush.configurations.GlobalResources;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements SettingsFragment.OnSettingsFragmentInteractionListener, FormFragment.OnFormFragmentInteractionListener, HomeFragment.OnHomeFragmentInteractionListener {
    private DrawerLayout drawerLayout;
    private Spinner spinner;
    private String selectedFrat;
    private int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(selectedFrat == null) {
            selectedFrat = new String("Theta Tau");
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View header = navigationView.getHeaderView(0);

        spinner = (Spinner)(header.findViewById(R.id.spinner));
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String selectedFraternity = spinner.getSelectedItem().toString();
                ImageView fraternityCrest = (ImageView)findViewById(R.id.ivNavHeaderCrest);
                ImageView topCrest = (ImageView)toolbar.findViewById(R.id.topCrest);
                if(selectedFraternity.equals("Theta Tau")) {
                    header.setBackground(getResources().getDrawable(R.drawable.side_nav_bar, getTheme()));
                    fraternityCrest.setImageResource(R.drawable.logo);
                    topCrest.setImageResource(R.drawable.logo);
                    toolbar.setBackground(getResources().getDrawable(R.drawable.side_nav_bar, getTheme()));
                    selectedFrat = "Theta Tau";
                }
                else if(selectedFraternity.equals("Sigma Pi")) {
                    header.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_purple, getTheme()));
                    fraternityCrest.setImageResource(R.drawable.logo_sigma_pi);
                    topCrest.setImageResource(R.drawable.logo_sigma_pi);
                    toolbar.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_purple, getTheme()));
                    selectedFrat = "Sigma Pi";
                }
                else if(selectedFraternity.equals("Alpha Delta Pi")) {
                    selectedFrat = "Alpha Delta Pi";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        List<String> frats = new ArrayList<String>();
        frats.add("Theta Tau");
        frats.add("Sigma Pi");
        frats.add("Alpha Delta Pi");

        ArrayAdapter<String> fratAdapter = new ArrayAdapter<String>(header.getContext(), R.layout.spinner_centered_text, frats);
        fratAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fratAdapter);

        navigationView.setNavigationItemSelectedListener(new DrawerNavigationListener());

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_home, fragment)
                .commit();
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

    // Handle navigation view item clicks here
    public boolean clickNavigationItem(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);

        // Create a new fragment, then insert the fragment by replacing any existing fragment
        if (id == R.id.nav_home) {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_home, fragment)
                    .commit();
        }
        else if (id == R.id.nav_forms) {

            Fragment fragment = new FormFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_home, fragment)
                    .commit();
        }
        else if (id == R.id.nav_contact_info) {
            Fragment fragment = new ContactInfoFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_home, fragment)
                    .commit();

        }
        else if (id == R.id.nav_about) {
            Fragment fragment = new SettingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_home, fragment)
                    .commit();
        }
        else {
            return false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Form Iteraction
    @Override
    public void onFormFragmentInteraction(Uri uri) {

    }

    @Override
    public void onHomeFragmentInteraction(Context context) {

    }

    @Override
    public void OnSettingsFragmentInteraction(Uri uri){

    }

    private class DrawerNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            return clickNavigationItem(item);
        }
    }
}
