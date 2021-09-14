package com.vincenzocassown.moviestrailer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vincenzocassown.moviestrailer.fragment.NowPlayingFragment;
import com.vincenzocassown.moviestrailer.fragment.PopularFragment;
import com.vincenzocassown.moviestrailer.fragment.TopReatedFragment;
import com.vincenzocassown.moviestrailer.fragment.UpComingFragment;

public class MyViewPager2Adapter extends FragmentStateAdapter {

    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PopularFragment();
            case 1:
                return new TopReatedFragment();
            case 2:
                return new UpComingFragment();
            case 3:
                return new NowPlayingFragment();
            default:
                 return new PopularFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
