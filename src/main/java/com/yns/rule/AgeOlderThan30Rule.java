package com.yns.rule;

import com.yns.model.Person;
import com.yns.rule.base.IRule;

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
