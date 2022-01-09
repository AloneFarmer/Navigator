package com.deepway.navigator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.deepway.navigator.databinding.ActivityMainBinding;
import com.deepway.navigator.navigation.ui.fragment.NavigationFragment;
import com.deepway.navigator.service.ui.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<Fragment> fragmentList = new ArrayList<>();

    private int lastIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initBottomNavigation();
        initData();
    }

    private void initData() {
        fragmentList.add(NavigationFragment.newInstance("", ""));
        fragmentList.add(ServiceFragment.newInstance("", ""));
        setFragmentPosition(0);
    }


    private void initBottomNavigation() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_navigation:
                    setFragmentPosition(0);
                    break;
                case R.id.menu_service:
                    setFragmentPosition(1);
                    break;
            }
            return true;
        });
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragmentList.get(position);
        Fragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = position;
        fragmentTransaction.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            fragmentTransaction.add(R.id.ll_frameLayout, currentFragment);
        }
        fragmentTransaction.show(currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}