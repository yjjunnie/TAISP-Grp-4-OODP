package sensors;

import common.ConditionType;
import common.Time;
import simulators.RealMoisture;

public class MoistureSensor extends Sensor {
    private RealMoisture realMoisture;

    // Constructor 
    public MoistureSensor(Integer range[]) {
        setConditionType(ConditionType.MOISTURE); // Abstract Sensor
        realMoisture = new RealMoisture(range);
    }

    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - realMoisture.getWeekLastRandomised() == 0) //checking conditions on day of creation 
            return realMoisture.getCondition();
        else {
            realMoisture.randomizeCondition();
            return realMoisture.getCondition();
        }
    }

    public void setCondition(int value) { // Used to set condition, when farmer updates value
        realMoisture.setCustomCondition(value);
    }
}