package proxy.service;

public interface AuthService {
    void checkPermission(String userId, String permission);
}
