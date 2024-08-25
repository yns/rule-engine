package com.yns.ruleengine.engine.base;

import java.util.List;

public interface IRuleEngine<T> {
    void start(List<T> dataList);
}
