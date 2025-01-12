package com.example.freecode.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.freecode.fragment.kingnoob.KingNoobFragment1;
import com.example.freecode.fragment.kingnoob.KingNoobFragment2;
import com.example.freecode.fragment.kingnoob.KingNoobFragment3;
import com.example.freecode.fragment.kingnoob.KingNoobFragment4;
import com.example.freecode.fragment.kingnoob.KingNoobFragment5;
import com.example.freecode.fragment.kingnoob.KingNoobFragment6;

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
            case 2:
                return new KingNoobFragment3();
            case 3:
                return new KingNoobFragment4();
            case 4:
                return new KingNoobFragment5();
            case 5:
                return new KingNoobFragment6();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

}
