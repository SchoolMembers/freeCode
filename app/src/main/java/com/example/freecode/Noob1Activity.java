package com.example.freecode;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.example.freecode.adapter.Noob1ViewPagerAdapter;
import com.example.freecode.databinding.ActivityNoob1Binding;
import com.example.freecode.methodClass.LastPageInfo;

public class Noob1Activity extends AppCompatActivity {

    LastPageInfo lastPageInfo;
    ViewPager2 viewPager;
    Noob1ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoob1Binding binding = ActivityNoob1Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        // 상단 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.topBar, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            return insets;
        });

        // 하단 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.viewPager, (view, insets) -> {
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(0, 0, 0, systemBarsInsets.bottom);
            return insets;
        });
        Log.d("Noob1Activity", "Insets setting completed for Noob1Activity");

        //진행도 저장 객체
        lastPageInfo = new LastPageInfo();
        int lastPage = lastPageInfo.getLastPage(this, "Noob1");

        //viewpager 설정
        viewPager = binding.viewPager;
        adapter = new Noob1ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(lastPage, false);
        viewPager.setUserInputEnabled(false); // 뷰 페이지 스크롤로 넘기기 비활

        //뒤로가기 버튼 (시스템)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 첫 번째 페이지에서는 액티비티 종료
                if (viewPager.getCurrentItem() == 0) {
                    Log.d("Noob1Activity", "called finish()");
                    finish();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1); // 이전 페이지로 이동
                }
            }
        });

        //뒤로가기 버튼 (앱)
        Button backButton = binding.back;
        backButton.setOnClickListener(v -> {
            finish();
        });

    }

    // 다음 페이지로 이동 메서드
    public void moveToNextPage() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem + 1 < adapter.getItemCount()) {
            viewPager.setCurrentItem(currentItem + 1, true);
        }
    }

    // 이전 페이지로 이동 메서드
    public void moveToBeforePage() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem -1 >= 0) {
            viewPager.setCurrentItem(currentItem - 1, true);
        }
    }
}
