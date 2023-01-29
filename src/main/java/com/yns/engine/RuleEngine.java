package com.yns.engine;

import com.yns.model.Person;
import com.yns.rule.base.IRule;

import java.util.List;

public class RuleEngine implements IRuleEngine<Person> {

    private List<IRule> rules;

    public RuleEngine(List<IRule> rules) {
        this.rules = rules;
    }

    @Override
    public void execute(List<Person> dataList) {
        dataList.forEach(p -> {
            rules.forEach(r -> {
                boolean result = r.execute(p);
                System.out.println("Rule = " + r + " Result = " + result);
            });
        });
    }
}
