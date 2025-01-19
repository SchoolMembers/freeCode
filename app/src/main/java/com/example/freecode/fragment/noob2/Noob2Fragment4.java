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

public class Noob2Fragment4 extends Fragment {
    // Page 3

    private boolean isAnimationFinished = false;
    private Animation anim;
    private Button nextButton;
    private LastPageInfo lastPage;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob2Fragment4 started");
        View view = inflater.inflate(R.layout.noob2_fragment4, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob2") <= 3; //참이면 아직 못 깸
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
            Log.d("Fragment", "Noob2Fragment4 checkLast true");
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
                    Log.d("Fragment", "Noob2Fragment4 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "Noob2") < 4) {
                lastPage.setLastPage(context, "Noob2", 4);
                Log.d("KingNoobActivity", "Noob2Fragment4 set lastPage : 4");
                Noob2ViewPagerAdapter adapter = (Noob2ViewPagerAdapter) activity.getViewPager().getAdapter();
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
        textCustom.setPiece("딕셔너리");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.violet));
        textCustom.setPiece("키");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.navy));
        textCustom.setPiece("값");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textView = view.findViewById(R.id.des3);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("딕셔너리");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.violet));
        textCustom.setPiece("key");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.navy));
        textCustom.setPiece("value");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("{ }");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setting();

        textView = view.findViewById(R.id.des4);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("딕셔너리");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.violet));
        textCustom.setPiece("키");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.navy));
        textCustom.setPiece("값");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("불변");
        textCustom.size(1.2f);
        textCustom.setPiece("가변");
        textCustom.size(1.2f);
        textCustom.setPiece("내부 요소도 불변");
        textCustom.sizeAll(1.2f);
        textCustom.setPiece("t = ([1,2], 3)");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("bool");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("set");
        textCustom.font(ResourcesCompat.getFont(view.getContext(), R.font.code));
        textCustom.background(ContextCompat.getColor(context, R.color.lightGray));
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("불변: 선언 후 변하지 않음");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.setPiece("가변: 선언 후 변경될 수 있음");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.setPiece("! 참고1");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.size(1.1f);
        textCustom.setPiece("! 참고2");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.size(1.1f);
        textCustom.setPiece("str(string), dict(dictionary), bool(boolean)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.setting();

        textView = view.findViewById(R.id.des5);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("{");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("}");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("#결과: one");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("#결과: kim");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textView = view.findViewById(R.id.des6);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("키");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.navy));
        textCustom.setPiece("값");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("해당 값에 대응하는 키");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("딕셔너리 키 'person'");
        textCustom.underline();
        textCustom.setPiece("값인 리스트 ['Kim', 'Lee']");
        textCustom.underline();
        textCustom.setPiece("인덱스 0번 값 'Kim'");
        textCustom.underline();
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
        if (lastPage.getLastPage(context, "Noob2") > 3) {
            nextButton.setEnabled(true);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }
}
