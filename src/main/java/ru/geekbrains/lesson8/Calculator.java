package ru.geekbrains.lesson8;

public class Calculator {

    static String calculate(String s) {

        double result = 0;
        return crop(String.valueOf(result));
    }

    static String calculateSqrt(String s) {
        return crop(String.valueOf(Math.sqrt(Double.parseDouble(calculate(s)))));
    }

    static String crop(String s) {
        return (s.endsWith(".0")) ? s.substring(0, s.length() - 2) : s;
    }

}
