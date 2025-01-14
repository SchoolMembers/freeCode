package com.example.freecode.methodClass;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.freecode.R;

public class RunPy {

    private final String userCode;
    private final TextView outputView;
    private final Context context;


    public RunPy(String userCode, TextView outputView, Context context) {
        this.userCode = userCode;
        this.outputView = outputView;
        this.context = context;
    }

    public boolean run() {
        if (!userCode.isEmpty()) {
            try (PyObject result = Python.getInstance()
                    .getModule("main")
                    .callAttr("eval", userCode)) {
                outputView.setText(result.toString());
            } catch (Exception e) {
                outputView.setText(String.format("Error: %s", e.getMessage()));
            }
            return true;
        } else {
            outputView.setText(ContextCompat.getString(context, R.string.emtpy_code));
            return false;
        }
    }

}
