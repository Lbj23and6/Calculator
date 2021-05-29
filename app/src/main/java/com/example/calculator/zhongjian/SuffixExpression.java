package com.example.calculator.zhongjian;

public class SuffixExpression {
    private SuffixExpression instance;

    public SuffixExpression getInstance() {
        if (instance == null) {
            instance = new SuffixExpression();
        }
        return instance;
    }

    private String suffix;

    private String getResult() {
        return suffix;
    }
}
