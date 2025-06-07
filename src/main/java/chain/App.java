package chain;

import chain.dto.ApprovalRequestDto;
import chain.enums.RequestType;
import chain.handler.ApprovalHandler;
import chain.processor.CEO;
import chain.processor.DepartmentManager;
import chain.processor.HRManager;

public class App {
    public static void main(String[] args) {
        // Chain：deptManager -> hrManager -> CEO
        ApprovalHandler deptManager = new DepartmentManager();
        ApprovalHandler hrManager = new HRManager();
        ApprovalHandler ceo = new CEO();

        deptManager.linkWith(hrManager).linkWith(ceo);

        // Different scene requests
        ApprovalRequestDto r1 = new ApprovalRequestDto(RequestType.LEAVE, "Alice", 0, 2);
        ApprovalRequestDto r2 = new ApprovalRequestDto(RequestType.EXPENSE, "Bob", 800, 0);
        ApprovalRequestDto r3 = new ApprovalRequestDto(RequestType.PURCHASE, "Charlie", 5000, 0);
        ApprovalRequestDto r4 = new ApprovalRequestDto(RequestType.LEAVE, "David", 0, 10);

        System.out.println("--- Start processing requests ---");
        System.out.printf("--- r1 start [%s]---%n", r1.getType());
        deptManager.handle(r1);
        System.out.printf("--- r1 end [%s]---%n", r1.getType());
        System.out.printf("--- r2 start [%s]---%n", r2.getType());
        deptManager.handle(r2);
        System.out.printf("--- r2 end [%s]---%n", r2.getType());
        System.out.printf("--- r3 start [%s]---%n", r3.getType());
        deptManager.handle(r3);
        System.out.printf("--- r3 end [%s]---%n", r3.getType());
        System.out.printf("--- r4 start [%s]---%n", r4.getType());
        deptManager.handle(r4);
        System.out.printf("--- r4 end [%s]---%n", r4.getType());

        System.out.println("--- Request processing completed ---");
    }
}
