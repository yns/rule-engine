package com.yns.ruleengine.example.rule;


import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.example.model.Person;

import java.util.function.Function;

public class FunctionalRule implements IRule<Person> {

    private Function<Person, Boolean> function;

    @Override
    public int order() {
        return 10;
    }

    public FunctionalRule(Function<Person, Boolean> function) {
        this.function = function;
    }

    @Override
    public boolean execute(Person instance) {
       return this.function.apply(instance);
    }
}