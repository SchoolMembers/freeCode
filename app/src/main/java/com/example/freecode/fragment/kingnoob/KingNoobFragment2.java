package com.example.freecode.fragment.kingnoob;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.freecode.R;

public class KingNoobFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment2 started");
        return inflater.inflate(R.layout.kingnoob_fragment2, container, false);
    }

}