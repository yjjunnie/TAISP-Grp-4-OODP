import java.util.*;

public class TestFarmManager {

    /**
     * Helper method to build a string of criteria from the parameters map.
     */
    public static String buildCriteriaString(Map<String, Object> parameters) {
        if (parameters.isEmpty()) {
            return "no criteria (listing all plots)";
        }
        StringBuilder criteria = new StringBuilder();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            criteria.append(entry.getKey())
                    .append("= ")
                    .append(entry.getValue())
                    .append(", ");
        }
        // Remove trailing comma and space if present.
        if (criteria.length() > 2) {
            criteria.setLength(criteria.length() - 2);
        }
        return criteria.toString();
    }

    public static void main(String[] args) {
        // Create some crops.
        Crop wheat = new Crop("Wheat");
        Crop corn = new Crop("Corn");

        // Create some plots with different parameters.
        Plot plot1 = new Plot(1, "Land", wheat, 25.0, 60.0, 80.0, 30.0);
        Plot plot2 = new Plot(2, "Land", corn, 30.0, 70.0, 90.0, 40.0);
        Plot plot3 = new Plot(3, "Aquatic", wheat, 25.0, 65.0, 85.0, 35.0);

        // Wrap plots in PlotManagers.
        PlotManager pm1 = new PlotManager(plot1);
        PlotManager pm2 = new PlotManager(plot2);
        PlotManager pm3 = new PlotManager(plot3);

        // Create a FarmManager and add the PlotManagers.
        FarmManager fm = new FarmManager();
        fm.addPlotManager(pm1);
        fm.addPlotManager(pm2);
        fm.addPlotManager(pm3);

        // --- Test Case 1 ---
        // Only two parameters: cropType "Wheat" and temperature 25.0.
        Map<String, Object> params1 = new HashMap<>();
        params1.put("cropType", "Wheat");
        params1.put("temperature", 30.0);

        List<Plot> result1 = fm.findPlot(params1);
        String criteria1 = buildCriteriaString(params1);
        System.out.println("Test Case 1: Plots matching criteria: " + criteria1);
        if (result1.isEmpty()) {
            System.out.println("No plot matches");
        } else {
            for (Plot p : result1) {
                System.out.println("Plot ID: " + p.getId());
            }
        }

        // --- Test Case 2 ---
        // Single parameter: plotType "Land".
        Map<String, Object> params2 = new HashMap<>();
        params2.put("plotType", "Land");

        List<Plot> result2 = fm.findPlot(params2);
        String criteria2 = buildCriteriaString(params2);
        System.out.println("\nTest Case 2: Plots matching criteria: " + criteria2);
        if (result2.isEmpty()) {
            System.out.println("No plot matches");
        } else {
            for (Plot p : result2) {
                System.out.println("Plot ID: " + p.getId());
            }
        }

        // --- Test Case 3 ---
        // No parameters provided; should return all plots.
        Map<String, Object> params3 = new HashMap<>();
        List<Plot> result3 = fm.findPlot(params3);
        String criteria3 = buildCriteriaString(params3);
        System.out.println("\nTest Case 3: Plots matching criteria: " + criteria3);
        if (result3.isEmpty()) {
            System.out.println("No plot matches");
        } else {
            for (Plot p : result3) {
                System.out.println("Plot ID: " + p.getId());
            }
        }
    }
}