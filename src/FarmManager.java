import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

public class FarmManager {
    // List to store Plot objects.
    private ArrayList<Plot> plotList;

    // Constructor initializes the plot list.
    public FarmManager() {
        plotList = new ArrayList<>();
    }

    public List<Integer> getPlotIds() {
        return plotList.stream().map(plot -> plot.getId()).collect(Collectors.toList());
    }

	public void displayAllPlotsCrops(int week) {
		if(plotList.size() == 0) 
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
		// displayAllPlotsCrops but for plot conditions, and if any alerts.
	}
	
	public ArrayList<Integer> displayAllHarvestable(int week) {
		ArrayList<Integer> plotIds = new ArrayList<Integer>();
		List<Plot> filteredPlotsList = plotList.stream().filter(plot -> plot.getGrowthStage(week) == "Mature - Ready to harvest").collect(Collectors.toList());
		
		if(plotList.size() == 0 ) {
			System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
		}else if(filteredPlotsList.size() == 0){
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

