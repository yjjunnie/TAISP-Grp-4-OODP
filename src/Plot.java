import java.util.*;

abstract class Plot {
    // Static variables
    private static int numOfPlots = 0;

    // Instance variables
    private int id;
    private Crop crop;
    private int estSeedlingDay;
    private int estMatureDay;
    private int plantedDay;
    private ArrayList<Sensor> sensors;
    private int punishment;


    public Plot(Crop crop, int plantedDay) {
        this.id = Plot.numOfPlots;
        Plot.numOfPlots++;
        this.crop = crop;
        this.plantedDay = plantedDay;
        this.estSeedlingDay = plantedDay + crop.getSeedlingDays();
        this.estMatureDay = plantedDay + crop.getMatureDays();
        this.punishment = 0;
        this.sensors = new ArrayList<Sensor>();
        initializeSensors();
    }

    public abstract void initializeSensors();

    private HashMap<String, Integer> getCurrentConditions() {

        HashMap<String, Integer> conditions = new HashMap<>();

        for (Sensor sensor : sensors) {
            String conditionType = sensor.getConditionType();
            int condition = sensor.getCondition();
            conditions.put(conditionType, condition);
        }

        return conditions;
    }

    // Replacing clearAlert() with setConditions()
    public void setConditions(String conditionType, int condition) {
        for (Sensor sensor : sensors) {
            if (sensor.getConditionType() == conditionType) {
                sensor.setCondition(condition);
            }
        }
        // Might want to raise alert again
    }

    public boolean raiseAlert() {
        HashMap<String, Integer> currentConditions = getCurrentConditions();
        HashMap<String, Integer[]> requiredConditions = crop.getConditions();

        for (String conditionType : requiredConditions.keySet()) {
            Integer[] range = requiredConditions.get(conditionType);

            if (currentConditions.containsKey(conditionType)) {
                Integer currentValue = currentConditions.get(conditionType);

                if (currentValue < range[0] || currentValue > range[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Depends on whether plots can be reused, may have to change construction functionality 
    public String harvestCrop(int currentDay) {
        if (currentDay >= getEstMatureDay()) {
            return crop.getName();
        }
    }

    public String getGrowthStage(int currentDay) {
        if (currentDay < getEstSeedlingDay()) {
            return "Seed";
        } else if (currentDay < getEstMatureDay()) {
            return "Seedling";
        } else {
            return "Mature - Ready to harvest";
        }
    }

    public static int getNumOfPlots() {
        return numOfPlots;
    }

    public int getId() {
        return id;
    }

    public Crop getCrop() {
        return crop;
    }

    public int getEstSeedlingDay() {
        return estSeedlingDay;
    }

    public int getEstMatureDay() {
        return estMatureDay;
    }

    public int getPlantedDay() {
        return plantedDay;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public int getPunishment() {
        return punishment;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public void setPunishment(int punishment) {
        this.punishment = punishment;
    }
}