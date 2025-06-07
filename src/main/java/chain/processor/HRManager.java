package chain.processor;

import chain.handler.ApprovalHandler;
import chain.service.impl.ExpenseAmountRuleService;
import chain.service.impl.LeaveDaysRuleService;

import java.util.Arrays;

public class HRManager extends ApprovalHandler {
    public HRManager() {
        super(Arrays.asList(
                new LeaveDaysRuleService(7),
                new ExpenseAmountRuleService(1000)
        ));
    }
}