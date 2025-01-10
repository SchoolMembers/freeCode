package com.example.freecode.fragment.kingnoob;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.freecode.KingNoobActivity;
import com.example.freecode.R;
import com.example.freecode.methodClass.LastPageInfo;

public class KingNoobFragment1 extends Fragment {
    // Page 0

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment1 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment1, container, false);

        Context context = requireContext();

        //클리어 챕터인지 아닌지 확인
        LastPageInfo lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 0; // 0 이하면 true

        if (checkLast) {
            lastPage.setLastPage(context, "King", 0);
            Log.d("KingNoobActivity", "KingNoobFragment1 set lastPage : 0");
        }

        KingNoobActivity activity = (KingNoobActivity) requireActivity();

        //버튼 천천히 보이게
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);
        Button nextButton = view.findViewById(R.id.next_button);

        if (checkLast) {
            Log.d("Fragment", "KingNoobFragment1 checkLast true");
            anim.setDuration(5000); // 5초
            nextButton.setEnabled(false);

            // 애니메이션 리스너
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // 애니메이션 시작 시
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // 애니메이션 종료 시
                    nextButton.setEnabled(true);
                    Log.d("Fragment", "KingNoobFragment1 nextButton visible, enabled true");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // 애니메이션 반복 시
                }
            });

            nextButton.startAnimation(anim);
        }


        //다음 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "King") < 1) {
                lastPage.setLastPage(context, "King", 1);
                Log.d("KingNoobActivity", "KingNoobFragment1 set lastPage : 1");
            }
        });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getView();
        if (view != null) {
            Button nextButton = view.findViewById(R.id.next_button);
            if (nextButton != null) {
                nextButton.clearAnimation();
            }
        }
    }

}
