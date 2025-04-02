import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Crop {
    private String name;
    public int seedlingWeeks;
    public int matureWeeks;
    private HashMap <ConditionType, int[]> conditions;
    private List<ConditionType> conditionType;

    public Crop(String name, int seedlingWeeks, int matureWeeks) {
        this.name = name;
        this.seedlingWeeks = seedlingWeeks;
        this.matureWeeks = matureWeeks;
        this.conditions = new HashMap<>();
        this.conditionType = new ArrayList<>();
    }

    // prints conditions neatly, can be changed later on
    public void printConditions() {
        for (ConditionType condition: conditions.keySet()) {
            System.out.println(condition + ": ");
            System.out.print("Min: " + conditions.get(condition)[0]);
            System.out.println(", Max: " + conditions.get(condition)[1]);
        }
    }

    public int getSeedlingWeeks() {
        return seedlingWeeks;
    }

    public void setSeedlingWeeks(int seedlingWeeks) {
        this.seedlingWeeks = seedlingWeeks;
    }

    public int getMatureWeeks() {
        return matureWeeks;
    }

    public void setMatureWeeks(int matureWeeks) {
        this.matureWeeks = matureWeeks;
    }

    // below are get set for variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<ConditionType, int[]> getConditions() {
        return conditions;
    }

    public void setConditions(HashMap<ConditionType, int[]> conditions) {
        this.conditions = conditions;
    }

    public List<ConditionType> getConditionType() {
        return conditionType;
    }

    public void setConditionType(List<ConditionType> conditionType) {
        this.conditionType = conditionType;
    }
}

