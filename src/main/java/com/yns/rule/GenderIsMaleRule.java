package com.yns.rule;

import com.yns.model.Gender;
import com.yns.model.Person;
import com.yns.rule.base.IRule;

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
