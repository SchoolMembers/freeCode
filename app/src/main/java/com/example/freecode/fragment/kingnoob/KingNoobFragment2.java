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
import com.example.freecode.adapter.KingNoobViewPagerAdapter;
import com.example.freecode.methodClass.DialogInfo;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

public class KingNoobFragment2 extends Fragment {
    // Page 1

    private boolean isAnimationFinished = false;
    private Button nextButton;
    Animation anim;
    LastPageInfo lastPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment2 started");
        View view = inflater.inflate(R.layout.kingnoob_fragment2, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 1; //참이면 아직 못 깸
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
            Log.d("Fragment", "KingNoobFragment2 checkLast true");
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
                    Log.d("Fragment", "KingNoobFragment2 nextButton visible, enabled true");
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
            if (lastPage.getLastPage(context, "King") < 2) {
                lastPage.setLastPage(context, "King", 2);
                Log.d("KingNoobActivity", "KingNoobFragment2 set lastPage : 2");
                KingNoobViewPagerAdapter adapter = (KingNoobViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(2);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //텍스트 강조 세팅
        TextView des1 = view.findViewById(R.id.des1);
        TextView des2 = view.findViewById(R.id.des2);
        TextView des3 = view.findViewById(R.id.des3);

        String text1 = des1.getText().toString();
        String text2 = des2.getText().toString();
        String text3 = des3.getText().toString();

        TextCustom textCustom = new TextCustom(des1, text1);
        textCustom.setPiece("프로그래밍");
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.size(1.1f);
        textCustom.setting();

        textCustom = new TextCustom(des2, text2);
        textCustom.setPiece("프로그래밍");
        textCustom.textColor(ContextCompat.getColor(context, R.color.hotPink));
        textCustom.setPiece("컴퓨터에게 일을 시키기 위한 설계도를 만드는 작업");
        textCustom.size(1.1f);
        textCustom.setting();

        textCustom = new TextCustom(des3, text3);
        textCustom.setPiece("자연어");
        textCustom.size(1.1f);
        textCustom.textColor(ContextCompat.getColor(context, R.color.lightPurple));
        textCustom.setPiece("기계어");
        textCustom.size(1.1f);
        textCustom.textColor(ContextCompat.getColor(context, R.color.lightPurple));
        textCustom.setPiece("Python");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.blue));
        textCustom.setPiece("프로그래밍 언어");
        textCustom.textColorAll(ContextCompat.getColor(context, R.color.green));
        textCustom.setting();

        // info 다이얼로그
        Button infoButton = view.findViewById(R.id.info);
        infoButton.setOnClickListener(v -> {
            DialogInfo dialog = new DialogInfo(context);
            String text = context.getString(R.string.king_page2_dialog);

            TextCustom dialogText = new TextCustom(text);

            dialogText.setPiece("프로그래밍과 코딩의 차이?");
            dialogText.size(1.1f);
            dialogText.setPiece("프로그래밍");
            dialogText.textColorAll(ContextCompat.getColor(context, R.color.darkPurple));
            dialogText.setPiece("코딩");
            dialogText.textColorAll(ContextCompat.getColor(context, R.color.lightPurple));

            dialog.setText(dialogText.getSpan(), 1);
            dialog.show();
            Log.d("Fragment", "KingNoobFragment2 info dialog show");
        });
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