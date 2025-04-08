abstract class Sensor {
    protected ConditionType conditionType;

    public ConditionType getConditionType() {
        return conditionType;
    }

    public abstract int getCondition();

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public abstract void setCondition(int condition);
       
}