package com.example.gpayapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainDrawerActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open,
                R.string.close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.menu_home) {
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.menu_profile) {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.menu_settings) {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.menu_logout) {
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
