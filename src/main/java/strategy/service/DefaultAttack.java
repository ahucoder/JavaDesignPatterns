package strategy.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultAttack implements AttackService {
    @Override
    public String attack() {
        return "No Attack";
    }
}
