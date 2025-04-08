package plots;

import crops.Crop;
import sensors.Sensor;

import java.util.ArrayList;

public class LandPlot extends Plot {
    private ArrayList<Sensor> sensors;

    public LandPlot(Crop crop, int plantedDay) {
        super(crop, plantedDay);
    }

    @Override
    public void initializeSensors() {
//        sensors = getSensors();
//        sensors.add(new LightSensor());
//        sensors.add(new TemperatureSensor());
//        sensors.add(new HumiditySensor());
//        sensors.add(new MoistureSensor());
    }
}