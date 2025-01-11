package com.example.freecode.fragment.kingnoob;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.freecode.KingNoobActivity;
import com.example.freecode.R;
import com.example.freecode.methodClass.LastPageInfo;

public class KingNoobFragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kingnoob_fragment3, container, false);

        Context context = requireContext();

        //클리어 챕터인지 아닌지 확인
        LastPageInfo lastPage = new LastPageInfo();
        boolean checkLast = lastPage.getLastPage(context, "King") <= 2;

        KingNoobActivity activity = (KingNoobActivity) requireActivity();
        Button beforeButton = view.findViewById(R.id.before_button);
        //이전 버튼 리스너
        beforeButton.setOnClickListener(v -> activity.moveToBeforePage());

        return view;
    }
}
