package crops;

import common.*;
import java.util.HashMap;
import java.util.List;

public class AquaticCrop extends Crop {

    public AquaticCrop(String name, int seedlingWeeks, int matureWeeks, int[] temperature, int[] humidity, int[] lightExposure) {
        super(name, seedlingWeeks, matureWeeks);

        // get the hash map
        HashMap<ConditionType, int[]> conditions = this.getConditions();

        // set conditions
        List<ConditionType> aquaticConditions = getConditionType();
        ConditionType[] aquaticConditionNames = {ConditionType.TEMPERATURE, ConditionType.HUMIDITY, ConditionType.LIGHT} ;
        aquaticConditions.addAll(List.of(aquaticConditionNames));


        // fill hashmap
        for (ConditionType condition : aquaticConditions) {
            switch (condition) {
                case ConditionType.TEMPERATURE:
                    conditions.put(condition, temperature);
                    break;
                case ConditionType.HUMIDITY:
                    conditions.put(condition, humidity);
                    break;
                case ConditionType.LIGHT:
                    conditions.put(condition, lightExposure);
                    break;
                default:
                    break;
            }
        }
    }
}
