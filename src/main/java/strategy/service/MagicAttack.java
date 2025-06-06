package strategy.service;

import org.springframework.stereotype.Service;
import strategy.annotation.SupportAttackType;
import strategy.enums.AttackType;

@Service
@SupportAttackType(AttackType.MagicAttack)
public class MagicAttack implements AttackService {
    @Override
    public String attack() {
        System.out.println("Magic Attack");
        return "Magic Attack";
    }
}
