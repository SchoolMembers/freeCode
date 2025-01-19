package com.example.freecode.methodClass;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface newTypeface;

    public CustomTypefaceSpan(Typeface typeface) {
        super("");
        newTypeface = typeface;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        applyCustomTypeface(textPaint, newTypeface);
    }

    @Override
    public void updateMeasureState(TextPaint textPaint) {
        applyCustomTypeface(textPaint, newTypeface);
    }

    private static void applyCustomTypeface(Paint paint, Typeface typeface) {
        paint.setTypeface(typeface);
    }

}
