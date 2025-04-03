import java.util.*;
import java.util.stream.Collectors;

public class FarmManager {
    // List to store Plot objects.
    private ArrayList<Plot> plotList;

    // Constructor initializes the plot list.
    public FarmManager() {
        plotList = new ArrayList<>();
    }

    public List<Integer> getPlotIds() {
        return plotList.stream().map(Plot::getId).collect(Collectors.toList());
    }

    public Plot getPlotById(int id) {
        for (Plot p : plotList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public void displayAllPlotsCrops(int week) {
        if (plotList.isEmpty())
            System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
        else {
            System.out.println("There are currently " + plotList.size() + " plots.");
            for (Plot plot : plotList) {
                System.out.println("PlotID:" + plot.getId());
                System.out.println("Crop:" + plot.getCrop().getName());
                System.out.println("Growth Stage:" + plot.getGrowthStage(week));
                System.out.println("Harvest Status:" + (plot.isHarvestable() ? "Harvestable" : "Not Harvestable"));
                System.out.println("PlotID\tCrop\tGrowth Stage\tHarvest Status\n");
            }
        }
    }

    public void displayAllPlotsConditions(int week) {
        // displayAllPlotsCrops but for plot conditions, and if any alerts.
        if (plotList.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through the Manage Menu.");
            return;
        }

        for (Plot plot : plotList) {
            System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plot.getCurrentConditions().entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
            
            if (plot.raiseAlert()) {
                System.out.println("ALERT: One or more conditions are out of the acceptable range!");
            }
        }

    }

    public ArrayList<Integer> displayAllAlertPlots() {
        boolean hasAlert = false;
        ArrayList<Integer> alertPlotIds = new ArrayList<Integer>();
        int count = 0;

        for(Plot plot : plotList) {
            if(plot.raiseAlert) {
                System.out.println("Alert " + ++count + ":");
                System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plot.getCurrentConditions().entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
                
                alertPlotIds.add(plot.getId());
                hasAlert = true;
            }
        } 

        if(!hasAlert) {
            return alertPlotIds;
        }else {
            System.out.println("All clear! No current alerts.");
            return null;
        }
    }

    public ArrayList<Integer> displayAllHarvestable(int week) {
        ArrayList<Integer> plotIds = new ArrayList<Integer>();
        List<Plot> filteredPlotsList = plotList.stream().filter(plot -> plot.isHarvestable()).toList();

        if (plotList.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
            return null;
        } else if (filteredPlotsList.isEmpty()) {
            System.out.println("Sorry, there are currently no plots that are ready to harvest.");
            return null;
        } else {

            System.out.println("There are currently " + filteredPlotsList.size() + " plots ready to harvest, please select one.");
            for (Plot plot : filteredPlotsList) {
                System.out.println("PlotID:" + plot.getId());
                System.out.println("Crop:" + plot.getCrop().getName());
                System.out.println("Growth Stage:" + plot.getGrowthStage(week));
            }
            return plotIds;
        }
    }

    public void createPlot(Crop crop, int plantedWeek) {
        System.out.println("ERROR! Unknown crop or plot type, please try again!");
        // Uses method overloading for plot creation.
        // Generic method is to prevent compilation error.
    }

    public void createPlot(LandCrop crop, int plantedWeek) {
        LandPlot plot = new LandPlot(crop, plantedWeek);
        plotList.add(plot);
   }
   
   public void createPlot(AquaticCrop crop, int plantedWeek) {
    	AquaticPlot plot = new AquaticPlot(crop, plantedWeek);
        plotList.add(plot);
   }

    public boolean harvestPlot(int plotId){
        Iterator<Plot> iterator = plotList.iterator();

        while (iterator.hasNext()) {
            Plot p = iterator.next();

            if (p.getId() == plotId) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
}


