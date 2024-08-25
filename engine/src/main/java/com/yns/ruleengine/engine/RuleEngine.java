package com.yns.ruleengine.engine;


import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.engine.base.IRuleEngine;

import java.util.Comparator;
import java.util.List;

public class RuleEngine<T> implements IRuleEngine<T> {

    private List<IRule> rules;

    public RuleEngine(List<IRule> ruleList) {
        this.rules = ruleList;
    }

    @Override
    public void start(List<T> dataList) {
        List<IRule> syncRuleList = getRulesByOrder();

        dataList.forEach(p -> {
            syncRuleList.forEach(r -> {
                boolean result = r.execute(p);
                System.out.println("Rule = " + r + " Result = " + result);
            });
        });

    }

    private List<IRule> getRulesByOrder() {
        return this.rules.stream()
                .sorted(Comparator.comparingInt(IRule::order))
                .toList();
    }
}
