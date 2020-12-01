package com.dlvjkb.hueapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import com.dlvjkb.hueapplication.fragments.*;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private HueLightBulbConnection hueEmulatorConnection;
    public static ArrayList<LightBulb> lightBulbs;
    private final String TAG = MainScreenActivity.class.getSimpleName();
    private final List<Fragment> accessibleFragments = new ArrayList<>();
    private Fragment currentFragment = null;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment change;
            switch (item.getItemId()){
                case R.id.menu_lights:
                    change = accessibleFragments.get(0);
                    Log.d(TAG,"Fragment changed to " + change);
                    currentFragment = change;
                    break;
                case R.id.menu_add:
                    change = accessibleFragments.get(1);
                    Log.d(TAG,"Fragment changed to " + change);
                    currentFragment = change;
                    break;
                case R.id.menu_settings:
                    change = accessibleFragments.get(2);
                    Log.d(TAG,"Fragment changed to " + change);
                    currentFragment = change;
                    break;
            };
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, currentFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragmentList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = this.accessibleFragments.get(0);
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    private void setupFragmentList(){
        Log.d(TAG,"Creating fragment list...");
        this.accessibleFragments.add(new LightsFragment());
        this.accessibleFragments.add(new ThemeFragment());
        this.accessibleFragments.add(new SettingsFragment());
        Log.d(TAG,"Fragment List Created...");
    }
}