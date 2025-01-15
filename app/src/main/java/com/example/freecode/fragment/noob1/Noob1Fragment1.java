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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.freecode.Noob1Activity;
import com.example.freecode.R;
import com.example.freecode.adapter.Noob1ViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class Noob1Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob1Fragment1 started");
        View view = inflater.inflate(R.layout.noob1_fragment1, container, false);

        Context context = requireContext();

        //클리어 챕터인지 아닌지 확인
        LastPageInfo lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob1") <= 0; // 0 이하면 true

        if (checkLast) {
            lastPage.setLastPage(context, "Noob1", 0);
            Log.d("Noob1Activity", "Noob1Fragment1 set lastPage : 0");
        }

        Noob1Activity activity = (Noob1Activity) requireActivity();

        //버튼 천천히 보이게
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);
        Button nextButton = view.findViewById(R.id.next_button);

        if (checkLast) {
            Log.d("Fragment", "Noob1Fragment1 checkLast true");
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
                    Log.d("Fragment", "Noob1Fragment1 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "Noob1") < 1) {
                lastPage.setLastPage(context, "Noob1", 1);
                Log.d("Noob1Activity", "Noob1Fragment1 set lastPage : 1");
            }
        });

        //텍스트 강조
        TextView des1 = view.findViewById(R.id.des1);
        TextView des2 = view.findViewById(R.id.des2);
        TextView des3 = view.findViewById(R.id.des3);
        TextView des4 = view.findViewById(R.id.des4);

        String text1 = des1.getText().toString();
        String text2 = des2.getText().toString();
        String text3 = des3.getText().toString();
        String text4 = des4.getText().toString();

        TextCustom textCustom = new TextCustom(des1, text1);
        textCustom.setPiece("자료형");
        textCustom.textColor(ContextCompat.getColor(context, R.color.orchid));
        textCustom.setPiece("int");
        textCustom.textColor(ContextCompat.getColor(context, R.color.hotPink));
        textCustom.setPiece("float");
        textCustom.textColor(ContextCompat.getColor(context, R.color.hotPink));
        textCustom.setting();

        //코드 주석
        textCustom = new TextCustom(des2, text2);
        textCustom.setPiece("#정수(int)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setPiece("#실수(float)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setting();

        textCustom =  new TextCustom(des3, text3);
        textCustom.setPiece("#결과 : 127");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setting();

        textCustom =  new TextCustom(des4, text4);
        textCustom.setPiece("#결과 : 2748");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));
        textCustom.setting();

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
