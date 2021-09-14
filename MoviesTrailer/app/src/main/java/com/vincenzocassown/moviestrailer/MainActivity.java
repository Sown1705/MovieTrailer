package com.vincenzocassown.moviestrailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.vincenzocassown.moviestrailer.adapter.MyViewPager2Adapter;
import com.vincenzocassown.moviestrailer.databinding.ActivityMainBinding;
import com.vincenzocassown.moviestrailer.other.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private MyViewPager2Adapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapterViewPager = new MyViewPager2Adapter(this);

        binding.viewPager2.setAdapter(adapterViewPager);
        binding.viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popular:
                        binding.viewPager2.setCurrentItem(0);
                        break;
                    case R.id.top_rated:
                        binding.viewPager2.setCurrentItem(1);
                        break;
                    case R.id.up_coming:
                        binding.viewPager2.setCurrentItem(2);
                        break;
                    case R.id.now_playing:
                        binding.viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.popular).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.top_rated).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.up_coming).setChecked(true);
                        break;
                    case 3:
                        binding.bottomNavigation.getMenu().findItem(R.id.now_playing).setChecked(true);
                        break;

                }
            }
        });
    }

}