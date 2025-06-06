package strategy.service;

import strategy.annotation.SupportAttackType;
import strategy.enums.AttackType;

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
