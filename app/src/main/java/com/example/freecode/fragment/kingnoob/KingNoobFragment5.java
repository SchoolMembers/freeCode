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

public class KingNoobFragment5 extends Fragment {
    // Page 4

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment5 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment5, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 4; //참이면 아직 못 깸
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
            Log.d("Fragment", "KingNoobFragment5 checkLast true");
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
                    Log.d("Fragment", "KingNoobFragment5 nextButton visible, enabled true");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // 애니메이션 반복 시
                }
            });
        } else {
            nextButton.setVisibility(View.GONE);
        }


        //완료 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "King") < 5) {
                lastPage.setLastPage(context, "King", 5);
                Log.d("KingNoobActivity", "KingNoobFragment5 set lastPage : 5");
                KingNoobViewPagerAdapter adapter = (KingNoobViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(5);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        TextView des1 = view.findViewById(R.id.des1);
        TextView des2 = view.findViewById(R.id.des2);
        TextView des3 = view.findViewById(R.id.des3);
        TextView des4 = view.findViewById(R.id.des4);
        TextView des5 = view.findViewById(R.id.des5);
        TextView des5S = view.findViewById(R.id.des5_s);
        TextView des6 = view.findViewById(R.id.des6);
        TextView des7 = view.findViewById(R.id.des7);
        TextView des8 = view.findViewById(R.id.des8);
        TextView des9 = view.findViewById(R.id.des9);
        TextView des10 = view.findViewById(R.id.des10);
        TextView des11 = view.findViewById(R.id.des11);

        String text1 = des1.getText().toString();
        String text2 = des2.getText().toString();
        String text3 = des3.getText().toString();
        String text4 = des4.getText().toString();
        String text5 = des5.getText().toString();
        String text5S = des5S.getText().toString();
        String text6 = des6.getText().toString();
        String text7 = des7.getText().toString();
        String text8 = des8.getText().toString();
        String text9 = des9.getText().toString();
        String text10 = des10.getText().toString();
        String text11 = des11.getText().toString();
        Log.d("Text", " " + getString(R.string.king_page5_des10));


        TextCustom textCustom = new TextCustom(des1, text1);
        textCustom.setPiece("변수 선언");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.size(1.3f);
        textCustom.setting();

        textCustom = new TextCustom(des2, text2);
        textCustom.setPiece("a");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("3");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textCustom = new TextCustom(des3, text3);
        textCustom.setPiece("● 변수 이름");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setPiece("● 변수에 대입할 값");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textCustom = new TextCustom(des4, text4);
        textCustom.setPiece("=");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkPurple));
        textCustom.setting();

        textCustom = new TextCustom(des5, text5);
        textCustom.setPiece("=");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.darkPurple));
        textCustom.setPiece("수학");
        textCustom.sizeAll(1.2f);
        textCustom.setPiece("코딩");
        textCustom.sizeAll(1.2f);
        textCustom.setPiece("결과를 출력하는 방법");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.size(1.3f);
        textCustom.setPiece("print()");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setting();

        textCustom = new TextCustom(des5S, text5S);
        textCustom.setPiece("주석");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.size(1.3f);
        textCustom.setPiece("#");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textCustom = new TextCustom(des6, text6);
        textCustom.setPiece("print(");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setPiece(")");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setting();

        textCustom = new TextCustom(des7, text7);
        textCustom.setPiece("결과: 오류 생김");
        textCustom.style(Typeface.BOLD_ITALIC);
        textCustom.setting();

        textCustom = new TextCustom(des8, text8);
        textCustom.setPiece("#더하기 결과");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("print(");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setPiece(")");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setting();

        textCustom = new TextCustom(des9, text9);
        textCustom.setPiece("결과: 4");
        textCustom.style(Typeface.BOLD_ITALIC);
        textCustom.setPiece("\"");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setting();

        textCustom = new TextCustom(des10, text10);
        textCustom.setPiece("\"\"\"더하기 결과를 알아볼까요?\nprint()로 출력을 해봅시다!!\"\"\"");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkBlue));
        textCustom.setPiece("print(");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setPiece(")");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkGreen));
        textCustom.setting();

        textCustom = new TextCustom(des11, text11);
        textCustom.setPiece("결과: 4");
        textCustom.style(Typeface.BOLD_ITALIC);
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
        if (lastPage.getLastPage(context, "King") > 4) {
            nextButton.setVisibility(View.GONE);
        } else {
            if (!isAnimationFinished) {
                nextButton.setEnabled(false);
                nextButton.startAnimation(anim);
            }
        }
    }
}
