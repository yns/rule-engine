package com.yns.rule;

import com.yns.model.Person;
import com.yns.rule.base.IFunctionalRule;

import java.util.function.Function;

public class FunctionalRule implements IFunctionalRule<Person> {

    private Function<Person, Boolean> function;

    public FunctionalRule(Function<Person, Boolean> function) {
        this.function = function;
    }

    @Override
    public boolean execute(Person instance) {
       return this.function.apply(instance);
    }
}