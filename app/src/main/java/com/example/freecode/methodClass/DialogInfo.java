package com.example.freecode.methodClass;

import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.freecode.R;

public class DialogInfo {

    private final AlertDialog dialog;
    private final View dialogView;

    public DialogInfo(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.dialog_info, null);
        builder.setView(dialogView);
        dialog = builder.create();

        // 닫기 버튼 설정
        Button closeButton = dialogView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> dialog.dismiss());
    }

    //텍스트 설정
    public void setText(String text, int num) {
        if (dialog != null) {
            if (num == 1) {
                TextView text1 = dialogView.findViewById(R.id.text1);
                if (text1 != null) {
                    text1.setText(text);
                    text1.setVisibility(View.VISIBLE);
                }
            } else {
                TextView text2 = dialogView.findViewById(R.id.text2);
                if (text2 != null) {
                    text2.setText(text);
                    text2.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    //텍스트 및 SpannableString 설정
    public void setText(SpannableString span, int num) {
        if (dialog != null) {
            if (num == 1) {
                TextView text1 = dialogView.findViewById(R.id.text1);
                if (text1 != null) {
                    text1.setText(span);
                    text1.setVisibility(View.VISIBLE);
                }
            } else {
                TextView text2 = dialogView.findViewById(R.id.text2);
                if (text2 != null) {
                    text2.setText(span);
                    text2.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    //텍스트 가리기
    public void goneText(int num) {
        if (num == 1) {
            TextView text1 = dialogView.findViewById(R.id.text1);
            text1.setVisibility(View.GONE);
        } else {
            TextView text2 = dialogView.findViewById(R.id.text2);
            text2.setVisibility(View.GONE);
        }
    }

    // 다이얼로그 표시
    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

}
