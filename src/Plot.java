import java.util.*;

abstract class Plot {
    // Static variables
    private static int numOfPlots = 0;

    // Instance variables
    private int id;
    private Crop crop;
    private int estSeedlingWeek;
    private int estMatureWeek;
    private int plantedWeek;
    private ArrayList<Sensor> sensors;
    private int punishment;


    public Plot(Crop crop, int plantedWeek) {
        this.id = Plot.numOfPlots;
        Plot.numOfPlots++;
        this.crop = crop;
        this.plantedWeek = plantedWeek;
        this.estSeedlingWeek = plantedWeek + crop.getSeedlingWeeks();
        this.estMatureWeek = plantedWeek + crop.getMatureWeeks();
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
    public String harvestCrop(int currentWeek) {
        if (currentWeek >= getEstMatureWeek()) {
            return crop.getName();
        }
    }

    public String getGrowthStage(int currentWeek) {
        if (currentWeek < getEstSeedlingWeek()) {
            return "Seed";
        } else if (currentWeek < getEstMatureWeek()) {
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

    public int getEstSeedlingWeek() {
        return estSeedlingWeek;
    }

    public int getEstMatureWeek() {
        return estMatureWeek;
    }

    public int getPlantedWeek() {
        return plantedWeek;
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