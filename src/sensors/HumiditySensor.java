package sensors;

import common.Time;
import crops.Crop;
import simulators.RealHumidity;

public class HumiditySensor extends Sensor {
    private RealHumidity realHumidity;

    // Constructor 
    public HumiditySensor(int range[]) {
        realHumidity = new RealHumidity(range);
        realHumidity.setWeekLastRandomised((new Time()).getCurrentWeek());
        conditionType = ConditionType.HUMIDITY; 
    }

    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - realHumidity.getWeekLastRandomised() == 0) //checking conditions on day of creation 
            return realHumidity.getCondition();
        else {
            realHumidity.randomizer()
            return realHumidity.getCondition();
        }
    }

    public void setCondition(int value) { // Used to get condition after humidity sensor is created 
        realHumidity.setCondition(value);
    }
}