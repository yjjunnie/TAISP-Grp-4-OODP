import java.util.Random;

class RealLight implements Simulator {
    int day_diff = climenu.getCurrentDay() - dayLastCleared; //use for randomiser ??
    private LightSensor lightSensor;
    private Random random = new Random();
<<<<<<< Updated upstream:src/RealLight.java

    // Bidirectional association
    public void setLightSensor(LightSensor lightSensor) {
        this.lightSensor = lightSensor;
    }
    public LightSensor getLightSensor() {
        return lightSensor;
    }
=======
    private int healthy_min, healthy_max;
>>>>>>> Stashed changes:src/simulators/RealLight.java

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