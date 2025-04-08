package simulators;

import common.ConditionType;

public class RealHumidity extends Simulator {

    private int healthy_min, healthy_max;

    // Constructor 
    public RealHumidity(Integer[] desiredRange) {
        this.healthy_min = desiredRange[0];
        this.healthy_max = desiredRange[1];
        setCustomCondition((healthy_min + healthy_max) / 2);
    }

    public void randomizeCondition() {
        int newCondition = randomizer(ConditionType.HUMIDITY, healthy_min, healthy_max);
        setRandomisedCondition(newCondition);
    }

}
