package com.szafar97.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.szafar97.assignment3.Calculator.CalculatorActivity;
import com.szafar97.assignment3.Quiz.QuizHome;

public class MainActivity extends AppCompatActivity {

    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_calculator :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(MainActivity.this, CalculatorActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(),"Calculator Started",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_quiz :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(MainActivity.this, QuizHome.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(),"Quiz Started",Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }
}