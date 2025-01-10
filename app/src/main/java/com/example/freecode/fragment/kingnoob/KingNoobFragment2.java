package com.example.freecode.fragment.kingnoob;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
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
import com.example.freecode.methodClass.DialogInfo;
import com.example.freecode.methodClass.LastPageInfo;

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
            }
        });

        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        //텍스트 강조 세팅
        int start, end;
        TextView des1 = view.findViewById(R.id.des1);
        TextView des2 = view.findViewById(R.id.des2);
        TextView des3 = view.findViewById(R.id.des3);

        String text1 = des1.getText().toString();
        String text2 = des2.getText().toString();
        String text3 = des3.getText().toString();

        SpannableString span = new SpannableString(text1);
        String word = "프로그래밍";
        start = text1.indexOf(word);
        if (start >= 0) {
            end = start + word.length();
            span.setSpan(new BackgroundColorSpan(ContextCompat.getColor(context, R.color.lightYellow)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new RelativeSizeSpan(1.1f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            des1.setText(span);
        }

        span = new SpannableString(text2);
        start = text2.indexOf(word);
        if (start >= 0) {
            end = start + word.length();
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.hotPink)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        word = "컴퓨터에게 일을 시키기 위한 설계도를 만드는 작업";
        start = text2.indexOf(word);
        if (start >= 0) {
            end = start + word.length();
            span.setSpan(new RelativeSizeSpan(1.1f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        des2.setText(span);

        span = new SpannableString(text3);
        word = "자연어";
        start = text3.indexOf(word);
        if (start >= 0) {
            end = start + word.length();
            span.setSpan(new RelativeSizeSpan(1.1f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.lightPurple)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        word = "기계어";
        start = text3.indexOf(word);
        if (start >= 0) {
            end = start + word.length();
            span.setSpan(new RelativeSizeSpan(1.1f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.lightPurple)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        word = "Python";
        start = text3.indexOf(word);
        while (start >= 0) {
            end = start + word.length();
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text3.indexOf(word, end);
        }
        word = "프로그래밍 언어";
        start = text3.indexOf(word);
        while (start >= 0) {
            end = start + word.length();
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text3.indexOf(word, end);
        }
        des3.setText(span);

        // info 다이얼로그
        Button infoButton = view.findViewById(R.id.info);
        infoButton.setOnClickListener(v -> {
            DialogInfo dialog = new DialogInfo(context);
            String text = context.getString(R.string.king_page2_dialog);
            SpannableString spannableString = new SpannableString(text);

            String textPiece = "프로그래밍과 코딩의 차이?";
            int startIndex = text.indexOf(textPiece);
            int endIndex;
            if (startIndex >= 0) {
                endIndex = startIndex + textPiece.length();
                spannableString.setSpan(new RelativeSizeSpan(1.1f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            textPiece = "프로그래밍";
            startIndex = text.indexOf(textPiece);
            while (startIndex >= 0) {
                endIndex = startIndex + textPiece.length();
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.darkPurple)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                startIndex = text.indexOf(textPiece, endIndex);
            }

            textPiece = "코딩";
            startIndex = text.indexOf(textPiece);
            while (startIndex >= 0) {
                endIndex = startIndex + textPiece.length();
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.lightPurple)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                startIndex = text.indexOf(textPiece, endIndex);
            }
            dialog.setText(spannableString, 1);
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