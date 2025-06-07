package behavioral.strategy.service;

import behavioral.strategy.annotation.SupportAttackType;
import behavioral.strategy.enums.AttackType;

public interface AttackService {

    String attack();

    default AttackType getAttackTypeByService() {
        SupportAttackType annotation = this.getClass().getAnnotation(SupportAttackType.class);
        if (annotation != null) {
            return annotation.value();
        }
        throw new IllegalStateException("AttackService implementation must be annotated with @SupportAttackType");
    }
}
