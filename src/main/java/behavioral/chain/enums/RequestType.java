package behavioral.chain.enums;

public enum RequestType {
    LEAVE("Leave Application"),
    EXPENSE("Expense Reimbursement "),
    PURCHASE("Procurement Request"),
    ;

    private final String typeName;

    RequestType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Get a description of the type
     * @return description of the type
     */
    public String getType() {
        return typeName;
    }

    /**
     * Search for the corresponding enumeration value based on the string (case-insensitive)
     * @param typeStr Type string
     * @return Corresponding enumeration values
     * @throws IllegalArgumentException If no matching enumeration value can be found
     */
    public static RequestType fromString(String typeStr) {
        for (RequestType type : values()) {
            if (type.typeName.equalsIgnoreCase(typeStr)
                    || type.name().equalsIgnoreCase(typeStr)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid request type: " + typeStr);
    }
}
