import java.util.ArrayList;

class AquaticPlot extends Plot {
    private ArrayList<Sensor> sensors;

    public AquaticPlot(Crop crop, int plantedDay) {
        super(crop, plantedDay);
    }

    @Override
    public void initializeSensors() {
        sensors = getSensors();
        sensors.add(new LightSensor());
        sensors.add(new TemperatureSensor());
        sensors.add(new HumiditySensor());
    }
}
