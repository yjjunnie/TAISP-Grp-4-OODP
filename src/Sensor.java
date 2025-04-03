abstract class Sensor {
    protected String conditionType;
    protected int condition;

    public String getConditionType() {
        return conditionType;
    }

    public int getCondition() {
        return condition;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}