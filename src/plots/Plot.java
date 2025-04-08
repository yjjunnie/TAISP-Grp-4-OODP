package plots;

import common.ConditionType;
import common.Time;
import crops.Crop;
import sensors.Sensor;

import java.security.KeyException;
import java.util.*;

public abstract class Plot {
    // Static variables
    private static int numOfPlots = 0;

    // Instance variables
    private int id;
    private Crop crop;
    private int estSeedlingWeek;
    private int estMatureWeek;
    private int plantedWeek;
    private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
    private int punishment = 0;


    public Plot(Crop crop, int plantedWeek) {
        this.id = Plot.numOfPlots;
        Plot.numOfPlots++;
        this.crop = crop;
        this.plantedWeek = plantedWeek;
        this.estSeedlingWeek = plantedWeek + crop.getSeedlingWeeks();
        this.estMatureWeek = plantedWeek + crop.getMatureWeeks();
        initializeSensors();
    }

    public abstract void initializeSensors();

    public HashMap<ConditionType, Integer> getCurrentConditions() {

        HashMap<ConditionType, Integer> currentConditions = new HashMap<>();

        for (Sensor sensor : sensors) {
            ConditionType conditionType = sensor.getConditionType();
            int condition = sensor.getCondition();
            currentConditions.put(conditionType, condition);
        }

        return currentConditions;
    }

    // Replacing clearAlert() with setConditions()
    public void setConditions(ConditionType conditionType, int condition) {
        for (Sensor sensor : sensors) {
            if (conditionType == sensor.getConditionType()) {
                sensor.setCondition(condition);
            }
        }
    }

    public HashMap<ConditionType, Integer> raiseAlert(){
        HashMap<ConditionType, Integer> currentConditions = getCurrentConditions();
        HashMap<ConditionType, Integer[]> requiredConditions = crop.getConditions();
        HashMap<ConditionType, Integer> alerts = new HashMap<>();

        for (ConditionType conditionType : requiredConditions.keySet()) {
            Integer[] range = requiredConditions.get(conditionType);

            if (currentConditions.containsKey(conditionType)) {
                Integer currentValue = currentConditions.get(conditionType);

                if (currentValue < range[0] || currentValue > range[1]) {
                    alerts.put(conditionType, currentValue);
                }
            }
        }
        return alerts;
    }

    public boolean isHarvestable() {
        if ((new Time()).getCurrentWeek() >= getEstMatureWeek()) {
            return true;
        }
        return false;
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