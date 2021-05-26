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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    public interface selectListener {
        void select(SpannableStringBuilder string);
    }

    public selectListener selectlistener;

    public MoreOperationDialog(@NonNull Context context, selectListener selectlistener) {
        super(context);
        this.context = context;
        this.selectlistener = selectlistener;
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dialog_general, null);
        setContentView(view);
    }
}

