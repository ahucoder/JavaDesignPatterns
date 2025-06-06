package proxy.service.impl;

import proxy.service.LogService;

import java.util.Arrays;

public class LogServiceImpl implements LogService {
    @Override
    public void log(String action, String... params) {
        System.out.printf("[log record] operation[%s] params%s\n", action, Arrays.toString(params));
    }
}
