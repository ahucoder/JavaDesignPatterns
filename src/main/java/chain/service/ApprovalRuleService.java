package chain.service;

import chain.dto.ApprovalRequestDto;

public interface ApprovalRuleService {
    boolean evaluate(ApprovalRequestDto request);

    String getName();
}
