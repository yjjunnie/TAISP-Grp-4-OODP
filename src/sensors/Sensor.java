package sensors;

import common.ConditionType;

public abstract class Sensor {
    private ConditionType conditionType;

    public final ConditionType getConditionType() {
        return conditionType;
    }

    public final void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public abstract int getCondition();

    public abstract void setCondition(int condition);
       
}