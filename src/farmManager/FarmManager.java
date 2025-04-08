package farmManager;

import crops.AquaticCrop;
import crops.Crop;
import crops.CropReader;
import crops.LandCrop;
import common.ConditionType;
import common.Time;
import plots.AquaticPlot;
import plots.LandPlot;
import plots.Plot;

import java.util.*;
import java.util.stream.Collectors;

public class FarmManager {
    // List to store Plots.Plot objects.
    private HashMap<Integer, Plot> plotMap;
    private ArrayList<Crop> cropsList;

    // Constructor initializes the plot list.
    public FarmManager() {
        plotMap = new HashMap<>();
        initCrops();
    }

    
    public void initCrops() {
    	// Enhancement with CVS Reading
    	CropReader reader = new CropReader("./src/crops/cropData.csv");
        this.cropsList = (ArrayList<Crop>) reader.getCropsList();
    }

    public List<Integer> getPlotIds() {
        return new ArrayList<>(plotMap.keySet());
    }

    public Plot getPlotById(int id) {
        return plotMap.get(id);
    }
    
    public <T extends Crop> ArrayList<? extends Crop> getCropListByType(Class<T> cropType) {
    	return (ArrayList<? extends Crop>) cropsList.stream()
    			.filter(cropType::isInstance)
                .map(cropType::cast)
                .collect(Collectors.toList());
    }
    
    public boolean hasAlerts() {
    	for (Plot p : plotMap.values()) {
            if (!p.raiseAlert().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public void displayAllPlotsCrops() {
    	int week = (new Time()).getCurrentWeek();
    	
        if (plotMap.isEmpty())
            System.out.println("\nThere are currently 0 plots, please create some plots through <Manage> Menu.");
        else {
            System.out.println("There are currently " + plotMap.size() + " plots.");
            for (Plot plot : plotMap.values()) {
                System.out.println("PlotID: " + plot.getId());
                System.out.println("Crops.Crop: " + plot.getCrop().getName());
                System.out.println("Growth Stage: " + plot.getGrowthStage(week));
                System.out.println("Est Seedling: " + plot.getEstSeedlingWeek());
                System.out.println("Est Harvestable: " + plot.getEstMatureWeek());
                System.out.println("Harvest Status: " + (plot.isHarvestable() ? "Harvestable\n" : "Not Harvestable\n"));
            }
        }
    }

    public void displayAllPlotsConditions() {
        // displayAllPlotsCrops but for plot conditions, and if any alerts.
        if (plotMap.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through the Manage Menu.");
            return;
        }

        for (Plot plot : plotMap.values()) {
            System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plot.getCurrentConditions().entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
            
            if (!plot.raiseAlert().isEmpty()) {
                System.out.println("ALERT: One or more conditions are out of the acceptable range!");
            }
            System.out.println(""); // Formatting for next line
        }

    }

    public ArrayList<Integer> displayAllAlertPlots() {
        boolean hasAlert = false;
        ArrayList<Integer> alertPlotIds = new ArrayList<Integer>();
        int count = 0;
        
        if (plotMap.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through the Manage Menu.");
            return null;
        }

        for(Plot plot : plotMap.values()) {
            HashMap<ConditionType, Integer> plotAlerts = plot.raiseAlert();
            if(!plotAlerts.isEmpty()) {
                System.out.println("Alert " + ++count + ":");
                System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plotAlerts.entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
                System.out.println(""); // Formatting next line
                
                alertPlotIds.add(plot.getId());
                hasAlert = true;
            }
        } 

        if(hasAlert) {
            return alertPlotIds;
        }else {
            System.out.println("All clear! No current alerts.");
            return null;
        }
    }

    public ArrayList<Integer> displayAllHarvestable() {
        ArrayList<Integer> plotIds = new ArrayList<Integer>();
        List<Plot> filteredPlotsList = plotMap.values().stream().filter(plot -> plot.isHarvestable()).toList();
        
        int week = (new Time()).getCurrentWeek();
        

        if (plotMap.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through <Manage> Menu.");
            return null;
        } else if (filteredPlotsList.isEmpty()) {
            System.out.println("Sorry, there are currently no plots that are ready to harvest.");
            return null;
        } else {

            System.out.println("There are currently " + filteredPlotsList.size() + " plots ready to harvest, please select one.");
            for (Plot plot : filteredPlotsList) {
                System.out.println("PlotID:" + plot.getId());
                System.out.println("Crops.Crop:" + plot.getCrop().getName());
                System.out.println("Growth Stage:" + plot.getGrowthStage(week));
                System.out.println("Est Seedling: " + plot.getEstSeedlingWeek());
                System.out.println("Est Harvestable: " + plot.getEstMatureWeek() + "\n");
                plotIds.add(plot.getId());
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
        plotMap.put(plot.getId(), plot);
        System.out.println("Land Plots planted with " + crop.getName() + " is created!");
   }
   
   public void createPlot(AquaticCrop crop, int plantedWeek) {
    	AquaticPlot plot = new AquaticPlot(crop, plantedWeek);
        plotMap.put(plot.getId(), plot);
        System.out.println("Aquatic Plots planted with " + crop.getName() + " is created!");
   }

    public boolean harvestPlot(int plotId){
        if (plotMap.containsKey(plotId)) {
            plotMap.remove(plotId);
            return true;
        }
        return false;
    }

    public void editPlotConditions(Scanner io, int plotId) {
        Plot plot = getPlotById(plotId);
        HashMap<ConditionType, Integer[]> cropConditionList = plot.getCrop().getConditions();
        boolean isComplete = false;

		while(!isComplete) {
			try{
                System.out.println("Editing plot with ID:  " + plot.getId());
                
                for(HashMap.Entry<ConditionType, Integer> entry : plot.getCurrentConditions().entrySet()) {
                    Boolean validValue = false;
                    Integer[] maxMin = cropConditionList.get(entry.getKey());
                    
                    while(!validValue) {
                        System.out.println(entry.getKey() + " [ Min: " + maxMin[0] + ", Max:" + maxMin[1] + " ]" );
                        
                        System.out.print("> ");
                        int inputValue = io.nextInt();
                        
                        if(inputValue >= maxMin[0] && inputValue <= maxMin[1]) {
                            plot.setConditions(entry.getKey(), inputValue);
                            validValue = true;
                        }else {
                            System.out.println("ERROR! Value out of crops range, please try again!");
                            continue;
                        }
                        
                    }
                }
                
                isComplete = true;
			}catch(InputMismatchException e) {
				System.out.println("ERROR! Invalid input, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Invalid input, please try again using integers only!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(Exception e) {
				System.out.println("ERROR! Unexpected error has occured, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}
		}

        System.out.println("Success! Plot conditions have been updated.");
    }
}