package com.example.freecode.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.freecode.fragment.noob2.Noob2Fragment1;
import com.example.freecode.fragment.noob2.Noob2Fragment2;

public class Noob2ViewPagerAdapter extends FragmentStateAdapter {
    private final long[] fragmentIds;

    public Noob2ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragmentIds = new long[getItemCount()];
        for (int i = 0; i < fragmentIds.length; i++) {
            fragmentIds[i] = i; // 초기화
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Noob2Fragment1();
            case 1:
                return new Noob2Fragment2();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return fragmentIds[position];
    }

    @Override
    public boolean containsItem(long itemId) {
        for (long id : fragmentIds) {
            if (id == itemId) return true;
        }
        return false;
    }

    public void refreshFragment(int position) {
        fragmentIds[position] = System.currentTimeMillis(); // 새로운 ID로 업데이트
        notifyDataSetChanged();
    }
}
