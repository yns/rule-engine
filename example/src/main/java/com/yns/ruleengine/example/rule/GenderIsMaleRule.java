package com.yns.ruleengine.example.rule;


import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.example.model.Gender;
import com.yns.ruleengine.example.model.Person;

public class GenderIsMaleRule implements IRule<Person> {

    @Override
    public int order() {
        return 1;
    }

    @Override
    public boolean execute(Person instance) {
        return instance.gender() == Gender.MALE;

    }
}
