import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class FarmManager {
    // List to store Plot objects.
    private ArrayList<Plot> plotList;

    // Constructor initializes the plot list.
    public FarmManager() {
        plotList = new ArrayList<>();
    }

    //Adds an already-created Plot (plot object) to the FarmManager. This method is used to manually add in already created plots.

    public void addPlot(Plot plot) {
        plotList.add(plot);
    }


    //Creates a new Plot based on the crop type and plantedDay provided.
    //If the Crop's type (via getCropType()) is "aquatic" (ignoring case),
    // an AquaticPlot is created; otherwise, a LandPlot is created.
    public void createPlot(Crop crop, int plantedDay) {
        Plot newPlot;
        if (crop.getCropType().equalsIgnoreCase("aquatic")) {
            newPlot = new AquaticPlot(crop, plantedDay);
        } else {
            newPlot = new LandPlot(crop, plantedDay);
        }
        addPlot(newPlot);
        System.out.println("Created plot with ID: " + newPlot.getId());
    }


    //Deletes a Plot from the farm based on its unique id.
    //returns true if the Plot was found and removed; false otherwise.
    public boolean deletePlot(int plotId) {
        Iterator<Plot> iterator = plotList.iterator();
        while (iterator.hasNext()) {
            Plot p = iterator.next();
            if (p.getId() == plotId) {
                iterator.remove();
                System.out.println("Deleted plot with ID: " + plotId);
                return true;
            }
        }
        System.out.println("Plot with ID " + plotId + " not found.");
        return false;
    }

    /**
     * Finds and returns the conditions of a particular Plot as a Map.
     * The returned map includes:
     * - "cropType": The crop's name (from the Crop object).
     * - "plotType": Determined based on the Crop's type.
     * - Sensor readings for keys such as "Moisture", "Humidity", "Light", "Temperature".
     *
     * @param plotId The unique id of the Plot.
     * @return A Map with the condition keys and values, or null if the Plot isn't found.
     */
    public Map<String, Object> findPlotConditions(int plotId) {
        for (Plot p : plotList) {
            if (p.getId() == plotId) {
                Map<String, Object> conditions = new HashMap<>();
                // Get crop type from the Crop object.
                String cropType = p.getCrop().CropType();
                conditions.put("cropType", cropType);
                // Determine plotType based on the crop's type.
                if (cropType.equalsIgnoreCase("aquatic")) {
                    conditions.put("plotType", "Aquatic");
                } else {
                    conditions.put("plotType", "Land");
                }
                // Add sensor readings.
                conditions.putAll(p.getCurrentConditions());
                return conditions;
            }
        }
        return null;
    }
}

