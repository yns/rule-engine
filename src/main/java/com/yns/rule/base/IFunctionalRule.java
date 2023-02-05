package com.yns.rule.base;

import java.util.function.Function;

public interface IFunctionalRule<T> {

    boolean execute(T instance);
}
