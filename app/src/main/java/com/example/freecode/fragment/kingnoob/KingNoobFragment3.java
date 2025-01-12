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
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.freecode.KingNoobActivity;
import com.example.freecode.R;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class KingNoobFragment3 extends Fragment {
    // Page 2

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment3 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment3, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 2; //참이면 아직 못 깸
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
            Log.d("Fragment", "KingNoobFragment3 checkLast true");
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
                    Log.d("Fragment", "KingNoobFragment3 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "King") < 3) {
                lastPage.setLastPage(context, "King", 3);
                Log.d("KingNoobActivity", "KingNoobFragment3 set lastPage : 3");
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //텍스트 강조
        TextView des1 = view.findViewById(R.id.des1);
        TextView des2 = view.findViewById(R.id.des2);
        TextView des3 = view.findViewById(R.id.des3);

        String text1 = des1.getText().toString();
        String text2 = des2.getText().toString();
        String text3 = des3.getText().toString();

        TextCustom textCustom = new TextCustom(des1, text1);
        textCustom.setPiece("! 코드 (Code)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 소스 코드 (Source Code)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 프로그램 (Program)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 컴파일러 (Compiler)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightGreen));
        textCustom.size(1.2f);

        textCustom.setPiece("! 인터프리터 (Interpreter)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightGreen));
        textCustom.size(1.2f);

        textCustom.setPiece("! 고급 언어 (High-Level Language)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 저급 언어 (Low-Level Language)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 변수 (Variable)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 함수 (Function)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setting();

        //코드 주석
        textCustom = new TextCustom(des2, text2);
        textCustom.setPiece("#두 숫자의 합 출력");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));

        textCustom.setPiece("#결과: 3");
        textCustom.textColor(ContextCompat.getColor(context, R.color.brown));

        textCustom.setting();

        //-------

        textCustom = new TextCustom(des3, text3);
        textCustom.setPiece("! 데이터 타입 (Data Type)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 디버깅 (Debugging)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

        textCustom.setPiece("! 알고리즘 (Algorithm)");
        textCustom.background(ContextCompat.getColor(context, R.color.lightPink));
        textCustom.size(1.2f);

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
        if (lastPage.getLastPage(context, "King") > 2) {
            nextButton.setEnabled(true);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }
}
