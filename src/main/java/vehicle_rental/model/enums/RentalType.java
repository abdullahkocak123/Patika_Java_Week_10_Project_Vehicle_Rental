package vehicle_rental.model.enums;

public enum RentalType {

    HOURLY ("saatlik"),
    DAILY ("günlük"),
    WEEKLY ("haftalık"),
    MONTHLY ("aylık");

    private final String displayName;

    RentalType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
