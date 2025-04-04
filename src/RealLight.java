import java.util.Random;

class RealLight implements Simulator {
    int day_diff = (new Time()).getCurrentWeek() - dayLastCleared; //use for randomiser ??
    private LightSensor lightSensor;
    private Random random = new Random();

    // Bidirectional association
    public void setLightSensor(LightSensor lightSensor) {
        this.lightSensor = lightSensor;
    }
    public LightSensor getLightSensor() {
        return lightSensor;
    }

    // Constructor 
    public RealLight(LightSensor lightSensor) {

    }

    @Override
    public int getCondition() {
        return lightSensor.getCondition();
    }

    @Override
    public void setCondition(String condition) {
        double probability = Simulator.probability(lightSensor.getConditionType());
        lightSensor.condition = randomizer(probability);
    }

    @Override
    public int randomizer(double prob) {
        int min = 0, max = 100;
        int randomizedValue = getRandomInt(min, max) * day_diff;
        return (int) Math.round(randomizedValue * prob);
    }
}