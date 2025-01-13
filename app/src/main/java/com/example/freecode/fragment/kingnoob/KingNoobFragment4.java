package com.example.freecode.fragment.kingnoob;

import android.content.Context;
import android.graphics.Typeface;
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
import com.example.freecode.KingNoobActivity;
import com.example.freecode.R;
import com.example.freecode.adapter.KingNoobViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class KingNoobFragment4 extends Fragment {
    // Page 3

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment4 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment4, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 3; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        KingNoobActivity activity = (KingNoobActivity) requireActivity();

        //버튼 천천히 보이게
        anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);

        if (isAnimationFinished || !checkLast) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
            nextButton.startAnimation(anim);
        }

        if (checkLast) {
            Log.d("Fragment", "KingNoobFragment4 checkLast true");
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
                    Log.d("Fragment", "KingNoobFragment4 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "King") < 4) {
                lastPage.setLastPage(context, "King", 4);
                Log.d("KingNoobActivity", "KingNoobFragment4 set lastPage : 4");
                KingNoobViewPagerAdapter adapter = (KingNoobViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(4);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        TextView textView = view.findViewById(R.id.des1);
        String text = textView.getText().toString();

        TextCustom textCustom = new TextCustom(textView, text);
        textCustom.setPiece("파이썬");
        textCustom.textColorCount(ContextCompat.getColor(context, R.color.blue), 2);
        textCustom.setPiece("장점");
        textCustom.size(1.3f);
        textCustom.setPiece("단점");
        textCustom.size(1.3f);
        textCustom.setPiece("사용성");
        textCustom.size(1.3f);

        textCustom.setPiece("간결한 문법");
        textCustom.background(ContextCompat.getColor(context, R.color.veryLightOrange));
        textCustom.setPiece("코드 가독성");
        textCustom.background(ContextCompat.getColor(context, R.color.veryLightOrange));
        textCustom.setPiece("플랫폼 독립성");
        textCustom.background(ContextCompat.getColor(context, R.color.veryLightOrange));
        textCustom.setPiece("풍부한 라이브러리");
        textCustom.background(ContextCompat.getColor(context, R.color.veryLightOrange));
        textCustom.setPiece("* 라이브러리란?");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.size(0.9f);
        textCustom.setPiece("자주 사용되는 기능 또는 복잡한 알고리즘이 설계되어 있는 데이터의 집합이에요. 자세한 건 고급 단계에서 배우게 돼요!");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.size(0.9f);
        textCustom.setPiece("무료 오픈소스");
        textCustom.background(ContextCompat.getColor(context, R.color.veryLightOrange));
        textCustom.setPiece("파이썬에게는 이외에도 많은 장점이 존재하며, 초보자와 전문가 모두에게 적합한 훌륭한 언어예요!");
        textCustom.style(Typeface.BOLD_ITALIC);

        textCustom.setPiece("비교적 느린 실행 속도");
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.setPiece("모바일 언어에 비적합");
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));

        textCustom.setPiece("데이터 분석");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.setPiece("인공지능 및 머신러닝");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.setPiece("게임 개발");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.setting();


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

        // [이전 버튼 -> 다음 버튼] 레이아웃 오류 방지
        if (lastPage.getLastPage(context, "King") > 3) {
            nextButton.setEnabled(true);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }
}
