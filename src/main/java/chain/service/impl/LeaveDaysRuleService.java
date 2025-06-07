package chain.service.impl;

import chain.dto.ApprovalRequestDto;
import chain.enums.RequestType;
import chain.service.ApprovalRuleService;

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
