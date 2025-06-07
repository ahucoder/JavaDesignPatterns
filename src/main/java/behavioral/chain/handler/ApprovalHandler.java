package behavioral.chain.handler;

import behavioral.chain.dto.ApprovalRequestDto;
import behavioral.chain.service.ApprovalRuleService;

import java.util.List;

public abstract class ApprovalHandler {
    protected ApprovalHandler next;
    private final List<ApprovalRuleService> rules;

    public ApprovalHandler(List<ApprovalRuleService> rules) {
        this.rules = rules;
    }

    //pipeline
    public ApprovalHandler linkWith(ApprovalHandler next) {
        this.next = next;
        return next;
    }

    public void handle(ApprovalRequestDto request) {
        if (rules.stream().anyMatch(rule -> rule.evaluate(request))) {
            System.out.printf("[%s]Approve the %s application for %s: days=%d, amount=%.2f%n",
                    getClass().getSimpleName(), request.getApplicant(), request.getType(),
                    request.getDays(), request.getAmount());
        } else {
            System.out.printf("[%s]Not in compliance with approval rules, transferred to the next level: %s%n",
                    getClass().getSimpleName(), next != null ? next.getClass().getSimpleName() : "Null");
            passToNext(request);
        }
    }

    protected void passToNext(ApprovalRequestDto request) {
        if (next != null) {
            next.handle(request);
        } else {
            System.out.println("[SYSTEM] No one can process this request: " + request.getType());
        }
    }
}
