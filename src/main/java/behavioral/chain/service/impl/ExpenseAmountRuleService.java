package behavioral.chain.service.impl;

import behavioral.chain.dto.ApprovalRequestDto;
import behavioral.chain.enums.RequestType;
import behavioral.chain.service.ApprovalRuleService;

public class ExpenseAmountRuleService implements ApprovalRuleService {
    private final double maxAmount;

    public ExpenseAmountRuleService(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public boolean evaluate(ApprovalRequestDto req) {
        return req.getType() == RequestType.EXPENSE && req.getAmount() <= maxAmount;
    }

    @Override
    public String getName() {
        return "Expense<=" + maxAmount;
    }
}
