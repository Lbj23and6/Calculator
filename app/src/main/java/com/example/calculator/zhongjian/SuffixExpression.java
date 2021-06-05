package com.example.calculator.zhongjian;

public class SuffixExpression {
    private SuffixExpression instance;

    public SuffixExpression getInstance() {
        Scanner sc =new Scanner(System.in);
        String str1  = sc.next();
        char str = str1.charAt(0);
        //将输入的符号转换为字符

        int a = sc.nextInt();
        int b = sc.nextInt();

        if(str == '+') {
            System.out.println(a+b);
        }else if(str == '-') {
            System.out.println(a-b);
        }else if(str == '*') {
            System.out.println(a*b);
        }else if(str == '/') {
            System.out.println(a/b);
        }
        sc.close();
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
