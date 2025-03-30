import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmManager {

    // List holding all PlotManager instances.
    private ArrayList<PlotManager> plotList;

    public FarmManager() {
        plotList = new ArrayList<>();
    }

    /**
     * Finds and returns a list of plots that match the given parameters.
     *
     * Supported parameters (all optional):
     *  - "plotType": String representing the plot type (e.g., "Aquatic", "Land").
     *  - "cropType": String representing the crop name.
     *  - "temperature": Double representing the temperature condition.
     *  - "humidity": Double representing the humidity condition.
     *  - "lightExposure": Double representing the light exposure condition.
     *  - "soilMoisture": Double representing the soil moisture condition.
     *
     * @param parameters Map containing the search parameters.
     * @return List of matching Plot objects.
     */
    public List<Plot> findPlot(Map<String, Object> parameters) {
        List<Plot> matchingPlots = new ArrayList<>();

        for (PlotManager manager : plotList) {
            Plot plot = manager.getPlot(); // Assume PlotManager provides its Plot.
            boolean matches = true;

            // Check Plot Type (e.g., "Aquatic" or "Land")
            if (parameters.containsKey("plotType")) {
                String requiredPlotType = parameters.get("plotType").toString();
                // Assuming Plot has getPlotType() method.
                if (!plot.getPlotType().equalsIgnoreCase(requiredPlotType)) {
                    matches = false;
                }
            }

            // Check Crop Type
            if (matches && parameters.containsKey("cropType")) {
                String requiredCropType = parameters.get("cropType").toString();
                // Assuming Plot has getCrop() that returns a Crop with a getName() method.
                if (plot.getCrop() == null ||
                        !plot.getCrop().getName().equalsIgnoreCase(requiredCropType)) {
                    matches = false;
                }
            }

            // Check Temperature
            if (matches && parameters.containsKey("temperature")) {
                double requiredTemp = Double.parseDouble(parameters.get("temperature").toString());
                // Assuming Plot has getTemperature() returning the current temperature.
                if (plot.getTemperature() != requiredTemp) {
                    matches = false;
                }
            }

            // Check Humidity
            if (matches && parameters.containsKey("humidity")) {
                double requiredHumidity = Double.parseDouble(parameters.get("humidity").toString());
                // Assuming Plot has getHumidity() returning the current humidity.
                if (plot.getHumidity() != requiredHumidity) {
                    matches = false;
                }
            }

            // Check Light Exposure
            if (matches && parameters.containsKey("lightExposure")) {
                double requiredLight = Double.parseDouble(parameters.get("lightExposure").toString());
                // Assuming Plot has getLightExposure() returning the current light exposure.
                if (plot.getLightExposure() != requiredLight) {
                    matches = false;
                }
            }

            // Check Soil Moisture
            if (matches && parameters.containsKey("soilMoisture")) {
                double requiredSoilMoisture = Double.parseDouble(parameters.get("soilMoisture").toString());
                // Assuming Plot has getSoilMoisture() returning the current soil moisture.
                if (plot.getSoilMoisture() != requiredSoilMoisture) {
                    matches = false;
                }
            }

            if (matches) {
                matchingPlots.add(plot);
            }
        }
        return matchingPlots;
    }

    /**
     * Adds a PlotManager to the FarmManager.
     *
     * @param manager PlotManager to be added.
     */
    public void addPlotManager(PlotManager manager) {
        plotList.add(manager);
    }
}
