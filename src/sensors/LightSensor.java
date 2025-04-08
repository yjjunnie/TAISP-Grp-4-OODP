package sensors;

import common.ConditionType;
import common.Time;
import simulators.RealLight;

public class LightSensor extends Sensor {
    private RealLight realLight;

    // Constructor 
    public LightSensor(Integer range[]) {
        setConditionType(ConditionType.LIGHT); // Abstract Sensor
        realLight = new RealLight(range);
    }

    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - realLight.getWeekLastRandomised() == 0) //checking conditions on day of creation 
            return realLight.getCondition();
        else {
            realLight.randomizeCondition();
            return realLight.getCondition();
        }
    }

    public void setCondition(int value) { // Used to set condition, when farmer updates value
        realLight.setCustomCondition(value);
    }
}