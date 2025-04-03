import java.util.*;
import java.util.stream.Collectors;

public class FarmManagerUI {
    private FarmManager farmManager; // CLIMenu passes UI handling to FarmManagerUI.
    private Scanner io;
    private ArrayList<Crop> cropsList;

    public FarmManagerUI() {
        this.farmManager = new FarmManager();
        this.io = new Scanner(System.in);
        this.cropsList = new ArrayList<Crop>();
        initCrops();
    }
    
    public void manageMenu(int week) {
    	Boolean isComplete = false;
		int selection;
		
		while(!isComplete) {
			try {
	    		System.out.println("Manage Plots:\n"
	    				+ "1. Create new plot\n"
	    				+ "2. Harvest plot\n"
	    				+ "3. Back to main menu");
	    		
	    		System.out.print("> ");
	    		selection = io.nextInt();
	    		
	    		switch(selection) {
	    			case 1 -> createPlotMenu(week);
	    			case 2 -> harvestPlotMenu(week);
	    			case 3 -> isComplete = true;
	    			default -> System.out.println("ERROR! Invalid selection, please try again!");
	    		}
	    		
			}catch(InputMismatchException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}catch(Exception e) {
				System.out.println("ERROR! Unexpected error has occured, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}
		}
    }
    
    public void createPlotMenu(int week) {
    	boolean isComplete = false;
    	int selection;

    	while(!isComplete) {
    		List<Crop> filteredCropsList;
        	int index = 0;
    		try {
        		System.out.println("Plot Creation.\n"
        				+ "Select plot type:\n"
        				+ "1. Soil\n"
        				+ "2. Aquatic");
        		
        		System.out.print("> ");
        		selection = io.nextInt();
        		
        		switch(selection) { // Open Close Principle violated.
        			case 1 -> {
        				System.out.println("Select crop type for soil plot");
        				filteredCropsList = cropsList.stream().filter(crop -> crop instanceof LandCrop).collect(Collectors.toList());
        			}
        			case 2 -> {
        				System.out.println("Select crop type for aquatic plot");
        				filteredCropsList = cropsList.stream().filter(crop -> crop instanceof AquaticCrop).collect(Collectors.toList());
        			}
        			default -> {
        				System.out.println("ERROR! Invalid selection, please try again!");
        				continue;
        			}
        		}
        		
        		
        		for(Crop crop : filteredCropsList) {
        			System.out.println(++index + ". " + crop.getName());
        		}
        		System.out.print("> ");
        		selection = io.nextInt();
        		if(selection < 1 || selection > index) {
        			System.out.println("ERROR! Invalid selection, please try again!");
    				continue;
        		}else {
        			farmManager.createPlot(filteredCropsList.get(index - 1), week);
        			isComplete = true;
        		}
        		
    		}catch(InputMismatchException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
        		io.nextLine(); // Clearing buffer only when error 
    		}catch(NumberFormatException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
        		io.nextLine(); // Clearing buffer only when error 
    		}catch(Exception e) {
    			System.out.println("ERROR! Unexpected error has occured, please try again!");
        		io.nextLine(); // Clearing buffer only when error 
    		}
    	}
    }
    
    public void harvestPlotMenu(int week) {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				ArrayList<Integer> plotIds = farmManager.displayAllHarvestable(week);
				if(plotIds.size() == 0) return;

				System.out.println("Enter the id of the plot you wish to harvest, or -1 to cancel.");
					
				System.out.print("> ");
				selection = io.nextInt();

				if(selection == -1) return;
				else if(plotIds.contains(selection)) {
					boolean deleted = farmManager.harvestPlot(selection);
					
					if(!deleted){
						System.out.println("ERROR! Unexpected error has occured, Plot with id "+selection+" was not found, please try again!");
						continue;
					}

					return;
				} else {
					System.out.println("ERROR! Invalid selection, please try again!");
					continue;
				}
					
			}catch(InputMismatchException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(Exception e) {
				System.out.println("ERROR! Unexpected error has occured, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}
		}
    }
    
    public void updateMenu(int week) {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				farmManager.displayAllPlotsConditions(week);
				List<Integer> plotIds = farmManager.getPlotIds();
				
				if(plotIds.size() == 0) return;
				System.out.println("Enter the id of the plot you wish to adjust, or -1 to cancel.");
				
				System.out.print("> ");
				selection = io.nextInt();

				if(selection == -1) return;
				else if(plotIds.contains(selection)) {
					// ADJUSTMENT CODE

					return;
				} else {
					System.out.println("ERROR! Invalid selection, please try again!");
					continue;
				}

			}catch(InputMismatchException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}catch(Exception e) {
				System.out.println("ERROR! Unexpected error has occured, please try again!");
				io.nextLine(); // Clearing buffer only when error 
			}
		}
		
    }

	public void alertMenu() {
		System.out.println("ALERT MENU: Displaying all plots with alerts.");

		// Get a list of all plot IDs.
		List<Integer> allPlotIds = farmManager.getPlotIds();
		// List to hold IDs of plots that currently have alerts.
		List<Integer> alertPlotIds = new ArrayList<>();

		// Display plots that have alerts.
		for (Integer id : allPlotIds) {
			Plot p = farmManager.getPlotById(id);
			if (p != null && p.raiseAlert()) {
				alertPlotIds.add(id);
				System.out.println("-------------------------------------------------");
				System.out.println("Plot ID: " + p.getId() + " | Crop: " + p.getCrop().getName());
				System.out.println("Current Conditions: " + p.getCurrentConditions().toString());
				System.out.println("Ideal Conditions: " + p.getCrop().getConditions().toString());
				System.out.println("-------------------------------------------------");
			}
		}

		if (alertPlotIds.isEmpty()) {
			System.out.println("There are no plots currently with alerts.");
			return;
		}

		// Prompt user to select a plot by ID.
		System.out.println("Enter the ID of the plot you wish to update conditions for, or -1 to cancel:");
		System.out.print("> ");
		int selectedId = io.nextInt();

		if (selectedId == -1) {
			System.out.println("Operation cancelled.");
			return;
		}

		if (!alertPlotIds.contains(selectedId)) {
			System.out.println("Invalid selection. The entered ID does not correspond to a plot with alerts.");
			return;
		}

		// Retrieve the selected plot.
		Plot selectedPlot = farmManager.getPlotById(selectedId);
		if (selectedPlot == null) {
			System.out.println("Plot not found.");
			return;
		}

		// For each condition, if the current reading is out-of-range, prompt the user to enter a new value.
		HashMap<String, Integer> currentConds = selectedPlot.getCurrentConditions();
		HashMap<String, int[]> idealConds = selectedPlot.getCrop().getConditions();

		for (String conditionType : idealConds.keySet()) {
			int[] range = idealConds.get(conditionType);
			if (currentConds.containsKey(conditionType)) {
				int currentValue = currentConds.get(conditionType);
				if (currentValue < range[0] || currentValue > range[1]) {
					System.out.println("Condition '" + conditionType + "' is out-of-range: current value = "
							+ currentValue + ", ideal range = [" + range[0] + ", " + range[1] + "]");
					System.out.print("Enter new value for " + conditionType + " (or -1 to skip): ");
					int newVal = io.nextInt();
					if (newVal != -1) {
						// Update the condition; the plot's setConditions() method updates the sensor value. and should remove alert too
						selectedPlot.setConditions(conditionType, newVal);
					}
				}
			}
		}

		// Display updated conditions.
		System.out.println("Updated Conditions for Plot ID " + selectedPlot.getId() + ": "
				+ selectedPlot.getCurrentConditions().toString());
		if (!selectedPlot.raiseAlert()) {
			System.out.println("Alerts cleared for Plot ID " + selectedPlot.getId());
		} else {
			System.out.println("Some alerts still persist for Plot ID " + selectedPlot.getId());
		}
	}



	public void viewMenu(int week) {
    	Boolean isComplete = false;
		int selection;
		
		while(!isComplete) {
			try {
	    		System.out.println("Select plot statuses to view:\n"
	    				+ "1. Crop Statuses\n"
	    				+ "2. Condition Statuses\n"
	    				+ "3. Back to main menu");
	    		
	    		System.out.print("> ");
	    		selection = io.nextInt();
	    		
	    		switch(selection) {
	    			case 1 -> farmManager.displayAllPlotsCrops(week);
	    			case 2 -> farmManager.displayAllPlotsConditions(week);
	    			case 3 -> isComplete = true;
	    			default -> System.out.println("ERROR! Invalid selection, please try again!");
	    		}
	    		
			}catch(InputMismatchException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Invalid selection, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}catch(Exception e) {
				System.out.println("ERROR! Unexpected error has occured, please try again!");
	    		io.nextLine(); // Clearing buffer only when error 
			}
		}
    }

    
    public void initCrops() {
    	// Enhancement with CVS reading
//    	cropsList.add(new LandCrop("Wheat", 20, 30));
//    	cropsList.add(new LandCrop("Corn", 20, 40));
//    	cropsList.add(new LandCrop("Tomatoes", 15, 10));
//    	cropsList.add(new AquaticCrop("Lettuce", 20, 30));
//    	cropsList.add(new AquaticCrop("Spinach", 20, 40));
//    	cropsList.add(new AquaticCrop("Peppers", 15, 10));
    }
}
