package com.example.freecode.fragment.kingnoob;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.freecode.R;

public class KingNoobFragment6 extends Fragment {
    //page 5

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment6 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment6, container, false);

        context = requireContext();

        LottieAnimationView party = view.findViewById(R.id.party);
        party.setAnimation(R.raw.party);
        party.setRepeatCount(0);
        party.playAnimation();

        return view;
    }
    

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume called: " + getClass().getSimpleName());
        //2초 후 액티비티 종료
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().finish();
            }
        }, 2000);
    }

}


