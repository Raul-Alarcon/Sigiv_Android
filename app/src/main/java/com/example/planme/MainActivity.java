package com.example.planme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.planme.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.auth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = this.auth.getCurrentUser();

//        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
//            Log.e("CrashHandler", "Uncaught Exception", throwable);
//            // Puedes enviar el log a un servidor o archivo
//        });


        //LocalContext.setUpContext();

        //SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        //boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_task, R.id.navigation_calendar, R.id.navigation_me)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            try {
                if (destination.getId() == R.id.navigation_login) {
                    navView.setVisibility(View.GONE);
                    Objects.requireNonNull(getSupportActionBar()).hide();
                } else {
                    navView.setVisibility(View.VISIBLE);
                    Objects.requireNonNull(getSupportActionBar()).show();
                }
            } catch (Exception e){
                Log.println(Log.ERROR, "Navigation",
                        "Error: " + e.getClass().getName() +" "+e.getMessage());
            }
        });

        if (currentUser == null) { // !isLoggedIn
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.mobile_navigation, true)
                    .build();

            navController.navigate(R.id.navigation_login, null, navOptions);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}