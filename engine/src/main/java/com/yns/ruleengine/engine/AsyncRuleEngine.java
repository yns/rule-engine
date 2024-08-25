package com.yns.ruleengine.engine;


import com.yns.ruleengine.engine.base.IRule;
import com.yns.ruleengine.engine.base.IRuleEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AsyncRuleEngine<T> implements IRuleEngine<T> {

    private ExecutorService executorService;

    private List<IRule> rules;

    private int ruleGroupCount = 2;

    public AsyncRuleEngine(List<IRule> ruleList, int threadCount) {
        this.rules = ruleList;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public AsyncRuleEngine(List<IRule> ruleList, int threadCount, int ruleGroupCount) {
        this.rules = ruleList;
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.ruleGroupCount = ruleGroupCount;
    }

    public AsyncRuleEngine(List<IRule> ruleList, ExecutorService executorService) {
        this.rules = ruleList;
        this.executorService = executorService;
    }

    public AsyncRuleEngine(List<IRule> ruleList, ExecutorService executorService, int ruleGroupCount) {
        this.rules = ruleList;
        this.executorService = executorService;
        this.ruleGroupCount = ruleGroupCount;
    }

    @Override
    public void start(List<T> dataList) {

        // create callable task holder
        List<Callable<String>> callableTasks = new ArrayList<>();

        // group rules
        Map<Integer, List<IRule>> groupedList = groupAsyncRules(this.rules);

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

        try {
            List<Future<String>> futures = this.executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, List<IRule>> groupAsyncRules(List<IRule> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / ruleGroupCount, Collectors.mapping(list::get, Collectors.toList())));
    }
}
