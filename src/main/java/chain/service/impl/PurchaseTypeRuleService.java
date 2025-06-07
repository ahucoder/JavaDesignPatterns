package chain.service.impl;

import chain.dto.ApprovalRequestDto;
import chain.enums.RequestType;
import chain.service.ApprovalRuleService;

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
