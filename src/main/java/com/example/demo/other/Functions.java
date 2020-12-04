package com.example.demo.other;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Functions {


    public int fractional(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be bigger than -1");
        }
        return number < 2 ? 1 :
                number * fractional(number - 1);
    }

    public double mathOperation(double a, double b,
                                BiFunction<Double, Double, Double> callback) {
        return callback.apply(a, b);
    }

    public int fibonacci(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Number must be at least 0");
        }
        return num < 2 ? num :
                fibonacci(num - 1) + fibonacci(num - 2);
    }

}
