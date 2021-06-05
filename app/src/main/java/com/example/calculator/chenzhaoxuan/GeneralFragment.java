package com.example.calculator.chenzhaoxuan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.R;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class GeneralFragment extends Fragment implements View.OnClickListener {
    //π值：3.14
    public double pi=4*Math.atan(1);
    private View view;
    private EditText et_input;
    private TextView tv_result;
    private final int MAXLEN = 500;

    public GeneralFragment() {
    }


    public static GeneralFragment newInstance() {
        return new GeneralFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general, container, false);
        initView();
        initEvent();
        view.findViewById(R.id.tv_more).setOnClickListener(this);
        return view;
    }

    private void initEvent() {
        view.findViewById(R.id.tv_0).setOnClickListener(this);
        view.findViewById(R.id.tv_1).setOnClickListener(this);
        view.findViewById(R.id.tv_2).setOnClickListener(this);
        view.findViewById(R.id.tv_3).setOnClickListener(this);
        view.findViewById(R.id.tv_4).setOnClickListener(this);
        view.findViewById(R.id.tv_5).setOnClickListener(this);
        view.findViewById(R.id.tv_6).setOnClickListener(this);
        view.findViewById(R.id.tv_7).setOnClickListener(this);
        view.findViewById(R.id.tv_8).setOnClickListener(this);
        view.findViewById(R.id.tv_9).setOnClickListener(this);
        view.findViewById(R.id.tv_00).setOnClickListener(this);
        view.findViewById(R.id.tv_add).setOnClickListener(this);
        view.findViewById(R.id.tv_division).setOnClickListener(this);
        view.findViewById(R.id.tv_multiply).setOnClickListener(this);
        view.findViewById(R.id.tv_sub).setOnClickListener(this);
        view.findViewById(R.id.tv_percent).setOnClickListener(this);
        view.findViewById(R.id.tv_capital).setOnClickListener(this);
        view.findViewById(R.id.tv_left_brackets).setOnClickListener(this);
        view.findViewById(R.id.tv_right_brackets).setOnClickListener(this);
        view.findViewById(R.id.tv_delete).setOnClickListener(this);
        view.findViewById(R.id.tv_clear).setOnClickListener(this);
    }

    private void initView() {
        et_input = view.findViewById(R.id.et_input);
        tv_result = view.findViewById(R.id.tv_reuslt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_0:
            case R.id.tv_1:
            case R.id.tv_3:
            case R.id.tv_4:
            case R.id.tv_5:
            case R.id.tv_6:
            case R.id.tv_7:
            case R.id.tv_8:
            case R.id.tv_9:
            case R.id.tv_00:
            case R.id.tv_add:
            case R.id.tv_division:
            case R.id.tv_multiply:
            case R.id.tv_sub:
            case R.id.tv_percent:
            case R.id.tv_capital:
            case R.id.tv_left_brackets:
            case R.id.tv_right_brackets:
                et_input.setText(et_input.getText().toString() + v.getTag().toString());
                break;
            case R.id.tv_more:
                new MoreOperationDialog(getContext(), string -> {
                    et_input.setText(et_input.getText().toString() + string);
                }).show();
                break;
            case R.id.tv_delete:
                int len = Tto(et_input.getText().toString());
                int lenght = et_input.getText().toString().length();
                et_input.setText(et_input.getText().toString().substring(0, lenght - len));
                break;
            case R.id.tv_clear:
                et_input.setText(null);
                tv_result.setText(null);
                break;
        }
        et_input.setSelection(et_input.length());
        tv_result.setText(process(et_input.getText().toString()));
    }

    private int Tto(String string) {
        int len = 0;
        if (!TextUtils.isEmpty(string)) {
            if (string.endsWith("asinh") || string.endsWith("acosh") || string.endsWith("atanh")) {
                len = 5;
            } else if (string.endsWith("sin") || string.endsWith("cos") || string.endsWith("tan") || string.endsWith("log") || string.endsWith("abs")) {
                len = 3;
            } else if (string.endsWith("ln")) {
                len = 2;
            } else {
                len = 1;
            }
        }
        Log.i("aaa", "len :" + len);
        return len;
    }

    public String process(String str) {
        int weightPlus = 0, topOp = 0,
                topNum = 0, flag = 1, weightTemp = 0;
        //weightPlus为同一（）下的基本优先级，weightTemp临时记录优先级的变化
        //topOp为weight[]，operator[]的计数器；topNum为number[]的计数器
        //flag为正负数的计数器，1为正数，-1为负数
        int weight[];  //保存operator栈中运算符的优先级，以topOp计数
        double[] number;  //保存数字，以topNum计数
        char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
        String num;//记录数字，str以+-×÷()sctglhijkmn!√^分段，+-×÷()sctglhijkmn!√^字符之间的字符串即为数字
        weight = new int[MAXLEN];
        number = new double[MAXLEN];
        operator = new char[MAXLEN];
        String expression = str;
        StringTokenizer expToken = new StringTokenizer(expression,"+-×÷()sctghijkmnlopq!√^");
        int i = 0;
        try {
            while (i < expression.length()) {
                ch = expression.charAt(i);
                //判断正负数
                if (i == 0) {
                    if (ch == '-')
                        flag = -1;
                } else if(expression.charAt(i-1) == '(' && ch == '-')
                    flag = -1;
                //取得数字，并将正负符号转移给数字
                if (ch <= '9' && ch >= '0'|| ch == '.' || ch == 'E') {
                    num = expToken.nextToken();
                    ch_gai = ch;
                    Log.e("guojs",ch+"--->"+i);
                    //取得整个数字
                    while (i < expression.length() &&
                            (ch_gai <= '9' && ch_gai >= '0'|| ch_gai == '.' || ch_gai == 'E'))
                    {
                        ch_gai = expression.charAt(i++);
                        Log.e("guojs","i的值为："+i);
                    }
                    //将指针退回之前的位置
                    if (i >= expression.length()) i-=1; else {i-=2;}
                    if (num.compareTo(".") == 0) number[topNum++] = 0;
                        //将正负符号转移给数字
                    else {
                        number[topNum++] = Double.parseDouble(num)*flag;
                        flag = 1;
                    }
                }
                //计算运算符的优先级
                if (ch == '(') weightPlus+=4;
                if (ch == ')') weightPlus-=4;
                if (ch == '-' && flag == 1 || ch == '+' || ch == '×'|| ch == '÷' ||
                        ch == 's' ||ch == 'c' || ch == 't' || ch == 'g' || ch == 'l' ||
                        ch == '!' || ch == '√' || ch == '^' || ch == 'h' || ch == 'i' ||
                        ch == 'j' || ch == 'k' || ch == 'm' || ch == 'n' ||
                        ch == 'o' || ch == 'p' || ch == 'q') { //hijkmn 为新增  //opq 二次新增
                    switch (ch) {
                        //+-的优先级最低，为1
                        case '+':
                        case '-':
                            weightTemp = 1 + weightPlus;
                            break;
                        //x÷的优先级稍高，为2
                        case '×':
                        case '÷':
                            weightTemp = 2 + weightPlus;
                            break;
                        //sincos之类优先级为3
                        case 's':
                        case 'c':
                        case 't':
                        case 'g':
                        case 'l':
                            //新增hijkmn
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case '!':
                            weightTemp = 3 + weightPlus;
                            break;
                        //其余优先级为4
                        //case '^':
                        //case '√':
                        default:
                            weightTemp = 4 + weightPlus;
                            break;
                    }
                    //如果当前优先级大于堆栈顶部元素，则直接入栈
                    if (topOp == 0 || weight[topOp-1] < weightTemp) {
                        //否则将堆栈中运算符逐个取出，直到当前堆栈顶部运算符的优先级小于当前运算符
                    }else {
                        while (topOp > 0 && weight[topOp-1] >= weightTemp) {
                            switch (operator[topOp-1]) {
                                //取出数字数组的相应元素进行运算
                                case '+':
                                    number[topNum-2]+=number[topNum-1];
                                    break;
                                case '-':
                                    number[topNum-2]-=number[topNum-1];
                                    break;
                                case '×':
                                    number[topNum-2]*=number[topNum-1];
                                    break;
                                //判断除数为0的情况
                                case '÷':
                                    number[topNum-2]/=number[topNum-1];
                                    break;
                                case '√':
                                    number[topNum-2] =
                                            Math.pow(number[topNum-2], 1/number[topNum-1]);
                                    break;
                                case '^':
                                    number[topNum-2] =
                                            Math.pow(number[topNum-2], number[topNum-1]);
                                    break;
                                //计算时进行角度弧度的转换
                                //sin
                                case 's':
                                    number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //cos
                                case 'c':
                                    number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //tan
                                case 't':
                                    number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //arcsin
                                case 'h':
                                    number[topNum-1] = Math.toDegrees(Math.asin(number[topNum-1]));
                                    topNum++;
                                    break;
                                //arccos
                                case 'i':
                                    number[topNum-1] = Math.toDegrees(Math.acos(number[topNum-1]));
                                    topNum++;
                                    break;
                                //arctan
                                case 'j':
                                    number[topNum-1] = Math.toDegrees(Math.atan(number[topNum-1]));
                                    topNum++;
                                    break;
                                //sinh
                                case 'k':
                                    number[topNum-1] = Math.sinh(number[topNum-1]);
                                    topNum++;
                                    break;
                                //cosh
                                case 'm':
                                    number[topNum-1] = Math.cosh(number[topNum-1]);
                                    topNum++;
                                    break;
                                //tanh
                                case 'n':
                                    number[topNum-1] = Math.tanh(number[topNum-1]);
                                    topNum++;
                                    break;
                                //asinh
                                case 'o':
                                    number[topNum-1] = Math.log(number[topNum-1] + Math.pow(Math.pow(number[topNum-1],2) + 1,0.5d));
                                    topNum++;
                                    break;
                                //acosh
                                case 'p':
    //                                    number[topNum-1] = 1;
                                    number[topNum-1] = Math.log(number[topNum-1] + Math.pow(number[topNum-1] + 1,0.5d)*Math.pow(number[topNum-1] - 1,0.5d));
                                    topNum++;
                                    break;
                                //atanh
                                case 'q':
                                    double operationNumber = number[topNum-1];
                                    number[topNum-1] = Math.log((1 + operationNumber)/(1 - operationNumber))/2;
                                    topNum++;
                                    break;
                                //log
                                case 'g':
                                    number[topNum-1] = Math.log10(number[topNum-1]);
                                    topNum++;
                                    break;
                                //ln
                                case 'l':
                                    number[topNum-1] = Math.log(number[topNum-1]);
                                    topNum++;
                                    break;
                                //阶
                                case '!':
                                    number[topNum-1] = N(number[topNum-1]);
                                    topNum++;
                                    break;
                            }
                            //继续取堆栈的下一个元素进行判断
                            topNum--;
                            topOp--;
                        }
                        //将运算符如堆栈
                    }
                    weight[topOp] = weightTemp;
                    operator[topOp] = ch;
                    topOp++;
                }
                i++;
            }
            //依次取出堆栈的运算符进行运算
            while (topOp>0) {
                //+-x直接将数组的后两位数取出运算
                switch (operator[topOp-1]) {
                    case '+':
                        number[topNum-2]+=number[topNum-1];
                        break;
                    case '-':
                        number[topNum-2]-=number[topNum-1];
                        break;
                    case '×':
                        number[topNum-2]*=number[topNum-1];
                        break;
                    //涉及到除法时要考虑除数不能为零的情况
                    case '÷':
                        number[topNum-2]/=number[topNum-1];
                        break;
                    case '√':
                        number[topNum-2] =
                                Math.pow(number[topNum-2], 1/number[topNum-1]);
                        break;
                    case '^':
                        number[topNum-2] =
                                Math.pow(number[topNum-2], number[topNum-1]);
                        break;
                    //sin
                    case 's':
                        number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //cos
                    case 'c':
                        number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //tan
                    case 't':
                        number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //arcsin
                    case 'h':
                        number[topNum-1] = Math.toDegrees(Math.asin(number[topNum-1]));
                        topNum++;
                        break;
                    //arccos
                    case 'i':
                        number[topNum-1] = Math.toDegrees(Math.acos(number[topNum-1]));
                        topNum++;
                        break;
                    //arctan
                    case 'j':
                        number[topNum-1] = Math.toDegrees(Math.atan(number[topNum-1]));
                        topNum++;
                        break;
                    //sinh
                    case 'k':
                        number[topNum-1] = Math.sinh(number[topNum-1]);
                        topNum++;
                        break;
                    //cosh
                    case 'm':
                        number[topNum-1] = Math.cosh(number[topNum-1]);
                        topNum++;
                        break;
                    //tanh
                    case 'n':
                        number[topNum-1] = Math.tanh(number[topNum-1]);
                        topNum++;
                        break;
                    //asinh
                    case 'o':
    //                        number[topNum-1] = 1;
                        number[topNum-1] = Math.log(number[topNum-1] + Math.pow(Math.pow(number[topNum-1],2) + 1,0.5d));
                        topNum++;
                        break;
                    //acosh
                    case 'p':
    //                        number[topNum-1] = 1;
                        number[topNum-1] = Math.log(number[topNum-1] + Math.pow(number[topNum-1] + 1,0.5d)*Math.pow(number[topNum-1] - 1,0.5d));
                        topNum++;
                        break;
                    //atanh
                    case 'q':
                        double operationNumber = number[topNum-1];
                        number[topNum-1] = Math.log((1 + operationNumber)/(1 - operationNumber))*0.5;
                        topNum++;
                        break;
                    //对数log
                    case 'g':
                        number[topNum-1] = Math.log10(number[topNum-1]);
                        topNum++;
                        break;
                    //自然对数ln
                    case 'l':
                        number[topNum-1] = Math.log(number[topNum-1]);
                        topNum++;
                        break;
                    //阶乘
                    case '!':
                        number[topNum-1] = N(number[topNum-1]);
                        topNum++;
                        break;
                }
                //取堆栈下一个元素计算
                topNum--;
                topOp--;
            }
        } catch (Exception e) {
            return null;
        }
        //输出最终结果
        return String.valueOf(FP(number[0]));
    }
    /*
     * 阶乘算法
     */
    public double N(double n) {
        int i = 0;
        double sum = 1;
        //依次将小于等于n的值相乘
        for(i = 1;i <= n;i++) {
            sum = sum*i;
        }
        return sum;
    }

    /*
     * FP = floating point 控制小数位数，达到精度
     * 否则会出现 0.6-0.2=0.39999999999999997的情况，用FP即可解决，使得数为0.4
     * 本格式精度为15位
     */
    public double FP(double n) {
        //NumberFormat format=NumberFormat.getInstance();  //创建一个格式化类f
        //format.setMaximumFractionDigits(18);    //设置小数位的格式
        DecimalFormat format = new DecimalFormat("0.#############");
        return Double.parseDouble(format.format(n));
    }
}