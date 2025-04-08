package sensors;

import common.ConditionType;
import common.Time;
import simulators.RealTemp;

public class TemperatureSensor extends Sensor {
    private RealTemp realTemp;

    // Constructor 
    public TemperatureSensor(Integer range[]) {
        setConditionType(ConditionType.TEMPERATURE); // Abstract Sensor
        realTemp = new RealTemp(range);
    }

    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - realTemp.getWeekLastRandomised() == 0) //checking conditions on day of creation 
            return realTemp.getCondition();
        else {
            realTemp.randomizeCondition();
            return realTemp.getCondition();
        }
    }

    public void setCondition(int value) { // Used to set condition, when farmer updates value
        realTemp.setCustomCondition(value);
    }

}