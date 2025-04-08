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
    private ArrayList<Plot> plotList;
    private ArrayList<Crop> cropsList;

    // Constructor initializes the plot list.
    public FarmManager() {
        plotList = new ArrayList<>();
        initCrops();
    }

    
    public void initCrops() {
    	// Enhancement with CVS Reading
    	CropReader reader = new CropReader("./src/crops/cropData.csv");
        this.cropsList = (ArrayList<Crop>) reader.getCropsList();
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
    
    public <T extends Crop> ArrayList<? extends Crop> getCropListByType(Class<T> cropType) {
    	return (ArrayList<? extends Crop>) cropsList.stream()
    			.filter(cropType::isInstance)
                .map(cropType::cast)
                .collect(Collectors.toList());
    }
    
    public boolean hasAlerts() {
    	for (Plot p : plotList) {
            if (!p.raiseAlert().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public void displayAllPlotsCrops() {
    	int week = (new Time()).getCurrentWeek();
    	
        if (plotList.isEmpty())
            System.out.println("\nThere are currently 0 plots, please create some plots through <Manage> Menu.");
        else {
            System.out.println("There are currently " + plotList.size() + " plots.");
            for (Plot plot : plotList) {
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
        if (plotList.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through the Manage Menu.");
            return;
        }

        for (Plot plot : plotList) {
            System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plot.getCurrentConditions().entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
            
            if (!plot.raiseAlert().isEmpty()) {
                System.out.println("ALERT: One or more conditions are out of the acceptable range!");
            }
        }

    }

    public ArrayList<Integer> displayAllAlertPlots() {
        boolean hasAlert = false;
        ArrayList<Integer> alertPlotIds = new ArrayList<Integer>();
        int count = 0;
        
        if (plotList.isEmpty()) {
            System.out.println("There are currently 0 plots, please create some plots through the Manage Menu.");
            return null;
        }

        for(Plot plot : plotList) {
            HashMap<ConditionType, Integer> plotAlerts = plot.raiseAlert();
            if(plotAlerts != null) {
                System.out.println("Alert " + ++count + ":");
                System.out.println("PlotID:" + plot.getId());

                for(HashMap.Entry<ConditionType, Integer> entry : plotAlerts.entrySet()) {
                    System.out.println(entry.getKey().toString() +": " + entry.getValue());
                }
                
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
        List<Plot> filteredPlotsList = plotList.stream().filter(plot -> plot.isHarvestable()).toList();
        
        int week = (new Time()).getCurrentWeek();
        

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
                System.out.println("Crops.Crop:" + plot.getCrop().getName());
                System.out.println("Growth Stage:" + plot.getGrowthStage(week));
                System.out.println("Est Seedling: " + plot.getEstSeedlingWeek());
                System.out.println("Est Harvestable: " + plot.getEstMatureWeek());
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
        plotList.add(plot);
        System.out.println("Land Plots.Plot planted with " + crop.getName() + " is created!");
   }
   
   public void createPlot(AquaticCrop crop, int plantedWeek) {
    	AquaticPlot plot = new AquaticPlot(crop, plantedWeek);
        plotList.add(plot);
        System.out.println("Aquatic Plots.Plot planted with " + crop.getName() + " is created!");
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

    public void editPlotConditions(int plotId) {
        Scanner io = new Scanner(System.in);
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

        System.out.println("Success! Plots.Plot conditions have been updated.");
    }
}