package com.example.freecode.fragment.noob1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import com.example.freecode.Noob1Activity;
import com.example.freecode.R;
import com.example.freecode.adapter.Noob1ViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class Noob1Fragment2 extends Fragment {
    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob1Fragment2 started");
        View view = inflater.inflate(R.layout.noob1_fragment2, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob1") <= 1; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        Noob1Activity activity = (Noob1Activity) requireActivity();

        //버튼 천천히 보이게
        anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);

        if (isAnimationFinished || !checkLast) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
            nextButton.startAnimation(anim);
        }

        if (checkLast) {
            Log.d("Fragment", "Noob1Fragment2 checkLast true");
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
                    Log.d("Fragment", "Noob1Fragment2 nextButton visible, enabled true");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // 애니메이션 반복 시
                }
            });
        }


        //다음 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "Noob1") < 2) {
                lastPage.setLastPage(context, "Noob1", 2);
                Log.d("KingNoobActivity", "Noob1Fragment2 set lastPage : 2");
                Noob1ViewPagerAdapter adapter = (Noob1ViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(2);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //코드 주석 달기
        TextView des1 = view.findViewById(R.id.des1);
        String text1 = des1.getText().toString();

        TextCustom textCustom = new TextCustom(des1, text1);
        textCustom.setPiece("#결과 : 1.6");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setPiece("#결과 : 1");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setPiece("#결과 : 3");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setPiece("#결과 : 8");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setting();

        return view;
    }
}
