import java.util.HashMap;

public class Crop {
    private String name;
    public int seedlingTime;
    public int matureTime;
    private HashMap <String, int[]> conditions;
    private String[] conditionName;

    public Crop(String name, int seedlingTime, int matureTime) {
        this.name = name;
        this.seedlingTime = seedlingTime;
        this.matureTime = matureTime;
        this.conditions = new HashMap<>();
        this.conditionName = new String[]{};
    }

    // prints conditions neatly, can be changed later on
    public void printConditions() {
        for (String condition: conditions.keySet()) {
            System.out.println(condition + ": ");
            System.out.print("Min: " + conditions.get(condition)[0]);
            System.out.println(", Max: " + conditions.get(condition)[1]);
        }
    }

    public int getSeedlingTime() {
        return seedlingTime;
    }

    public void setSeedlingTime(int seedlingTime) {
        this.seedlingTime = seedlingTime;
    }

    public int getMatureTime() {
        return matureTime;
    }

    public void setMatureTime(int matureTime) {
        this.matureTime = matureTime;
    }

    // below are get set for variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, int[]> getConditions() {
        return conditions;
    }

    public void setConditions(HashMap<String, int[]> conditions) {
        this.conditions = conditions;
    }

    public String[] getConditionName() {
        return conditionName;
    }

    public void setConditionName(String[] conditionName) {
        this.conditionName = conditionName;
    }
}

