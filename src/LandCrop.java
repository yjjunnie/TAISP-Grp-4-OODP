import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LandCrop extends Crop {

    public LandCrop(String name, int seedlingWeeks, int matureWeeks,  int[] temperature, int[] humidity, int[] lightExposure, int[] soilMoisture) {
        super(name, seedlingWeeks, matureWeeks);

        // get the hash map
        HashMap<ConditionType, int[]> conditions = this.getConditions();

        // set conditions
        List<ConditionType> landConditions = getConditionType();
        ConditionType[] landConditionNames = {ConditionType.TEMPERATURE, ConditionType.HUMIDITY, ConditionType.LIGHT, ConditionType.MOISTURE} ;
        landConditions.addAll(List.of(landConditionNames));


        // fill hashmap
        for (ConditionType condition : landConditions) {
            switch (condition) {
                case TEMPERATURE:
                    conditions.put(condition, temperature);
                    break;
                case HUMIDITY:
                    conditions.put(condition, humidity);
                    break;
                case LIGHT:
                    conditions.put(condition, lightExposure);
                    break;
                case MOISTURE:
                    conditions.put(condition, soilMoisture);
                    break;
                default:
                    break;
            }
        }
    }
}
