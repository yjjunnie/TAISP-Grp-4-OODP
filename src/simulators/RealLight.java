package simulators;

import common.ConditionType;

public class RealLight extends Simulator {
    
    private int healthy_min, healthy_max;

    // Constructor 
    public RealLight(Integer[] desiredRange) {
        this.healthy_min = desiredRange[0];
        this.healthy_max = desiredRange[1];
        setCustomCondition((healthy_min + healthy_max) / 2);
    }

    public void randomizeCondition() {
        int newCondition = randomizer(ConditionType.LIGHT, healthy_min, healthy_max);
        setRandomisedCondition(newCondition);
    }

}