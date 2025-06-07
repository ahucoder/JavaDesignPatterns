package behavioral.chain.processor;

import behavioral.chain.handler.ApprovalHandler;
import behavioral.chain.service.impl.ExpenseAmountRuleService;
import behavioral.chain.service.impl.LeaveDaysRuleService;

import java.util.Arrays;

public class HRManager extends ApprovalHandler {
    public HRManager() {
        super(Arrays.asList(
                new LeaveDaysRuleService(7),
                new ExpenseAmountRuleService(1000)
        ));
    }
}