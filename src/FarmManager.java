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

	public void displayAllPlots(int week) {
		if(plotList.size() == 0) 
			System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
		else {
			System.out.println("There are currently "+ plotList.size() +" plots.");
			for(Plot plot : plotList) {
				System.out.println("PlotID\tCrop\tGrowth Stage\tHarvest Status\n");
				System.out.print(plot.getId()+"\t"+plot.getCrop().name);
				if(plot.getEstSeedlingWeek() > week) 
					System.out.print("\tSeedling\tNot Ready");
				else
					System.out.print("\tMature\tReady");
				
			}
			// Potentially add alert status
		}
	}
	
    public void createPlot(Crop crop, int plantedWeek) {
        if(crop instanceof LandCrop) {
        	plotList.add(new LandPlot(crop, plantedWeek));
        }else if(crop instanceof AquaticCrop) {
        	plotList.add(new LandPlot(crop, plantedWeek));
        }else {
        	System.out.println("ERROR! Unknown crop or plot type, please try again!");
        }
    }
    
//    public void createPlot(LandCrop crop, int plantedWeek) {
//    	LandPlot plot = new LandPlot(crop, plantedWeek);
//        plotList.add(plot);
//    }
//    
//    public void createPlot(AquaticCrop crop, int plantedWeek) {
//    	AquaticPlot plot = new AquaticPlot(crop, plantedWeek);
//        plotList.add(plot);
//    }


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
//    public Map<String, Object> findPlotConditions(int plotId) {
//        for (Plot p : plotList) {
//            if (p.getId() == plotId) {
//                Map<String, Object> conditions = new HashMap<>();
//                // Get crop type from the Crop object.
//                String cropType = p.getCrop().getName();
//                conditions.put("cropType", cropType);
//                // Determine plotType based on the crop's type.
//                if (p.getCrop().getCropType().equalsIgnoreCase("aquatic")) {
//                    conditions.put("plotType", "Aquatic");
//                } else {
//                    conditions.put("plotType", "Land");
//                }
//                // Add sensor readings.
//                conditions.putAll(p.getCurrentConditions());
//                return conditions;
//            }
//        }
//        return null;
//    }
}

