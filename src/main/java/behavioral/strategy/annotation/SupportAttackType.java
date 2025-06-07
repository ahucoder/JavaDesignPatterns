package behavioral.strategy.annotation;

import behavioral.strategy.enums.AttackType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportAttackType {
    AttackType value();
}
