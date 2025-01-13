package com.example.freecode.fragment.kingnoob;

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

public class KingNoobFragment7 extends Fragment {
    //page 6

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment7 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment7, container, false);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume called: " + getClass().getSimpleName());

        LottieAnimationView party = getView().findViewById(R.id.party);
        if (party != null) {
            party.setAnimation(R.raw.party);
            party.setRepeatCount(0);
            party.playAnimation();

            // 2초 후 액티비티 종료
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if (getActivity() != null && !getActivity().isFinishing()) {
                    getActivity().finish();
                }
            }, 3000);
        }
    }

}


