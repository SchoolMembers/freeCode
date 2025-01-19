package com.example.freecode.fragment.noob2;

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
import com.example.freecode.Noob2Activity;
import com.example.freecode.R;
import com.example.freecode.adapter.Noob2ViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;

import java.util.ArrayList;

public class Noob2Fragment3 extends Fragment {
    // Page 2
    private Button nextButton;
    private LastPageInfo lastPage;
    private Context context;
    private Noob2Activity activity = null;

    //quiz
    private final int[] choiceList = {0, 0, 0, 0, 0};
    private final ArrayList<TextView> answerList = new ArrayList<>();
    private final ArrayList<TextView> answerTextList = new ArrayList<>();
    private TextView choiceView = null;
    private LinearLayout quiz1;
    private boolean checkComplete = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob2Fragment3 started");
        View view = inflater.inflate(R.layout.noob2_fragment3, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob2") <= 2; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        activity = (Noob2Activity) requireActivity();

        //버튼 활성화
        if (!checkLast) {
            nextButton.setEnabled(true);
            nextButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setEnabled(false);
            nextButton.setVisibility(View.GONE);
        }

        //다음 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "Noob2") < 3) {
                lastPage.setLastPage(context, "Noob2", 3);
                Log.d("Noob2Activity", "Noob2Fragment3 set lastPage : 3");
                Noob2ViewPagerAdapter adapter = (Noob2ViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(3);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //퀴즈
        LinearLayout choice1 = view.findViewById(R.id.choice1);
        LinearLayout choice2 = view.findViewById(R.id.choice2);
        LinearLayout choice3 = view.findViewById(R.id.choice3);
        LinearLayout choice4 = view.findViewById(R.id.choice4);
        LinearLayout choice5 = view.findViewById(R.id.choice5);
        answerList.add(view.findViewById(R.id.answer1));
        answerList.add(view.findViewById(R.id.answer2));
        answerList.add(view.findViewById(R.id.answer3));
        answerList.add(view.findViewById(R.id.answer4));
        answerList.add(view.findViewById(R.id.answer5));
        answerTextList.add(view.findViewById(R.id.answer1t));
        answerTextList.add(view.findViewById(R.id.answer2t));
        answerTextList.add(view.findViewById(R.id.answer3t));
        answerTextList.add(view.findViewById(R.id.answer4t));
        answerTextList.add(view.findViewById(R.id.answer5t));

        choiceView = view.findViewById(R.id.choice);

        choice1.setOnClickListener(v -> {
            if (choiceList[0] == 0) {
                choiceList[0] = 1;
                setAnswer(1,true);
            } else {
                choiceList[0] = 0;
                setAnswer(1,false);
            }
        });
        choice2.setOnClickListener(v -> {
            if (choiceList[1] == 0) {
                choiceList[1] = 1;
                setAnswer(2,true);
            } else {
                choiceList[1] = 0;
                setAnswer(2,false);
            }
        });
        choice3.setOnClickListener(v -> {
            if (choiceList[2] == 0) {
                choiceList[2] = 1;
                setAnswer(3,true);
            } else {
                choiceList[2] = 0;
                setAnswer(3,false);
            }
        });
        choice4.setOnClickListener(v -> {
            if (choiceList[3] == 0) {
                choiceList[3] = 1;
                setAnswer(4,true);
            } else {
                choiceList[3] = 0;
                setAnswer(4,false);
            }
        });
        choice5.setOnClickListener(v -> {
            if (choiceList[4] == 0) {
                choiceList[4] = 1;
                setAnswer(5,true);
            } else {
                choiceList[4] = 0;
                setAnswer(5,false);
            }
        });

        Button quizButton = view.findViewById(R.id.quiz_button);
        quiz1 = view.findViewById(R.id.quiz1);
        quizButton.setOnClickListener(v -> {
            int choice = 0;
            for (int i=0; i<choiceList.length; i++) {
                if (choiceList[i] == 1) {
                    if (i == 0 || i == 1 || i == 4) {
                        choice++;
                    } else {
                        choice--;
                    }
                }
            }
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
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                    checkComplete = true;
                }
            } else { //오답
                Toast.makeText(context, ContextCompat.getString(context, R.string.not_answer), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //객관식 답 선택
    private void setAnswer(int index, boolean red) {
        if (red) {
            answerList.get(index-1).setTextColor(ContextCompat.getColor(context, R.color.darkRed));
            answerTextList.get(index-1).setTextColor(ContextCompat.getColor(context, R.color.darkRed));
            setChoiceView();
        } else {
            answerList.get(index-1).setTextColor(ContextCompat.getColor(context, R.color.colorSecondary));
            answerTextList.get(index-1).setTextColor(ContextCompat.getColor(context, R.color.colorSecondary));
            setChoiceView();
        }
    }

    private void setChoiceView() {
        if (choiceView != null) {
            StringBuilder textBuilder = new StringBuilder();
            for (int i=0; i<choiceList.length; i++) {
                if (choiceList[i] == 1) {
                    switch (i) {
                        case 0:
                            textBuilder.append(" ").append(ContextCompat.getString(context, R.string.num1));
                            break;
                        case 1:
                            textBuilder.append(" ").append(ContextCompat.getString(context, R.string.num2));
                            break;
                        case 2:
                            textBuilder.append(" ").append(ContextCompat.getString(context, R.string.num3));
                            break;
                        case 3:
                            textBuilder.append(" ").append(ContextCompat.getString(context, R.string.num4));
                            break;
                        case 4:
                            textBuilder.append(" ").append(ContextCompat.getString(context, R.string.num5));
                            break;
                        default:
                            break;
                    }
                }
            }
            String text = textBuilder.toString().trim();
            choiceView.setText(text);
        }
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
                animationView.setVisibility(View.GONE);
                quiz1.setVisibility(View.VISIBLE);
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

    // 페이지로 돌아올 때
    @Override
    public void onResume() {
        super.onResume();

        // [이전 버튼 -> 다음 버튼] 레이아웃 오류 방지
        if (lastPage.getLastPage(context, "Noob2") > 2 || checkComplete) {
            nextButton.setEnabled(true);
            nextButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setEnabled(false);
            nextButton.setVisibility(View.GONE);
        }
    }
}
