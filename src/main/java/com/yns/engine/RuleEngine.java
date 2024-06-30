package com.yns.engine;

import com.yns.model.Person;
import com.yns.rule.base.IRule;
import com.yns.rule.base.ProcessType;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RuleEngine implements IRuleEngine<Person> {

    private List<IRule> rules;

    public RuleEngine(List<IRule> rules) {
        this.rules = rules;
    }

    @Override
    public void start(List<Person> dataList) {

        List<IRule> syncRuleList = getRulesByType(ProcessType.SYNC);

        dataList.forEach(p -> {
            syncRuleList.forEach(r -> {
                boolean result = r.execute(p);
                System.out.println("Rule = " + r + " Result = " + result);
            });
        });

        startAsync(dataList);
    }

    public void startAsync(List<Person> dataList) {
        List<IRule> ruleList = getRulesByType(ProcessType.ASYNC);

        // group rules
        Map<Integer, List<IRule>> groupedList = groupAsyncRules(ruleList);

        // create callable task holder
        List<Callable<String>> callableTasks = new ArrayList<>();

        // create callable tasks
        for (Map.Entry<Integer,  List<IRule>> entry : groupedList.entrySet()) {

            Callable<String> callableTask = () -> {

                dataList.forEach(p -> {
                    entry.getValue().forEach(r -> {
                        boolean result = r.execute(p);
                        System.out.println("Rule = " + r + " Result = " + result);
                    });
                });

                return entry.getKey().toString();
            };

            callableTasks.add(callableTask);
        }

        //execute callable tasks
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            List<Future<String>> futures = executor.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<IRule> getRulesByType(ProcessType processType) {
        return this.rules.stream()
                .filter(p-> p.processType() == processType)
                .sorted(Comparator.comparingInt(IRule::order))
                .toList();
    }

    private Map<Integer, List<IRule>> groupAsyncRules(List<IRule> list) {
        int groupSize = 2;

        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / groupSize, Collectors.mapping(list::get, Collectors.toList())));
    }
}
