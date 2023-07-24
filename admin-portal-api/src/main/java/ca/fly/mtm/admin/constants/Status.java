package ca.fly.mtm.admin.constants;

public enum Status {
    ACTIVATE("A"),
    DEACTIVATE("D");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
