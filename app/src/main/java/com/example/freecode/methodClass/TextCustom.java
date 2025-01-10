package com.example.freecode.methodClass;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class TextCustom {
    //메서드명에 All붙은 애들은 해당 문자 조각과 동일한 모든 텍스트가 바뀐다는 뜻

    private TextView view = null;
    private final String text;
    private String piece;
    SpannableString span;

    //생성자 이거 쓰면 setting() 사용 못함
    public TextCustom(String text) {
        span = new SpannableString(text);
        this.text = text;
    }

    //텍스트 뷰와 해당 텍스트 뷰에 들어갈 텍스트
    public TextCustom(TextView view, String text) {
        this.view = view;
        span = new SpannableString(text);
        this.text = text;
    }

    //어떤 텍스트를 변경?
    public void setPiece(String piece) {
        if (text != null) {
            this.piece = piece;
        }
    }

    //세팅 완료 후 마지막 호출
    public void setting() {
        view.setText(span);
    }

    //완성된 SpannableString 객체 얻기 (필요시에만)
    public SpannableString getSpan() {
        return span;
    }

    //백그라운드 색상 (하이라이트. 형광펜.)
    public void background(int color) {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void backgroundAll(int color) {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }

    //글자 조각 크기가 다른 텍스트 사이즈의 value배가 됨
    public void size(float value)  {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new RelativeSizeSpan(value), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void sizeAll(float value) {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new RelativeSizeSpan(value), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }

    //글자 조각 컬러 변경
    public void textColor(int color) {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void textColorAll(int color) {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }

    //글자 조각 스타일 지정
    //스타일 종류 : Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC, Typeface.NORMAL
    public void style(int style) {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StyleSpan(style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void styleAll(int style) {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StyleSpan(style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }

}
