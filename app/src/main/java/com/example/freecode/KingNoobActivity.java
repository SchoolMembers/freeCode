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
import com.example.freecode.adapter.KingNoobViewPagerAdapter;
import com.example.freecode.databinding.ActivityKingnoobBinding;

public class KingNoobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityKingnoobBinding binding = ActivityKingnoobBinding.inflate(getLayoutInflater());
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
        Log.d("KingNoobActivity", "Insets setting completed for KingNoobActivity");

        //viewpager 설정
        ViewPager2 viewPager = binding.viewPager;
        KingNoobViewPagerAdapter adapter = new KingNoobViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        //뒤로가기 버튼 (시스템)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 첫 번째 페이지에서는 액티비티 종료
                if (viewPager.getCurrentItem() == 0) {
                    Log.d("KingNoobActivity", "called finish()");
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
}
