package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainDrawerActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // ✅ LOAD DEFAULT FRAGMENT (THIS WAS MISSING)
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new DashboardFragment())
                    .commit();
        }

        // Drawer menu click handling
        navigationView.setNavigationItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.menu_dashboard) {
                selectedFragment = new DashboardFragment();
            }
            else if (item.getItemId() == R.id.menu_profile) {
                selectedFragment = new ProfileFragment();
            }
            else if (item.getItemId() == R.id.menu_logout) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }
}
