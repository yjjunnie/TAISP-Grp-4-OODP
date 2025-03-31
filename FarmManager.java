import java.util.*;
import java.util.stream.Collectors;

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

    public void displayAllPlotsCrops(int week) {
        if(plotList.isEmpty())
            System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
        else {
            System.out.println("There are currently "+ plotList.size() +" plots.");
            for(Plot plot : plotList) {
                System.out.println("PlotID\tCrop\tGrowth Stage\tHarvest Status\n");
                System.out.print(plot.getId()+"\t"+plot.getCrop().name);

                if(plot.getEstMatureWeek() > week)
                    System.out.print("\t"+plot.getGrowthStage(week)+"\tNot Ready");
                else
                    System.out.print("\tMature\tReady");

            }
        }
    }

    public void displayAllPlotsConditions(int week) {
        System.out.println("Displaying conditions for all plots for week " + week + ":");
        System.out.println("-----------------------------------------------------");

        // Iterate through each plot in the plot list.
        for (Plot p : plotList) {
            // Retrieve the condition map for this plot.
            Map<String, Object> conditions = findPlotConditions(p.getId());
            System.out.println("Plot ID: " + p.getId());
            System.out.println("Conditions: " + conditions);

            // Check if the plot has any alerts.
            if (p.raiseAlert()) {
                System.out.println("ALERT: One or more conditions are out of the acceptable range!");
            }
            System.out.println("-----------------------------------------------------");
        }
    }

    public ArrayList<Integer> displayAllHarvestable(int week) {
        ArrayList<Integer> plotIds = new ArrayList<Integer>();
        List<Plot> filteredPlotsList = plotList.stream().filter(plot -> Objects.equals(plot.getGrowthStage(week), "Mature - Ready to harvest")).toList();

        if(plotList.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
        }else if(filteredPlotsList.isEmpty()){
            System.out.println("Sorry, there are currently no plots that are ready to harvest.");
        }else {

            System.out.println("There are currently "+ filteredPlotsList.size() +" plots ready to harvest, please select one.");
            for(Plot plot : filteredPlotsList) {
                plotIds.add(plot.getId());
                System.out.println(plot.getId()+"\t"+plot.getCrop().name+"\t"+plot.getGrowthStage(week));
            }
            return plotIds;
        }
        return null;
    }



    //Creates a new Plot based on the crop type and plantedDay provided.
    //If the Crop's type (via getCropType()) is "aquatic" (ignoring case),
    // an AquaticPlot is created; otherwise, a LandPlot is created.
    //Can use instanceof instead of getcroptype also
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


     //Finds and returns the conditions of a particular Plot as a Map based on unique plotID.
    public Map<String, Object> findPlotConditions(int plotId) {
        for (Plot p : plotList) {
            if (p.getId() == plotId) {
                Map<String, Object> conditions = new HashMap<>();
                // Get crop type from the Crop object.
                String cropType = p.getCrop().getCropType();
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

