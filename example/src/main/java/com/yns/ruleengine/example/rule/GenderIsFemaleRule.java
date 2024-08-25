package com.yns.ruleengine.example.rule;


import com.yns.ruleengine.aspect.LogExecutionTime;
import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.engine.base.ProcessType;
import com.yns.ruleengine.example.model.Gender;
import com.yns.ruleengine.example.model.Person;

public class GenderIsFemaleRule implements IRule<Person> {
    @Override
    public int order() {
        return 2;
    }

    @Override
    @LogExecutionTime
    public boolean execute(Person instance) {
        return instance.gender() == Gender.FEMALE;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.ASYNC;
    }
}
