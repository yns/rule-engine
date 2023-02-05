package com.yns.engine;

import com.yns.model.Person;
import com.yns.rule.FunctionalRule;
import com.yns.rule.base.IFunctionalRule;
import com.yns.rule.base.IRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RuleEngine implements IRuleEngine<Person> {

    private List<IRule> rules;

    private List<IFunctionalRule> functionalRules;

    public RuleEngine(List<IRule> rules, List<IFunctionalRule> functionalRules) {
        this.rules = rules;
        this.functionalRules = functionalRules;
    }

    @Override
    public void execute(List<Person> dataList) {

        Collections.sort(rules, Comparator.comparingInt(IRule::order));

        dataList.forEach(p -> {
            rules.forEach(r -> {
                boolean result = r.execute(p);
                System.out.println("Rule = " + r + " Result = " + result);
            });

            functionalRules.forEach(r -> {
                boolean result = r.execute(p);
                System.out.println("Functional Rule = " + r + " Result = " + result);
            });
        });
    }
}
