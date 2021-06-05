package com.example.calculator.chenzhaoxuan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.calculator.R;

public class MoreOperationDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private View view;

    @Override
    public void onClick(View v) {
            selectlistener.select(v.getTag().toString());
            dismiss();
    }

    public interface selectListener {
        void select(String string);
    }

    private selectListener selectlistener;

    public MoreOperationDialog(@NonNull Context context,selectListener selectListener) {
        super(context);
        this.context = context;
        this.selectlistener = selectListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.dialog_general, null);
        setContentView(view);
        initEvent();
    }

    private void initEvent() {
        view.findViewById(R.id.tv_ln).setOnClickListener(this);
        view.findViewById(R.id.tv_sin).setOnClickListener(this);
        view.findViewById(R.id.tv_cos).setOnClickListener(this);
        view.findViewById(R.id.tv_tan).setOnClickListener(this);
        view.findViewById(R.id.tv_sinh).setOnClickListener(this);
        view.findViewById(R.id.tv_cosh).setOnClickListener(this);
        view.findViewById(R.id.tv_tanh).setOnClickListener(this);
        view.findViewById(R.id.tv_asinh).setOnClickListener(this);
        view.findViewById(R.id.tv_acosh).setOnClickListener(this);
        view.findViewById(R.id.tv_atanh).setOnClickListener(this);
        view.findViewById(R.id.tv_asin).setOnClickListener(this);
        view.findViewById(R.id.tv_acos).setOnClickListener(this);
        view.findViewById(R.id.tv_atan).setOnClickListener(this);
        view.findViewById(R.id.tv_pai).setOnClickListener(this);
        view.findViewById(R.id.tv_fai).setOnClickListener(this);
        view.findViewById(R.id.tv_e).setOnClickListener(this);
        view.findViewById(R.id.tv_log).setOnClickListener(this);
        view.findViewById(R.id.tv_sqrt).setOnClickListener(this);
        view.findViewById(R.id.tv_abs).setOnClickListener(this);
    }
}

