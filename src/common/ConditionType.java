package common;

public enum ConditionType {
    HUMIDITY("humidity"),
    TEMPERATURE("temperature"),
    MOISTURE("moisture"),
    LIGHT("light exposure");

    private String conditionName;

    ConditionType(String conditionName) {
        this.conditionName = conditionName;
    }

    @Override
    public String toString() {
        return this.conditionName;
    }
}
