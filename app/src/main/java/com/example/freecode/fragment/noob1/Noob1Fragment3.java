package com.example.freecode.fragment.noob1;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.freecode.Noob1Activity;
import com.example.freecode.R;
import com.example.freecode.adapter.Noob1ViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

import java.util.ArrayList;

public class Noob1Fragment3 extends Fragment {

    View view;
    LastPageInfo lastPage;
    boolean checkLast;
    private Button finishButton;
    Context context;
    TextView completeInfo;

    //퀴즈
    LinearLayout quiz1 = null;
    LinearLayout quiz2 = null;
    LinearLayout quiz3 = null;

    //객관식 문제
    private int choice = 0;
    ArrayList<TextView> answerList = new ArrayList<>();
    ArrayList<TextView> answerTextList = new ArrayList<>();
    TextView choiceView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob1Fragment3 started");
        view = inflater.inflate(R.layout.noob1_fragment3, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        checkLast = lastPage.getLastPage(context, "Noob1") <= 5; //참이면 아직 못 깸
        finishButton = view.findViewById(R.id.finish_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        Noob1Activity activity = (Noob1Activity) requireActivity();

        if (!checkLast) {
            finishButton.setVisibility(View.GONE);
        }

        //완료 정보
        completeInfo = view.findViewById(R.id.info);

        //초급1 텍스트 설정
        TextView textView = view.findViewById(R.id.q1);
        String text = textView.getText().toString();
        TextCustom textCustom = new TextCustom(textView, text);
        textCustom.setPiece("초급1");
        textCustom.size(1.3f);
        textCustom.setting();

        //객관식 문제
        LinearLayout choice1 = view.findViewById(R.id.choice1);
        LinearLayout choice2 = view.findViewById(R.id.choice2);
        LinearLayout choice3 = view.findViewById(R.id.choice3);
        LinearLayout choice4 = view.findViewById(R.id.choice4);
        answerList.add(view.findViewById(R.id.answer1));
        answerList.add(view.findViewById(R.id.answer2));
        answerList.add(view.findViewById(R.id.answer3));
        answerList.add(view.findViewById(R.id.answer4));
        answerTextList.add(view.findViewById(R.id.answer1t));
        answerTextList.add(view.findViewById(R.id.answer2t));
        answerTextList.add(view.findViewById(R.id.answer3t));
        answerTextList.add(view.findViewById(R.id.answer4t));

        choiceView = view.findViewById(R.id.choice);

        choice1.setOnClickListener(v -> {
            setAnswer(choice, false);
            choice = 1;
            setAnswer(choice, true);
        });
        choice2.setOnClickListener(v -> {
            setAnswer(choice, false);
            choice = 2;
            setAnswer(choice, true);
        });
        choice3.setOnClickListener(v -> {
            setAnswer(choice, false);
            choice = 3;
            setAnswer(choice, true);
        });
        choice4.setOnClickListener(v -> {
            setAnswer(choice, false);
            choice = 4;
            setAnswer(choice, true);
        });

        Button quizButton = view.findViewById(R.id.quiz_button);
        quiz1 = view.findViewById(R.id.quiz1);
        quizButton.setOnClickListener(v -> {
            if (choice == 0) { //선택 x
                Toast.makeText(context, ContextCompat.getString(context, R.string.null_answer), Toast.LENGTH_SHORT).show();
            } else if (choice == 3) { //정답
                LottieAnimationView completed = view.findViewById(R.id.completed);
                if (completed != null) {
                    quiz1.setVisibility(View.GONE);
                    completed.setVisibility(View.VISIBLE);
                    completed.setAnimation(R.raw.clap);
                    completed.setRepeatCount(0);
                    completed.playAnimation();
                    setLottieAnimationListener(completed, 1);
                }
            } else { //오답
                Toast.makeText(context, ContextCompat.getString(context, R.string.not_answer), Toast.LENGTH_SHORT).show();
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        return view;
    }

    //애니메이션 리스너
    private void setLottieAnimationListener(LottieAnimationView animationView, int num) {
        if (animationView == null) return;

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 애니메이션 시작 시
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 애니메이션이 끝난 후
                Log.d("LottieAnimation", "Animation finished!");
                if (num == 1) {
                    animationView.setVisibility(View.GONE);
                    quiz2.setVisibility(View.VISIBLE);
                } else if (num == 2) {
                    quiz3.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);
                } else if (num == 3) {
                    if (!checkLast) {
                        finishButton.setVisibility(View.GONE);
                        completeInfo.setVisibility(View.GONE);
                    } else {
                        completeInfo.setVisibility(View.VISIBLE);
                        finishButton.setVisibility(View.VISIBLE);
                    }
                    quiz3.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 애니메이션이 취소되었을 때
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // 애니메이션이 반복될 때
            }
        });
    }
    //객관식 답 선택
    private void setAnswer(int choice, boolean red) {
        if (choice != 0) {
            if (red) {
                answerList.get(choice-1).setTextColor(ContextCompat.getColor(context, R.color.darkRed));
                answerTextList.get(choice-1).setTextColor(ContextCompat.getColor(context, R.color.darkRed));
                setChoiceView(choice);
            } else {
                answerList.get(choice-1).setTextColor(ContextCompat.getColor(context, R.color.colorSecondary));
                answerTextList.get(choice-1).setTextColor(ContextCompat.getColor(context, R.color.colorSecondary));
            }
        }
    }

    //객관식 답 선택
    private void setChoiceView(int choice) {
        if (choiceView != null) {
            switch (choice) {
                case 1:
                    choiceView.setText(ContextCompat.getString(context, R.string.num1));
                    break;
                case 2:
                    choiceView.setText(ContextCompat.getString(context, R.string.num2));
                    break;
                case 3:
                    choiceView.setText(ContextCompat.getString(context, R.string.num3));
                    break;
                case 4:
                    choiceView.setText(ContextCompat.getString(context, R.string.num4));
                    break;
                default:
                    choiceView.setText("");
                    break;
            }
        }
    }
    // 페이지로 돌아올 때
    @Override
    public void onResume() {
        super.onResume();

        // [이전 버튼 -> 다음 버튼] 레이아웃 오류 방지
        if (lastPage.getLastPage(context, "Noob1") > 5) {
            finishButton.setVisibility(View.GONE);
            completeInfo.setVisibility(View.GONE);
        }
    }
}
