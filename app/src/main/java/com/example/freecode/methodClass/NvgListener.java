package com.example.freecode.methodClass;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.freecode.MainActivity;
import com.example.freecode.R;
import com.example.freecode.RunActivity;
import com.example.freecode.SettingActivity;

public class NvgListener {
    public static boolean itemSelected(Context context, int itemId) {
        Intent intent = null;

        if (itemId == R.id.home) {
            intent = new Intent(context, MainActivity.class);
            start(context, intent);
            MainActivity.checkInit = 1;
            Log.d("MainActivity", "checkInit 1");
            return true;
        } else if (itemId == R.id.run) {
            intent = new Intent(context, RunActivity.class);
            start(context, intent);
            return true;
        } else if (itemId == R.id.quiz) {
            return true;
        } else if (itemId == R.id.concept) {
            return true;
        }
        else if (itemId == R.id.comm) {
            return true;
        }
        else if (itemId == R.id.setting) {
            intent = new Intent(context, SettingActivity.class);
            start(context, intent);
            return true;
        }
        return false;
    }

    private static void start(Context context, Intent intent) {
        //화면 전환 animation setting
        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, 0, 0);

        if (intent != null) {
            context.startActivity(intent, options.toBundle());
        }
    }
}
