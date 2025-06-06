package strategy.service;

import org.springframework.stereotype.Service;
import strategy.annotation.SupportAttackType;
import strategy.enums.AttackType;

@Service
@SupportAttackType(value = AttackType.PoisonAttack)
public class PoisonAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Poison Attack");
        return "Poison Attack";
    }
}
