package com.example.calculator.chenzhaoxuan;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscountFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText et_dicount;
    private EditText et_price;
    private EditText et_addtax;
    private TextView tv_final;
    private TextView tv_discount;
    private int state_code = 1;

    public DiscountFragment() {
        // Required empty public constructor
    }

    public static DiscountFragment newInstance(String param1, String param2) {
        return new DiscountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discount, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        et_addtax = view.findViewById(R.id.et_addtax);
        et_price = view.findViewById(R.id.et_price);
        et_dicount = view.findViewById(R.id.et_discount);
        tv_discount = view.findViewById(R.id.tv_discount);
        tv_final = view.findViewById(R.id.tv_final);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        et_addtax.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                state_code = 1;
                et_addtax.setSelected(true);
                et_addtax.setFocusable(true);
                return true;
            }
        });
        et_price.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                state_code = 2;
                et_price.setSelected(true);
                et_price.setFocusable(true);
                return true;
            }
        });
        et_dicount.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                state_code = 3;
                et_dicount.setSelected(true);
                et_dicount.setFocusable(true);
                return true;
            }
        });
        view.findViewById(R.id.tv_1).setOnClickListener(this);
        view.findViewById(R.id.tv_2).setOnClickListener(this);
        view.findViewById(R.id.tv_3).setOnClickListener(this);
        view.findViewById(R.id.tv_4).setOnClickListener(this);
        view.findViewById(R.id.tv_5).setOnClickListener(this);
        view.findViewById(R.id.tv_6).setOnClickListener(this);
        view.findViewById(R.id.tv_7).setOnClickListener(this);
        view.findViewById(R.id.tv_8).setOnClickListener(this);
        view.findViewById(R.id.tv_9).setOnClickListener(this);
        view.findViewById(R.id.tv_0).setOnClickListener(this);
        view.findViewById(R.id.tv_00).setOnClickListener(this);
        view.findViewById(R.id.tv_spot).setOnClickListener(this);
        view.findViewById(R.id.tv_clear).setOnClickListener(this);
        view.findViewById(R.id.tv_delete).setOnClickListener(this);
        view.findViewById(R.id.tv_up).setOnClickListener(this);
        view.findViewById(R.id.tv_down).setOnClickListener(this);
    }

    public String calSaved(double f_add, double f_ori, double f_dis) {
        return String.valueOf(f_ori * f_dis / 100 * (1 + (f_add / 100)));
    }
    public String calPrice(double f_add, double f_ori, double f_dis) {
        return String.valueOf(f_ori * (1 - (f_dis / 100)) * (1 + (f_add / 100)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
            case R.id.tv_2:
            case R.id.tv_4:
            case R.id.tv_3:
            case R.id.tv_5:
            case R.id.tv_6:
            case R.id.tv_7:
            case R.id.tv_8:
            case R.id.tv_9:
            case R.id.tv_0:
            case R.id.tv_00:
            case R.id.tv_spot:
                if (state_code == 1) {
                    et_addtax.setText(add2(et_addtax.getText().toString(), v.getTag().toString()));
                    et_addtax.setSelection(et_addtax.length());
                } else if (state_code == 2) {
                    et_price.setText(add1(et_price.getText().toString(), v.getTag().toString()));
                    et_price.setSelection(et_price.length());
                } else if (state_code == 3) {
                    et_dicount.setText(add2(et_dicount.getText().toString(), v.getTag().toString()));
                    et_dicount.setSelection(et_dicount.length());
                }
                break;
            case R.id.tv_delete:
                if (state_code == 1) {
                    et_addtax.setText(delete2(et_addtax.getText().toString()));
                    et_addtax.setSelection(et_addtax.length());
                } else if (state_code == 2) {
                    et_price.setText(delete1(et_price.getText().toString()));
                    et_price.setSelection(et_price.length());
                } else if (state_code == 3) {
                    et_dicount.setText(delete2(et_dicount.getText().toString()));
                    et_dicount.setSelection(et_dicount.length());
                }
                break;
        }
        if (!TextUtils.isEmpty(et_addtax.getText().toString()) && !TextUtils.isEmpty(et_price.getText().toString()) && !TextUtils.isEmpty(et_dicount.getText().toString())) {
            String s1 = et_addtax.getText().toString().substring(0, et_addtax.getText().length() - 1), s2 = et_price.getText().toString(),
                    s3 = et_dicount.getText().toString().substring(0, et_dicount.getText().length() - 1);
            tv_discount.setText(calSaved(Double.parseDouble(s1),
                    Double.parseDouble(s2), Double.parseDouble(s3)));
            tv_final.setText(calPrice(Double.parseDouble(s1),
                    Double.parseDouble(s2), Double.parseDouble(s3)));
        }
    }

    private String delete2(String string) {
        String temp;
        if (TextUtils.isEmpty(string)) {
            temp = null;
        } else {
            if (string.length() == 2) {
                temp = null;
            } else {
                temp = string.substring(0, string.length() - 2) + "%";
            }
        }
        return temp;
    }

    private String delete1(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        } else {
            return string.substring(0, string.length() - 1);
        }
    }

    private String add2(String string, String tag) {
        String temp;
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.equals(tag, ".")) {
                temp = "0.%";
            } else {
                temp = tag + "%";
            }
        } else {
            if (TextUtils.equals(tag, ".")) {
                if (string.contains(".")) {
                    temp = string;
                } else {
                    temp = string.replace("%", "." + "%");
                }
            } else {
                temp = string.replace("%", tag + "%");
            }
        }
        return temp;
    }

    private String add1(String string, String tag) {
        String temp;
        if (TextUtils.equals(tag, ".")) {
            if (TextUtils.isEmpty(string)) {
                temp = "0.";
            } else {
                if (string.contains(".")) {
                    temp = string;
                } else {
                    temp = string + ".";
                }
            }
        } else {
            temp = string + tag;
        }
        return temp;
    }
}


