package com.yns.ruleengine.example.rule;


import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.example.model.Person;

public class AgeOlderThan30Rule implements IRule<Person> {

    @Override
    public int order() {
        return 3;
    }

    @Override
    public boolean execute(Person instance) {
        return instance.age() > 30;
    }
}
