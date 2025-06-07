package behavioral.chain.service;

import behavioral.chain.dto.ApprovalRequestDto;

public interface ApprovalRuleService {
    boolean evaluate(ApprovalRequestDto request);

    String getName();
}
