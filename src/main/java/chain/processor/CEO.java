package chain.processor;

import chain.handler.ApprovalHandler;
import chain.service.impl.DefaultRuleService;

import java.util.List;

public class CEO extends ApprovalHandler {
    public CEO() {
        super(List.of(new DefaultRuleService()));
    }
}