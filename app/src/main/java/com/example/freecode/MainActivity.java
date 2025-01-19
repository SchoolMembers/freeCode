package com.example.freecode;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freecode.databinding.ActivityMainBinding;
import com.example.freecode.methodClass.CardViews;
import com.example.freecode.methodClass.LastPageInfo;
import com.example.freecode.methodClass.NvgListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //checkInit 값이 0일 때는 menu gone, 1일 때는 visible
    public static int checkInit = 0;

    //카드 뷰 바인딩 리스트
    private final List<CardViews> cardViews = new ArrayList<>();

    ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        // 상단 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.topBar, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            return insets;
        });

        // 하단 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainView, (view, insets) -> {
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(0, 0, 0, systemBarsInsets.bottom);
            return insets;
        });
        Log.d("MainActivity", "Insets setting completed for MainActivity");

        //메뉴 바 열고 닫기 ------------
        Button menuOpenButton = binding.openNavigation;
        Button menuCloseButton = binding.closeNavigation;
        ScrollView navView = binding.navView;
        NavigationBarView navRail = binding.navigation; //네비게이션 레일

        //초기 위치 세팅
        if (checkInit == 0) {
            navView.setVisibility(View.INVISIBLE);
            navView.post(() -> {
                int navWidth = navView.getWidth();
                navView.setTranslationX(-navWidth);
                navView.setVisibility(View.GONE);
                Log.d("MainActivity", String.format("Navigation rail location set: %d", navView.getWidth()));
            });
        } else {
            navView.setVisibility(View.VISIBLE);
            menuOpenButton.setVisibility(View.GONE);
            menuCloseButton.setVisibility(View.VISIBLE);
        }

        // 메뉴 열기
        menuOpenButton.setOnClickListener(v -> {

            navView.setVisibility(View.VISIBLE);

            //-navView.getWidth()에서 시작해서 0까지로 (0은 뷰의 원래 위치)
            ObjectAnimator animator = ObjectAnimator.ofFloat(navView, "translationX", -navView.getWidth(), 0);
            animator.setDuration(500); // 애니메이션 지속 시간 (ms)
            animator.start();

            menuOpenButton.setVisibility(View.GONE);
            menuCloseButton.setVisibility(View.VISIBLE);
        });

        // 메뉴 닫기
        menuCloseButton.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(navView, "translationX", 0, -navView.getWidth());
            animator.setDuration(500); // 애니메이션 지속 시간 (ms)
            animator.start();

            animator.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    navView.setVisibility(View.GONE); // 애니메이션 종료 후 숨김
                }
            });

            menuOpenButton.setVisibility(View.VISIBLE);
            menuCloseButton.setVisibility(View.GONE);
            checkInit = 0;
            Log.d("MainActivity", "checkInit 0");
        });

        // 메뉴 바 아이템 선택 리스너 설정
        navRail.setOnItemSelectedListener(item -> NvgListener.itemSelected(this, item.getItemId()));

        //카드 뷰 세팅
        cardViews.add(new CardViews(binding.kingNoob, binding.progressKingNoob, 0, this));
        cardViews.add(new CardViews(binding.noob1, binding.progressNoob1, 1, this));
        cardViews.add(new CardViews(binding.noob2, binding.progressNoob2, 2, this));

        //챕터 선택
        for (int i = 0; i < cardViews.size(); i++) {
            int index = i;
            cardViews.get(i).getCardView().setOnClickListener(v -> {
                handleCardClick(index);
            });
        }


        //진행도 세팅 - 초기화
        setProgressText(cardViews.size());


        //진행도 세팅 - 챕터에서 메인으로 돌아올 때
        activityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // 어떤 액티비티에서 온 결과인지 구분
                            String source = data.getStringExtra("source");
                            int progressPercentage = data.getIntExtra("progress", 0);

                            if ("KingNoob".equals(source)) {
                                cardViews.get(0).getTextView().setText(String.format(Locale.getDefault(), "진행도 %d%%", progressPercentage));
                            } else if ("Noob1".equals(source)) {
                                cardViews.get(1).getTextView().setText(String.format(Locale.getDefault(), "진행도 %d%%", progressPercentage));
                            } else if ("Noob2".equals(source)) {
                                cardViews.get(2).getTextView().setText(String.format(Locale.getDefault(), "진행도 %d%%", progressPercentage));
                            }
                        }
                    }
                }
        );

    }

    //진행도 초기화 함수
    private void setProgressText(int count) {
        for (int i=0; i<count; i++) {
            cardViews.get(i).setProgressText();
        }
    }


    //카드 뷰 클릭 인텐트 액티비티
    private void handleCardClick(int index) {
        Intent intent;
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0);

        switch (index) {
            case 0: // 첫 번째 카드뷰 클릭
                intent = new Intent(this, KingNoobActivity.class);
                activityLauncher.launch(intent, options);
                break;
            case 1:
                intent = new Intent(this, Noob1Activity.class);
                activityLauncher.launch(intent, options);
                break;
            case 2:
                intent = new Intent(this, Noob2Activity.class);
                activityLauncher.launch(intent, options);
                break;
            default:
                return;
        }


    }
}