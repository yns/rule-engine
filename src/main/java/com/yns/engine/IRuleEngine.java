package com.yns.engine;

import java.util.List;

public interface IRuleEngine<T> {

    void execute(List<T> dataList);
}
