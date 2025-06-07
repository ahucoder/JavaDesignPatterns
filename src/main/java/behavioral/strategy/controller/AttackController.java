package behavioral.strategy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import behavioral.strategy.annotation.SupportAttackType;
import behavioral.strategy.enums.AttackType;
import behavioral.strategy.service.AttackService;
import behavioral.strategy.service.DefaultAttack;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class AttackController {
    private Map<AttackType, AttackService> attackServiceMap;

    @Autowired
    private DefaultAttack defaultAttackService;

//    @Autowired
//    private List<AttackService> attackServices;

    @GetMapping("/attack/{type}")
    public String attack(@PathVariable(value = "type") int type) {
        AttackType attackType = AttackType.typeOf(type);
        AttackService attackService = attackServiceMap.getOrDefault(attackType, defaultAttackService);
        return attackService.attack();
    }

    @Autowired
    private void setAttackServiceMap(List<AttackService> attackServiceList) {
        this.attackServiceMap = attackServiceList.stream()
                .filter(attackService -> attackService.getClass().isAnnotationPresent(SupportAttackType.class))
                .collect(Collectors.toMap(AttackService::getAttackTypeByService, Function.identity()));
        if (this.attackServiceMap.size() != AttackType.values().length) {
            throw new IllegalArgumentException("some attack types are not supported");
        }
    }

    //The following code completes the same task as setAttackServiceMap(List)
    /*@PostConstruct
    private void initAttackServiceMap() {
        this.attackServiceMap = attackServices.stream()
                .filter(attackService -> attackService.getClass().isAnnotationPresent(SupportAttackType.class))
                .collect(Collectors.toMap(AttackService::getAttackTypeByService, Function.identity()));
        if (this.attackServiceMap.size() != AttackType.values().length) {
            throw new IllegalArgumentException("some attack types are not supported");
        }
    }*/
}
