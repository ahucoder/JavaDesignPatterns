package chain.processor;

import chain.handler.ApprovalHandler;
import chain.service.impl.LeaveDaysRuleService;

import java.util.List;

public class DepartmentManager extends ApprovalHandler {
    public DepartmentManager() {
        super(List.of(new LeaveDaysRuleService(3)));
    }
}
