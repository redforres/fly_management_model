package ca.fly.mtm.admin.constants;

public enum SingleNameIndicator {
    TRUE("T"),
    FASLSE("F");

    private String indicator;

    SingleNameIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getIndicator() {
        return indicator;
    }
}
