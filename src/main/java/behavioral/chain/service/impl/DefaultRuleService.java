package behavioral.chain.service.impl;

import behavioral.chain.dto.ApprovalRequestDto;
import behavioral.chain.service.ApprovalRuleService;

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
