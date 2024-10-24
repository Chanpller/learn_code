package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

public class CalculatorLogImpl implements Calculator {

    @Override
    public int add(int i, int j) {

        System.out.println("参数是：" + i + "," + j);
        int result = i + j;
        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int sub(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i - j;

        System.out.println("方法内部 result = " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i * j;

        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int div(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i / j;

        System.out.println("方法内部 result = " + result);

        return result;
    }
}