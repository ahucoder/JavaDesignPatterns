package behavioral.strategy.service;

import org.springframework.stereotype.Service;
import behavioral.strategy.annotation.SupportAttackType;
import behavioral.strategy.enums.AttackType;

@Service
@SupportAttackType(AttackType.MagicAttack)
public class MagicAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Magic Attack");
        return "Magic Attack";
    }
}
