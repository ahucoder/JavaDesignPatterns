package proxy.service.impl;

import proxy.service.AuthService;

public class AuthServiceImpl implements AuthService {
    @Override
    public void checkPermission(String userId, String permission) {
        System.out.printf("[validate permission] user[%s] permission[%s]\n", userId, permission);
        //do sth...
    }
}
