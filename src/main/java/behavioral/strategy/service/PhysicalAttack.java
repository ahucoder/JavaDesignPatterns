package behavioral.strategy.service;

import org.springframework.stereotype.Service;
import behavioral.strategy.annotation.SupportAttackType;
import behavioral.strategy.enums.AttackType;

@Service
@SupportAttackType(AttackType.PhysicalAttack)
public class PhysicalAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Physical Attack");
        return "Physical Attack";
    }
}
