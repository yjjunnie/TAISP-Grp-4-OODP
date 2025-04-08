package plots;

import common.ConditionType;
import crops.Crop;
import sensors.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AquaticPlot extends Plot {
    private ArrayList<Sensor> sensors;

    public AquaticPlot(Crop crop, int plantedWeek) {
        super(crop, plantedWeek);
    }

    @Override
    public void initializeSensors() {
        HashMap<ConditionType, Integer[]> requiredConditions = getCrop().getConditions();
        sensors = getSensors();
        for (ConditionType conditionType : requiredConditions.keySet()) {
            Integer[] range = requiredConditions.get(conditionType);
            switch(conditionType) {
                case LIGHT -> sensors.add(new LightSensor(range));
                case HUMIDITY -> sensors.add(new HumiditySensor(range));
                case TEMPERATURE -> sensors.add(new TemperatureSensor(range));
                case MOISTURE -> sensors.add(new MoistureSensor(range));
            }
        }
    }
}
