import java.util.HashMap;

public class LandCrop extends Crop {

    public LandCrop(String name, int seedlingTime, int matureTime,  int[] temperature, int[] humidity, int[] lightExposure, int[] soilMoisture) {
        super(name, seedlingTime, matureTime);

        // get the hash map
        HashMap<String, int[]> conditions = this.getConditions();

        // set conditions
        String[] landConditions = new String[]{"Temperature", "Humidity", "Light Exposure", "Soil Moisture"};
        this.setConditionName(landConditions);

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
                case "Soil Moisture":
                    conditions.put(condition, soilMoisture);
                    break;
                default:
                    break;
            }
        }
    }
}
