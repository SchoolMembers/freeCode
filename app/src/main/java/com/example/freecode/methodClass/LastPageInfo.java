package com.example.freecode.methodClass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LastPageInfo {

    public void setLastPage(Context context, String chapterName, int page) {
        SharedPreferences pref = context.getSharedPreferences("page", Activity.MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.putInt(chapterName, page);
        editor.apply();
    }

    public int getLastPage(Context context, String chapterName) {
        SharedPreferences pref = context.getSharedPreferences("page", Activity.MODE_PRIVATE);
        final int page = pref.getInt(chapterName, -1);
        return page;
    }

}
