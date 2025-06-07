package behavioral.chain.processor;

import behavioral.chain.handler.ApprovalHandler;
import behavioral.chain.service.impl.LeaveDaysRuleService;

import java.util.List;

public class DepartmentManager extends ApprovalHandler {
    public DepartmentManager() {
        super(List.of(new LeaveDaysRuleService(3)));
    }
}
