import java.util.Random;

class RealHumidity implements Simulator { 
    int day_diff = (new Time()).getCurrentWeek() - dayLastCleared; //use for randomiser ??
    private HumiditySensor humiditySensor;
    private Random random = new Random();

    // Bidirectional association
    public void setHumiditySensor(HumiditySensor humiditySensor) {
        this.humiditySensor = humiditySensor;
    }
    public HumiditySensor getHumiditySensor() {
        return humiditySensor;
    }

    // Constructor 
    public RealHumidity(HumiditySensor humiditySensor) {

    }

    @Override
    public int getCondition() {
        return humiditySensor.getCondition();
    }

    @Override
    public void setCondition(String condition) {
        double probability = Simulator.probability(humiditySensor.getConditionType());
        humiditySensor.condition = randomizer(probability);
    }

    @Override
    public int randomizer(double prob) {
        int min = 0, max = 100;
        int randomizedValue = getRandomInt(min, max) * day_diff;
        return (int) Math.round(randomizedValue * prob);
    }

}