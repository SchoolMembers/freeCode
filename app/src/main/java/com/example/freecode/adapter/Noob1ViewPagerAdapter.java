package com.example.freecode.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.freecode.fragment.noob1.Noob1Fragment1;
import com.example.freecode.fragment.noob1.Noob1Fragment2;

public class Noob1ViewPagerAdapter extends FragmentStateAdapter {
    public Noob1ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Noob1Fragment1();
//            case 1:
//                return new Noob1Fragment2();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
