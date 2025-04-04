import java.util.Random;

class RealTemp implements Simulator {
    int day_diff = (new Time()).getCurrentWeek() - dayLastCleared; //use for randomiser ??
    private TemperatureSensor temperatureSensor;
    private Random random = new Random();

    // Bidirectional association
    public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }
    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    // Constructor 
    public RealTemp(TemperatureSensor temperatureSensor) {

    }

    @Override
    public int getCondition() {
        return temperatureSensor.getCondition();
    }

    @Override
    public void setCondition(String condition) {
        double probability = Simulator.probability(temperatureSensor.getConditionType());
        temperatureSensor.condition = randomizer(probability);
    }

    @Override
    public int randomizer(double prob) {
        int min = 0, max = 100;
        int randomizedValue = getRandomInt(min, max) * day_diff;
        return (int) Math.round(randomizedValue * prob);
    }


}