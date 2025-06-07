package chain.service.impl;

import chain.dto.ApprovalRequestDto;
import chain.service.ApprovalRuleService;

public class DefaultRuleService implements ApprovalRuleService {
    @Override
    public boolean evaluate(ApprovalRequestDto request) {
        return true;
    }

    @Override
    public String getName() {
        return "True";
    }
}
