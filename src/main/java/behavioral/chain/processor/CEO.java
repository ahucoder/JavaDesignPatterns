package behavioral.chain.processor;

import behavioral.chain.handler.ApprovalHandler;
import behavioral.chain.service.impl.DefaultRuleService;

import java.util.List;

public class CEO extends ApprovalHandler {
    public CEO() {
        super(List.of(new DefaultRuleService()));
    }
}