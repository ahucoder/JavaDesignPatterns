package behavioral.chain.dto;

import behavioral.chain.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApprovalRequestDto {
    private final RequestType type;
    private final String applicant;
    private final double amount;
    private final int days;
}
