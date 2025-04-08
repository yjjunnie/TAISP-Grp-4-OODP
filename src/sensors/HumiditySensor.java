package sensors;

import common.Time;
import common.ConditionType;
import simulators.RealHumidity;

public class HumiditySensor extends Sensor {
    private RealHumidity realHumidity;

    // Constructor 
    public HumiditySensor(Integer range[]) {
        setConditionType(ConditionType.HUMIDITY); // Abstract Sensor
        realHumidity = new RealHumidity(range);
    }

    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - realHumidity.getWeekLastRandomised() == 0) //checking conditions on day of creation 
            return realHumidity.getCondition();
        else {
            realHumidity.randomizeCondition();
            return realHumidity.getCondition();
        }
    }

    public void setCondition(int value) { // Used to set condition, when farmer updates value
        realHumidity.setCustomCondition(value);
    }
}