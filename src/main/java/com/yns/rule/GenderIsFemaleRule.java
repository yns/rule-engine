package com.yns.rule;

import com.yns.model.Gender;
import com.yns.model.Person;
import com.yns.rule.base.IRule;
import com.yns.rule.base.ProcessType;

public class GenderIsFemaleRule implements IRule<Person> {
    @Override
    public int order() {
        return 2;
    }

    @Override
    public boolean execute(Person instance) {
        return instance.gender() == Gender.FEMALE;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.ASYNC;
    }
}
