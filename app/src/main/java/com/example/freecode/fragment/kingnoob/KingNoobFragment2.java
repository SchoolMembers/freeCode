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

public class KingNoobFragment2 extends Fragment {

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment1 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment2, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 1; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);

        KingNoobActivity activity = (KingNoobActivity) requireActivity();

        //버튼 천천히 보이게
        anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);
        Button beforeButton = view.findViewById(R.id.before_button);

        if (isAnimationFinished || !checkLast) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
            nextButton.startAnimation(anim);
        }

        if (checkLast) {
            Log.d("Fragment", "KingNoobFragment1 checkLast true");
            nextButton.setEnabled(false);
            anim.setDuration(5000);

            // 애니메이션 리스너
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // 애니메이션 시작 시
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // 애니메이션 종료 시
                    isAnimationFinished = true;
                    nextButton.setEnabled(true);
                    Log.d("Fragment", "nextButton visible");
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
//            activity.allowNextPage(1);
            activity.moveToNextPage();
            if (lastPage.getLastPage(context, "King") < 2) {
                lastPage.setLastPage(context, "King", 2);
                Log.d("KingNoobActivity", "set lastPage " + 2);
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (nextButton != null) {
            nextButton.clearAnimation();
            if (anim != null) {
                anim.setAnimationListener(null);
            }
        }
    }

    // 페이지로 돌아올 때
    @Override
    public void onResume() {
        super.onResume();

        if (lastPage.getLastPage(context, "King") > 1) {
            nextButton.setEnabled(true);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }

}