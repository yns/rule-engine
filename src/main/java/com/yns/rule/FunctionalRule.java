package com.yns.rule;

import com.yns.model.Person;
import com.yns.rule.base.IRule;

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