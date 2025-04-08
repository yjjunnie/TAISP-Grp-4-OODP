package simulators;

import common.Time;
import sensors.HumiditySensor;

import java.util.Random;

class RealHumidity extends Simulator { 
    int Week_diff = (new Time()).getCurrentWeek() - WeekLastCleared; //use for randomiser ??
    private HumiditySensor humiditySensor;
    private Random random = new Random();
    private int healthy_min, healthy_max;

    // Constructor 
    public RealHumidity(int[] desiredRange) {
        this.healthy_min = desiredRange[0];
        this.healthy_max = desiredRange[1];
        setCondition((healthy_min + healthy_max) / 2);
    }

    // @Override
    public int randomizer() {
        int min = 10, max = 90;
        int changeRange[] = change_range(ConditionType.HUMIDITY);
        int change;
        int currentCondition = humiditySensor.getCondition();
        int newCondition = currentCondition;
        if (currentCondition >= healthy_min && currentCondition <= healthy_max) {
            change = random.nextInt(2 * changeRange[1] + 1) - changeRange[1]; // choose between -norm change to norm change 
            newCondition = currentCondition + change;
            return newCondition;
        }
        else if (currentCondition < healthy_min) {
            change = random.nextInt(-1 - changeRange[0] + 1) + changeRange[0]; // choose between -1 and min change 
            newCondition = currentCondition + change - Week_diff; // worsen condition based on week_diff 
            if (newCondition == min)
                return newCondition;
            while (newCondition < min) {
                int var = min - currentCondition;
                change = random.nextInt(var); // choose between 0 to var
                newCondition = currentCondition - change;
            }
        }
        else if (currentCondition > healthy_max) { // choose between 1 and max change 
            change = random.nextInt(changeRange[2]) + 1;
            newCondition = currentCondition + change;
            if (newCondition == max) 
                return newCondition;
            while (newCondition > max) {
                int var = max - currentCondition;
                change = random.nextInt(var); //choose between 0 to var
                newCondition = currentCondition + change;
            }
        }
        return newCondition;
    }

}
