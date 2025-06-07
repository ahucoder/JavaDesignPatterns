package behavioral.chain.service.impl;

import behavioral.chain.dto.ApprovalRequestDto;
import behavioral.chain.enums.RequestType;
import behavioral.chain.service.ApprovalRuleService;

public class PurchaseTypeRuleService implements ApprovalRuleService {
    @Override
    public boolean evaluate(ApprovalRequestDto req) {
        return req.getType() == RequestType.PURCHASE;
    }

    @Override
    public String getName() {
        return "Type==PURCHASE";
    }
}
