package simulators;

import java.util.Random;
import common.ConditionType;
import common.Time;

public abstract class Simulator {
    protected int WeekLastCleared = 0;
    protected Random random = new Random();
    protected int WeekLastRandomised = 0;
    private int condition;
    
    private static final int MAX_MULTIPLIER = 4; // Used to set plateu for fast fowarding long periods.

    public int getWeekLastRandomised() {
        return WeekLastRandomised;
    }

    public void setWeekLastRandomised(int week) {
        WeekLastRandomised = week;
    }

    public int getCondition() {
        return condition;
    };

    public void setRandomisedCondition(int value) {
        this.condition = value;
        this.WeekLastRandomised = (new Time()).getCurrentWeek();
    };

    public void setCustomCondition(int value) {
        this.condition = value;
        this.WeekLastCleared = (new Time()).getCurrentWeek();
        this.WeekLastRandomised = this.WeekLastCleared ;
    };

    public static int getMaxDeviation(ConditionType conditionType) { // Realistically, how much can it deviate in a week?
        int change_range = switch (conditionType) {
            case HUMIDITY -> 20;
            case LIGHT -> 1000;
            case MOISTURE -> 30;
            case TEMPERATURE -> 20;
            default -> 0;
        };
        return change_range;
    }

    public static int[] getLimits(ConditionType conditionType) { // Realistically, how much can it deviate in a week?
        int[] change_range = switch (conditionType) {
            case HUMIDITY -> new int[]{10, 90};
            case LIGHT -> new int[]{100, 20000};
            case MOISTURE -> new int[]{10, 90};
            case TEMPERATURE -> new int[]{0, 100};
            default -> new int[]{0,0};
        };
        return change_range;
    }

    public final int randomizer(ConditionType conditionType, int healthy_max, int healthy_min) {
        int effectiveWeeks = (new Time()).getCurrentWeek() - WeekLastRandomised;
        int multiplier  = Math.min(effectiveWeeks, MAX_MULTIPLIER);
        
        int max_deviation = getMaxDeviation(conditionType);
        int[] limits = getLimits(conditionType);
        int min_limit = limits[0], max_limit = limits[1];

        int delta = 0;
        
        // Check if the current condition is within the healthy range.
        if (condition >= healthy_min && condition <= healthy_max) {
            // Allow both upward and downward changes.
            // 1. Generate a normalized random value in the range [-1, 1].
            double normalized = 2 * random.nextDouble() - 1;
            // 2. Apply a non-linear (cubic) effect to bias towards small changes.
            double nonLinearDeviation = Math.pow(normalized, 3);
            // 3. Scale the delta using the max deviation and week multiplier.
            delta = (int) Math.round(nonLinearDeviation * max_deviation * multiplier);
        } else {
            // Condition is outside healthy range; "lock" the deviation in one direction.
            if (condition < healthy_min) {
                // Only allow further downward change.
                double nonLinearDeviation = Math.pow(random.nextDouble(), 3);
                // The delta is negative.
                delta = -(int) Math.round(nonLinearDeviation * max_deviation * multiplier);
            } else { // condition > healthy_max
                // Only allow further upward change.
                double nonLinearDeviation = Math.pow(random.nextDouble(), 3);
                delta = (int) Math.round(nonLinearDeviation * max_deviation * multiplier);
            }
        }
        
        // Apply the delta.
        int newCondition = condition + delta;
        // Ensure we never cross the overall min/max limits.
        newCondition = Math.max(min_limit, Math.min(newCondition, max_limit));

        return newCondition;
    }
} 