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
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.freecode.Noob2Activity;
import com.example.freecode.R;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.TextCustom;

import java.util.ArrayList;
import java.util.List;

public class Noob2Fragment1 extends Fragment {
    // Page 0

    //그림 텍스트뷰
    private final List<TextView> imageTextViews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragment", "Noob2Fragment1 started");
        View view = inflater.inflate(R.layout.noob2_fragment1, container, false);

        Context context = requireContext();

        //클리어 챕터인지 아닌지 확인
        LastPageInfo lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "Noob2") <= 0; // 0 이하면 true

        if (checkLast) {
            lastPage.setLastPage(context, "Noob2", 0);
            Log.d("Noob2Activity", "Noob2Fragment1 set lastPage : 0");
        }

        Noob2Activity activity = (Noob2Activity) requireActivity();

        //버튼 천천히 보이게
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha0_to_show);
        Button nextButton = view.findViewById(R.id.next_button);

        if (checkLast) {
            Log.d("Fragment", "Noob2Fragment1 checkLast true");
            anim.setDuration(5000); // 5초
            nextButton.setEnabled(false);

            // 애니메이션 리스너
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // 애니메이션 시작 시
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // 애니메이션 종료 시
                    nextButton.setEnabled(true);
                    Log.d("Fragment", "Noob2Fragment1 nextButton visible, enabled true");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // 애니메이션 반복 시
                }
            });

            nextButton.startAnimation(anim);
        }


        //다음 버튼 리스너
        nextButton.setOnClickListener(v -> {
            activity.moveToNextPage();
            // 현재 페이지가 현재 진행도의 마지막 페이지일 때
            if (lastPage.getLastPage(context, "Noob2") < 1) {
                lastPage.setLastPage(context, "Noob2", 1);
                Log.d("Noob2Activity", "Noob2Fragment1 set lastPage : 1");
            }
        });

        //텍스트 커스텀
        TextView textView = view.findViewById(R.id.des1);
        String text = textView.getText().toString();
        TextCustom textCustom = new TextCustom(textView, text);
        textCustom.setPiece("! 메모리 (Memory)");
        textCustom.size(1.3f);
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.setPiece("! 객체 (Object)");
        textCustom.size(1.3f);
        textCustom.background(ContextCompat.getColor(context, R.color.lightYellow));
        textCustom.setPiece("메모리 주소(상자 위치)를 참조(기억)");
        textCustom.textColor(ContextCompat.getColor(context, R.color.purple));
        textCustom.setPiece("그림의 각 부분을 클릭해보세요!");
        textCustom.size(0.8f);
        textCustom.textColor(ContextCompat.getColor(context, R.color.gray));
        textCustom.setPiece("Tip");
        textCustom.background(ContextCompat.getColor(context, R.color.yellow));
        textCustom.setting();

        textView = view.findViewById(R.id.image_text1);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("1234");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setting();

        textView = view.findViewById(R.id.image_text2);
        text = textView.getText().toString();
        textCustom = new TextCustom(textView, text);
        textCustom.setPiece("1234");
        textCustom.textColor(ContextCompat.getColor(context, R.color.darkRed));
        textCustom.setting();

        //그림 클릭 리스너
        imageTextViews.add(view.findViewById(R.id.object));
        imageTextViews.add(view.findViewById(R.id.object_text));
        imageTextViews.add(view.findViewById(R.id.image_text1));
        imageTextViews.add(view.findViewById(R.id.image_text2));
        imageTextViews.add(view.findViewById(R.id.back_view));

        setImagesListener(context);

        view.findViewById(R.id.box).setOnClickListener(v -> {
            Toast.makeText(context, ContextCompat.getString(context, R.string.noob2_page1_image1), Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.code_box).setOnClickListener(v -> {
            Toast.makeText(context, ContextCompat.getString(context, R.string.noob2_page1_image4), Toast.LENGTH_SHORT).show();
        });

        return view;
    }


    //그림 터치 리스너 세팅
    private void setImagesListener(Context context) {
        String[] strings = {
                ContextCompat.getString(context, R.string.noob2_page1_image2),
                ContextCompat.getString(context, R.string.noob2_page1_image3),
        };
        for (int i=0; i<imageTextViews.size(); i++) {
            final int index = i;
            imageTextViews.get(index).setOnClickListener(v -> {
                if (index < 3) {
                    Toast.makeText(context, strings[0], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, strings[1], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getView();
        if (view != null) {
            Button nextButton = view.findViewById(R.id.next_button);
            if (nextButton != null) {
                nextButton.clearAnimation();
            }
        }
    }
}
