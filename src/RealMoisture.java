import java.util.Random;

class RealMoisture implements Simulator {
    int day_diff = (new Time()).getCurrentWeek() - dayLastCleared; //use for randomiser ??
    private MoistureSensor moistureSensor;
    private Random random = new Random();

    // Bidirectional association
    public void setMoistureSensor(MoistureSensor moistureSensor) {
        this.moistureSensor = moistureSensor;
    }
    public MoistureSensor getMoistureSensor() {
        return moistureSensor;
    }

    // Constructor 
    public RealMoisture(MoistureSensor moistureSensor) {

    }

    @Override
    public int getCondition() {
        return moistureSensor.getCondition();
    }

    @Override
    public void setCondition(String condition) {
        double probability = Simulator.probability(moistureSensor.getConditionType());
        moistureSensor.condition = randomizer(probability);
    }

    @Override
    public int randomizer(double prob) {
        int min = 0, max = 100;
        int randomizedValue = getRandomInt(min, max) * day_diff;
        return (int) Math.round(randomizedValue * prob);
    }
}