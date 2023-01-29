package com.yns.rule.base;

public interface IRule<T> {

    int order();

    boolean execute(T instance);

}
