package com.example.freecode.methodClass;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class TextCustom {
    //메서드명에 All붙은 애들은 해당 문자 조각과 동일한 모든 텍스트가 바뀐다는 뜻
    //Count는 count개까지만 적용한다는거

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
    public void backgroundCount(int color, int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
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

    public void sizeCount(float value, int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new RelativeSizeSpan(value), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
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

    public void textColorCount(int color, int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
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
    public void styleCount(int style, int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StyleSpan(style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
        }
    }

    //글자 조각 폰트 다르게 적용
    public void font(Typeface customTypeface) {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = piece.length() + start;
            span.setSpan(new CustomTypefaceSpan(customTypeface), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void fontAll(Typeface customTypeface) {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new CustomTypefaceSpan(customTypeface), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }
    public void fontCountTypeface(Typeface customTypeface, int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new CustomTypefaceSpan(customTypeface), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
        }
    }

    //글자 조각 밑줄 적용
    public void underline() {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void underlineAll() {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }
    public void underlineCount(int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
        }
    }

    //글자 조각 취소선
    public void strike() {
        int start = text.indexOf(piece);
        if (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    public void strikeAll() {
        int start = text.indexOf(piece);
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
        }
    }
    public void strikeCount(int count) {
        int start = text.indexOf(piece);
        int stop = 0;
        while (start >= 0) {
            int end = start + piece.length();
            span.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = text.indexOf(piece, end);
            stop += 1;
            if (stop >= count) {
                return;
            }
        }
    }
}
