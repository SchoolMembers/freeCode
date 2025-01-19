package com.example.freecode.fragment.noob2;

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
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import com.example.freecode.Noob2Activity;
import com.example.freecode.R;
import com.example.freecode.adapter.Noob2ViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class Noob2Fragment2 extends Fragment {
    // Page 1

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob2Fragment2 started");
        View view = inflater.inflate(R.layout.noob2_fragment2, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob2") <= 1; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        Noob2Activity activity = (Noob2Activity) requireActivity();

        //버튼 천천히 보이게
        anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);

        if (isAnimationFinished || !checkLast) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
            nextButton.startAnimation(anim);
        }

        if (checkLast) {
            Log.d("Fragment", "Noob2Fragment2 checkLast true");
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
                    Log.d("Fragment", "Noob2Fragment2 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "Noob2") < 2) {
                lastPage.setLastPage(context, "Noob2", 2);
                Log.d("Noob2Activity", "Noob2Fragment2 set lastPage : 2");
                Noob2ViewPagerAdapter adapter = (Noob2ViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(2);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //텍스트 커스텀
        TextView textView = view.findViewById(R.id.des1);
        String text = textView.getText().toString();
        TextCustom textCustom = new TextCustom(textView, text);
        textCustom.setPiece("한 번 생성되면 그 값을 절대 바꿀 수 없어요");
        textCustom.underline();
        textCustom.setPiece("안전성이 중요한 데이터");
        textCustom.size(1.2f);
        textCustom.setting();

        textView = view.findViewById(R.id.des2);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("#비어있는 튜플 생성");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#요소가 하나인 튜플 생성");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#요소가 두 개 이상인 튜플 생성");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#중첩 튜플");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#tuple() 내장 함수");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#빈 튜플 생성");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#리스트를 튜플로 변환");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: (1, 2, 3)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#문자열을 튜플로 변환");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: ('h', 'i')");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textView = view.findViewById(R.id.des3);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("t2");
        textCustom.fontAll(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.setPiece("t2 = 1 또는 t2 = (1)");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("변수명 = (1, )");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("튜플을 다루는 방식은 리스트와 유사해요!");
        textCustom.size(1.3f);
        textCustom.setting();

        textView = view.findViewById(R.id.des4);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("#인덱싱");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#슬라이싱");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#튜플 더하기");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#튜플 곱하기");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#튜플 길이");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: 1");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: ('three', 'four')");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: (1, 2, 'three', 'four', 3, 4)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: (3, 4, 3, 4)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: 4");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textView = view.findViewById(R.id.des5);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("t3");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
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
        if (lastPage.getLastPage(context, "Noob2") > 1) {
            nextButton.setEnabled(true);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }
}
