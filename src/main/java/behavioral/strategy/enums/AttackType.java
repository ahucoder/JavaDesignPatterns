package behavioral.strategy.enums;

import java.util.Arrays;
import java.util.function.IntPredicate;

import static behavioral.strategy.constant.AttackTypeValue.*;

public enum AttackType {
    PhysicalAttack(attackType -> attackType == PHYSIC_ATTACK_VALUE),
    MagicAttack(attackType -> attackType == MAGIC_ATTACK_VALUE),
    PoisonAttack(attackType -> attackType == POISON_ATTACK_VALUE),
    ;

    private final IntPredicate route;

    AttackType(IntPredicate route) {
        this.route = route;
    }

    public static AttackType typeOf(int attackType) {
        return Arrays.stream(values())
                .filter(value -> value.route.test(attackType))
                .findFirst()
                .orElse(null);
    }
}
