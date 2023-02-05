package com.yns;

import com.yns.engine.RuleEngine;
import com.yns.model.Education;
import com.yns.model.Gender;
import com.yns.model.Person;
import com.yns.rule.AgeOlderThan30Rule;
import com.yns.rule.FunctionalRule;
import com.yns.rule.GenderIsFemaleRule;
import com.yns.rule.GenderIsMaleRule;
import com.yns.rule.base.IFunctionalRule;
import com.yns.rule.base.IRule;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<IRule> ruleList = new ArrayList<>();
        ruleList.add(new AgeOlderThan30Rule());
        ruleList.add(new GenderIsFemaleRule());
        ruleList.add(new GenderIsMaleRule());

        List<IFunctionalRule> functionalRules = new ArrayList<>();
        FunctionalRule funcRule = new FunctionalRule(o -> {
            if (o.name().equals("Name9")) {
                return true;
            }
            return false;
        });

       functionalRules.add(funcRule);

        RuleEngine engine = new RuleEngine(ruleList, functionalRules);

        List<Person> people = new ArrayList<>();

        people.add(new Person("Name1","Surname 1", Gender.MALE, 39, Education.PRIMARY_SCHOOL, "Address1"));
        people.add(new Person("Name2","Surname 2", Gender.MALE, 88, Education.BACHELORS_DEGREE, "Address2"));
        people.add(new Person("Name3","Surname 3", Gender.MALE, 43, Education.HIGH_SCHOOL, "Address3"));
        people.add(new Person("Name4","Surname 4", Gender.FEMALE, 28, Education.BACHELORS_DEGREE, "Address4"));
        people.add(new Person("Name5","Surname 5", Gender.MALE, 22, Education.PRIMARY_SCHOOL, "Address5"));
        people.add(new Person("Name6","Surname 6", Gender.FEMALE, 18, Education.BACHELORS_DEGREE, "Address6"));
        people.add(new Person("Name7","Surname 7", Gender.MALE, 65, Education.BACHELORS_DEGREE, "Address7"));
        people.add(new Person("Name8","Surname 8", Gender.FEMALE, 46, Education.PRIMARY_SCHOOL, "Address8"));
        people.add(new Person("Name9","Surname 9", Gender.FEMALE, 55, Education.HIGH_SCHOOL, "Address9"));

        engine.execute(people);
    }
}