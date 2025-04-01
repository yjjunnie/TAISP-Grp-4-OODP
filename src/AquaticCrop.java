import java.util.HashMap;

public class AquaticCrop extends Crop{

    public AquaticCrop(String name, int seedlingTime, int matureTime, int[] temperature, int[] humidity, int[] lightExposure) {
        super(name, seedlingTime, matureTime);

        // get the hash map
        HashMap<String, int[]> conditions = this.getConditions();

        // set conditions
        String[] aquaticConditions = new String[]{"Temperature", "Humidity", "Light Exposure"};
        this.setConditionName(aquaticConditions);

        // fill hashmap
        for (String condition : this.getConditionName()) {
            switch (condition) {
                case "Temperature":
                    conditions.put(condition, temperature);
                    break;
                case "Humidity":
                    conditions.put(condition, humidity);
                    break;
                case "Light Exposure":
                    conditions.put(condition, lightExposure);
                    break;
                default:
                    break;
            }
        }
    }

}
