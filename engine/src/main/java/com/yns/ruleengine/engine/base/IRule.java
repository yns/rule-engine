package com.yns.ruleengine.engine.base;

public interface IRule<T> {

    int order();

    boolean execute(T instance);

    default ProcessType processType() {
        return ProcessType.SYNC;
    }
}
