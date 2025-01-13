package com.example.freecode.fragment.kingnoob;

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
import com.example.freecode.KingNoobActivity;
import com.example.freecode.R;
import com.example.freecode.adapter.KingNoobViewPagerAdapter;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;
import java.util.ArrayList;

public class KingNoobFragment6 extends Fragment {
    // Page 5

    private Button nextButton;
    LastPageInfo lastPage;
    Context context;

    private int choice = 0;
    ArrayList<TextView> answerList = new ArrayList<>();
    ArrayList<TextView> answerTextList = new ArrayList<>();
    TextView choiceView = null;

    ArrayList<TextView> alphabet = new ArrayList<>();
    ArrayList<String> alphabetString = new ArrayList<>();
    ArrayList<TextView> results = new ArrayList<>();
    ArrayList<Integer> resultIndex = new ArrayList<>();
    int resultCount = 0;

    LinearLayout quiz1 = null;
    LinearLayout quiz2 = null;
    LinearLayout quiz3 = null;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "KingNoobFragment6 started");
        view = inflater.inflate(R.layout.kingnoob_fragment6, container, false);

        context = requireContext();

        //클리어 챕터인지 아닌지 확인
        lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 5; //참이면 아직 못 깸
        nextButton = view.findViewById(R.id.next_button);
        Button beforeButton = view.findViewById(R.id.before_button);

        KingNoobActivity activity = (KingNoobActivity) requireActivity();

        if (!checkLast) {
            nextButton.setVisibility(View.GONE);
        }

        //왕초보 텍스트 설정
        TextView textView = view.findViewById(R.id.q1);
        String text = textView.getText().toString();
        TextCustom textCustom = new TextCustom(textView, text);
        textCustom.setPiece("왕초보");
        textCustom.size(1.3f);
        textCustom.setting();

        //객관식 문제
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
        choice5.setOnClickListener(v -> {
            setAnswer(choice, false);
            choice = 5;
            setAnswer(choice, true);
        });

        Button quizButton = view.findViewById(R.id.quiz_button);
        quiz1 = view.findViewById(R.id.quiz1);
        quizButton.setOnClickListener(v -> {
            if (choice == 0) { //선택 x
                Toast.makeText(context, ContextCompat.getString(context, R.string.nullAnswer), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, ContextCompat.getString(context, R.string.notAnswer), Toast.LENGTH_SHORT).show();
            }
        });


        //알파벳 선택 문제
        quiz2 = view.findViewById(R.id.quiz2);
        TextView q2 = view.findViewById(R.id.q2);
        String text2 = q2.getText().toString();
        textCustom = new TextCustom(q2, text2);
        textCustom.setPiece("왕초보");
        textCustom.size(1.3f);
        textCustom.setting();

        alphabet.add(view.findViewById(R.id.a1)); //0
        alphabet.add(view.findViewById(R.id.a2));
        alphabet.add(view.findViewById(R.id.a3));
        alphabet.add(view.findViewById(R.id.a4));
        alphabet.add(view.findViewById(R.id.a5));
        alphabet.add(view.findViewById(R.id.b1));
        alphabet.add(view.findViewById(R.id.b2));
        alphabet.add(view.findViewById(R.id.b3));
        alphabet.add(view.findViewById(R.id.b4));
        alphabet.add(view.findViewById(R.id.b5)); //9
        for (int i = 0; i < alphabet.size(); i++) {
            alphabetString.add(alphabet.get(i).getText().toString());
        }

        resultIndex.add(-1);
        resultIndex.add(-1);
        resultIndex.add(-1);

        results.add(view.findViewById(R.id.result1));
        results.add(view.findViewById(R.id.result2));
        results.add(view.findViewById(R.id.result3));

        alphabet.get(0).setOnClickListener(v -> {
            setAlphabet(0);
        });
        alphabet.get(1).setOnClickListener(v -> {
            setAlphabet(1);
        });
        alphabet.get(2).setOnClickListener(v -> {
            setAlphabet(2);
        });
        alphabet.get(3).setOnClickListener(v -> {
            setAlphabet(3);
        });
        alphabet.get(4).setOnClickListener(v -> {
            setAlphabet(4);
        });
        alphabet.get(5).setOnClickListener(v -> {
            setAlphabet(5);
        });
        alphabet.get(6).setOnClickListener(v -> {
            setAlphabet(6);
        });
        alphabet.get(7).setOnClickListener(v -> {
            setAlphabet(7);
        });
        alphabet.get(8).setOnClickListener(v -> {
            setAlphabet(8);
        });
        alphabet.get(9).setOnClickListener(v -> {
            setAlphabet(9);
        });
        results.get(0).setOnClickListener(v -> {
            setResult(0);
        });
        results.get(1).setOnClickListener(v -> {
            setResult(1);
        });
        results.get(2).setOnClickListener(v -> {
            setResult(2);
        });



        //완료 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "King") < 6) {
                lastPage.setLastPage(context, "King", 6);
                Log.d("KingNoobActivity", "KingNoobFragment6 set lastPage : 6");
                KingNoobViewPagerAdapter adapter = (KingNoobViewPagerAdapter) activity.getViewPager().getAdapter();
                if (adapter != null) {
                    adapter.refreshFragment(6);
                }
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());


        return view;
    }

    //알파벳 선택 처리 함수 (알파벳 클릭 시)
    private void setAlphabet(int index) {
        if (!(index < 0 || index >= alphabet.size())) {
            if (resultCount < 3) {
                alphabet.get(index).setVisibility(View.INVISIBLE);
                alphabet.get(index).setEnabled(false);
                for (int i=0; i<3; i++) {
                    if (resultIndex.get(i) == -1) {
                        resultIndex.set(i, index);
                        results.get(i).setText(alphabetString.get(index));
                        resultCount++;
                        if (resultCount == 3) {
                            int result1 = resultIndex.get(0);
                            int result2 = resultIndex.get(1);
                            int result3 = resultIndex.get(2);
                            if (result1 == 5 && result2 == 8 && result3 == 2)  {
                                LottieAnimationView completed = view.findViewById(R.id.completed);
                                if (completed != null) {
                                    quiz2.setVisibility(View.GONE);
                                    completed.setVisibility(View.VISIBLE);
                                    completed.setAnimation(R.raw.great1);
                                    completed.setRepeatCount(0);
                                    completed.playAnimation();
                                    completed.removeAllAnimatorListeners(); //애니메이션 재사용 위한 세팅
                                    setLottieAnimationListener(completed, 2);
                                }
                            } else {
                                Toast.makeText(context, ContextCompat.getString(context, R.string.notAnswer), Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    //알파벳 처리 함수 (결과창 클릭 시)
    private void setResult(int index) {
        if (!(index < 0 || index >= results.size())) {
            if (resultIndex.get(index) != -1) {
                alphabet.get(resultIndex.get(index)).setVisibility(View.VISIBLE);
                alphabet.get(resultIndex.get(index)).setEnabled(true);
                resultIndex.set(index, -1);
                results.get(index).setText("");
                resultCount--;
            }
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
                Log.d("LottieAnimation", "Animation finished!");
                if (num == 1) {
                    animationView.setVisibility(View.GONE);
                    quiz2.setVisibility(View.VISIBLE);
                } else if (num == 2) {

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
                case 5:
                    choiceView.setText(ContextCompat.getString(context, R.string.num5));
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
        if (lastPage.getLastPage(context, "King") > 5) {
            nextButton.setVisibility(View.GONE);
        }
    }
}



