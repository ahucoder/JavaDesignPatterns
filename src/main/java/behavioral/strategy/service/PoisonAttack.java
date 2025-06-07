package behavioral.strategy.service;

import org.springframework.stereotype.Service;
import behavioral.strategy.annotation.SupportAttackType;
import behavioral.strategy.enums.AttackType;

@Service
@SupportAttackType(value = AttackType.PoisonAttack)
public class PoisonAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Poison Attack");
        return "Poison Attack";
    }
}
