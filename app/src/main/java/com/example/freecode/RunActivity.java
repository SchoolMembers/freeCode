package com.example.freecode;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.freecode.databinding.ActivityRunBinding;
import com.example.freecode.methodClass.DialogInfo;
import com.example.freecode.methodClass.NvgListener;
import com.example.freecode.methodClass.TextCustom;
import com.google.android.material.navigation.NavigationBarView;


public class RunActivity  extends AppCompatActivity {

    private ActivityRunBinding binding;

    private int checkOpen = 0; //0은 오픈x, 1은 오픈 상태


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRunBinding.inflate(getLayoutInflater());
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
        Log.d("RunActivity", "Insets setting completed for RunActivity");

        // Python init
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        //메뉴 바 열고 닫기 ------------
        Button menuOpenButton = binding.openNavigation;
        Button menuCloseButton = binding.closeNavigation;
        ScrollView navView = binding.navView;
        NavigationBarView navRail = binding.navigation; //네비게이션 레일

        navRail.setSelectedItemId(R.id.run);


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

        //파이썬
        EditText codeInput = binding.codeInput;
        Button runButton = binding.runButton;
        TextView outputView = binding.outputView;

        runButton.setOnClickListener(v -> {
            String userCode = codeInput.getText().toString();

            if (!userCode.isEmpty()) {
                try (PyObject result = Python.getInstance()
                        .getModule("main")
                        .callAttr("eval", userCode)) {
                    outputView.setText(result.toString());
                    Log.d("RunActivity", "User code: " + userCode);
                } catch (Exception e) {
                    outputView.setText(String.format("Error: %s", e.getMessage()));
                }
            } else {
                outputView.setText(ContextCompat.getString(this, R.string.emtpy_code));
            }
        });

        //제한된 기능 보기
        TextView info = binding.info;
        TextView infoDes = binding.infoDes;

        TextCustom textCustom = new TextCustom(infoDes, ContextCompat.getString(this, R.string.run_info));

        textCustom.setPiece("과부하 방지 및 보안 제한");
        textCustom.background(ContextCompat.getColor(this, R.color.lightYellow));
        textCustom.setPiece("FreeCode에서 제공하는 Python 기능");
        textCustom.background(ContextCompat.getColor(this, R.color.lightYellow));
        textCustom.setPiece("3초");
        textCustom.size(1.2f);
        textCustom.setPiece("input()");
        textCustom.size(1.2f);
        textCustom.setPiece("FreeCode");
        textCustom.textColorAll(ContextCompat.getColor(this, R.color.violet));
        textCustom.setPiece("math.pi");
        textCustom.background(ContextCompat.getColor(this, R.color.lightGray));
        textCustom.setPiece("import math");
        textCustom.background(ContextCompat.getColor(this, R.color.lightGray));
        textCustom.setting();


        info.setOnClickListener(v -> {
            if (checkOpen == 0) {
                checkOpen = 1;
                info.setText(ContextCompat.getString(this, R.string.close_info));
                infoDes.setVisibility(View.VISIBLE);
            } else {
                checkOpen = 0;
                info.setText(ContextCompat.getString(this, R.string.open_info));
                infoDes.setVisibility(View.GONE);
            }
        });
    }
}
