package strategy.service;

import org.springframework.stereotype.Service;
import strategy.annotation.SupportAttackType;
import strategy.enums.AttackType;

@Service
@SupportAttackType(AttackType.PhysicalAttack)
public class PhysicalAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Physical Attack");
        return "Physical Attack";
    }
}
