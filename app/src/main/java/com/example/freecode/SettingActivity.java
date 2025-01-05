package com.example.freecode;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.freecode.databinding.ActivitySettingBinding;
import com.example.freecode.methodClass.NvgListener;
import com.google.android.material.navigation.NavigationBarView;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
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
        Log.d("SettingActivity", "Insets setting completed for SettingActivity");

        //메뉴 바 열고 닫기 ------------
        Button menuOpenButton = binding.openNavigation;
        Button menuCloseButton = binding.closeNavigation;
        ScrollView navView = binding.navView;
        NavigationBarView navRail = binding.navigation; //네비게이션 레일

        navRail.setSelectedItemId(R.id.setting);


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
        });

        // 메뉴 바 아이템 선택 리스너 설정
        navRail.setOnItemSelectedListener(item -> NvgListener.itemSelected(this, item.getItemId()));
    }
}
