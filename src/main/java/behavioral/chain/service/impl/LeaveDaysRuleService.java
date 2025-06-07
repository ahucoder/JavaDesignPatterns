package behavioral.chain.service.impl;

import behavioral.chain.dto.ApprovalRequestDto;
import behavioral.chain.enums.RequestType;
import behavioral.chain.service.ApprovalRuleService;

public class LeaveDaysRuleService implements ApprovalRuleService {
    private final int maxDays;

    public LeaveDaysRuleService(int maxDays) {
        this.maxDays = maxDays;
    }

    @Override
    public boolean evaluate(ApprovalRequestDto req) {
        return req.getType() == RequestType.LEAVE && req.getDays() <= maxDays;
    }

    @Override
    public String getName() {
        return "LeaveDays<=" + maxDays;
    }
}
