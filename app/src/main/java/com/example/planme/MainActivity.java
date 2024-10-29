package com.example.planme;

import android.os.Bundle;

import com.example.planme.data.local.LocalContext;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.planme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LocalContext.setUpContext();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_task, R.id.navigation_calendar, R.id.navigation_me)
                .build();
        this.navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, this.navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, this.navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return this.navController.navigateUp() || super.onSupportNavigateUp();
    }

}