package com.example.freecode.methodClass;

import android.content.Context;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class CardViews {

    private Context context;

    //챕터 카드
    private final MaterialCardView cardView;

    //진행도 텍스트뷰
    private final TextView textView;

    //lastPage
    public String lastPageKey;
    private int pageCount;

    private static final String[] LAST_PAGE_KEYS = {"King", "Noob1", "Noob2"};

    public CardViews(MaterialCardView cardView, TextView textView, int index, Context context) {
        this.cardView = cardView;
        this.textView = textView;
        this.context = context;
        setLastPageKey(index);
    }

    public void setProgressText() {
        int lastPage = new LastPageInfo().getLastPage(context, lastPageKey) + 1;
        if (lastPage < 0) {
            lastPage = 0;
        }
        int progressPer = (int)(((float) lastPage / pageCount) * 100);
        textView.setText(String.format(Locale.getDefault(), "진행도 %d%%", progressPer));
    }

    public MaterialCardView getCardView() {
        return cardView;
    }

    public TextView getTextView() {
        return textView;
    }

    private void setLastPageKey(int index) {
        switch (index) {
            case 0:
                lastPageKey = LAST_PAGE_KEYS[0];
                pageCount = 7;
                break;
            case 1:
                lastPageKey = LAST_PAGE_KEYS[1];
                pageCount = 10; //임시
                break;
            case 2:
                lastPageKey = LAST_PAGE_KEYS[2];
                pageCount = 10; //임시
                break;
        }
    }

}
