import java.util.Random;

public interface Simulator {
    public int dayLastCleared = 0;
    public Random random = new Random();

    /*default void callGetCurrentDay(CLIMenu obj) {
        obj.getCurrentDay();
    }
    public int day_diff = callGetCurrentDay(some cli menu obj??) - dayLastCleared;*/
    
    /* 
    public static <T> double probability(T type) { 
        double prob = 1.0;
        if (type instanceof RealHumidity)  //will update probabiltiies ltr 
            prob = 0.5;
        else if (type instanceof RealLight)
            prob = 0.5;
        else if (type instanceof RealMoisture)
            prob = 0.5;
        else if (type instanceof RealTemp)
            prob = 0.5;
        return prob;
    }
    */

    public static double probability(String conditionType) { // Probabilities for randomizer 
        double probability = switch (conditionType) {
            case "Humidity", "Light Intensity", "Moisture Level", "Temperature" -> 0.5;
            default -> 0.0;
        };
        return probability;
    }

    public default int getRandomInt(int min, int max) { //
        return random.nextInt(max - min + 1) + min; // Random number in [min, max]
    }

    public int randomizer(double prob);

    public int getCondition();

    public void setCondition(String condition);
} 