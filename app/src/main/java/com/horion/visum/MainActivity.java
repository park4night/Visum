package com.horion.visum;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.horion.visum.Data.NavigationRuleEnum;
import com.horion.visum.Presentation.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
        loadFragment(new NavigationRule(NavigationRuleEnum.ADD, new HomeFragment(),null));
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> navigateFromBottomBar(item.getItemId()));
    }

    // TODO: 17/07/2020 switch
    private boolean navigateFromBottomBar(int itemId) {

        switch (itemId) {
            case R.id.navigation_home:
                break;

            case R.id.navigation_dashboard:
                break;

            case R.id.navigation_notifications:
                break;
        }
        return true;
    }

    // TODO: 17/07/2020 switch
    public void loadFragment(NavigationRule navigationRule) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (navigationRule.rule) {
            case ADD:
                fragmentTransaction.add(R.id.home_fragment_container, navigationRule.fragment).addToBackStack(navigationRule.tag);
                break;

            case REPLACE:
                fragmentTransaction.replace(R.id.home_fragment_container, navigationRule.fragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
