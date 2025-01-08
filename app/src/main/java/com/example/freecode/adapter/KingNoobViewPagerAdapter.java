package com.example.freecode.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.freecode.fragment.kingnoob.KingNoobFragment1;
import com.example.freecode.fragment.kingnoob.KingNoobFragment2;

public class KingNoobViewPagerAdapter extends FragmentStateAdapter {

    public KingNoobViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new KingNoobFragment1();
            case 1:
                return new KingNoobFragment2();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
