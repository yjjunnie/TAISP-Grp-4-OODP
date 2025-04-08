package simulators;

import common.ConditionType;
import common.Time;
import java.util.Random;

public abstract class Simulator {
    protected int WeekLastCleared = 0;
    protected Random random = new Random();
    protected int WeekLastRandomised = 0;
    private int condition;

    public int getWeekLastRandomised() {
        return WeekLastRandomised;
    }

    public void setWeekLastRandomised(int week) {
        WeekLastRandomised = week;
    }

    public static int[] change_range(ConditionType conditionType) { 
        int[] change_range = switch (conditionType) {
            case HUMIDITY -> new int[]{-20, 5, 20}; // {min change, norm change, max change}
            case LIGHT -> new int[]{-1000, 100, 1000};
            case MOISTURE -> new int[]{-30, 10, 30};
            case TEMPERATURE -> new int[]{-20, 5, 20};
            default -> new int[]{0, 0, 0};
        };
        return change_range;
    }

    public abstract int randomizer();

    public int getCondition() {
        return condition;
    };

    public void setCondition(int value) {
        this.condition = value;
        this.WeekLastRandomised = (new Time()).getCurrentWeek();
    };
} 