package com.aakash.RateLimiter.DemoApplication.Operations;

public interface Operation {

    double calculate(double arg1, double arg2);
    OperationType getSupportedOperationType();
}
