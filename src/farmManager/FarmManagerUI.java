package farmManager;

import crops.AquaticCrop;
import crops.Crop;
import crops.LandCrop;
import common.Time;

import java.util.*;

public class FarmManagerUI {
    private FarmManager farmManager; // menu.CLIMenu passes UI handling to farmManager.FarmManagerUI.
    private Scanner io;
    private Time time;

    public FarmManagerUI() {
        this.time = new Time(0);
        this.farmManager = new FarmManager();
        this.io = new Scanner(System.in);
    }
    
    public void manageMenu() {
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
	    			case 1 -> createPlotMenu();
	    			case 2 -> harvestPlotMenu();
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
    
    public void createPlotMenu() {
    	boolean isComplete = false;
    	int selection;
    	int week = (new Time()).getCurrentWeek();

    	while(!isComplete) {
    		List<? extends Crop> filteredCropsList;
        	int index = 0;
    		try {
        		System.out.println("Plots Creation.\n"
        				+ "Select plot type:\n"
        				+ "1. Soil\n"
        				+ "2. Aquatic");
        		
        		System.out.print("> ");
        		selection = io.nextInt();
        		
        		switch(selection) { // Open Close Principle violated.
	        		case 1 -> {
	    				System.out.println("Select crop type for soil plot");
	    				filteredCropsList = farmManager.getCropListByType(LandCrop.class);
	    			}
	    			case 2 -> {
	    				System.out.println("Select crop type for aquatic plot");
	    				filteredCropsList = farmManager.getCropListByType(AquaticCrop.class);
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
        			Crop selectedCrop = filteredCropsList.get(selection - 1);
        			if(selectedCrop instanceof LandCrop) {
            			farmManager.createPlot((LandCrop) selectedCrop, week);
            			isComplete = true;
        			}else if(selectedCrop instanceof AquaticCrop) {
            			farmManager.createPlot((AquaticCrop) selectedCrop, week);
            			isComplete = true;
        			}else {
            			farmManager.createPlot(selectedCrop, week);
        			}
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
    
    public void harvestPlotMenu() {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				ArrayList<Integer> plotIds = farmManager.displayAllHarvestable();
				if(plotIds == null) return; // No plots created
				if(plotIds.size() == 0) return; // No harvestable crops

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

					isComplete = true;
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
    
    public void updateMenu() {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				farmManager.displayAllPlotsConditions();
				List<Integer> plotIds = farmManager.getPlotIds();
				
				if(plotIds == null || plotIds.isEmpty()) return; // No plots created
				System.out.println("Enter the id of the plot you wish to adjust, or -1 to cancel.");
				
				System.out.print("> ");
				selection = io.nextInt();

				if(selection == -1) return;
				else if(plotIds.contains(selection)) {
					farmManager.editPlotConditions(io, selection);
					isComplete = true;
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
		boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				ArrayList<Integer> alertIds = farmManager.displayAllAlertPlots();

				if (alertIds == null || alertIds.isEmpty()) {
					return;
				}

				System.out.println("Enter the ID of the plot you wish to update conditions for, or -1 to cancel:");
				System.out.print("> ");
				selection = io.nextInt();

				if (selection == -1) {
					return;
				}else if (alertIds.contains(selection)) {
					farmManager.editPlotConditions(io, selection);
					isComplete = true;
				}else {
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



	public void viewMenu() {
    	Boolean isComplete = false;
		int selection;
		
		while(!isComplete) {
			try {
	    		System.out.println("Select view type:\n"
	    				+ "1. Crops Statuses\n"
	    				+ "2. Condition Statuses\n"
	    				+ "3. Back to main menu");
	    		
	    		System.out.print("> ");
	    		selection = io.nextInt();
	    		
	    		switch(selection) {
	    			case 1 -> farmManager.displayAllPlotsCrops();
	    			case 2 -> farmManager.displayAllPlotsConditions();
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

	public void handleFastFoward() {
		Boolean isComplete = false;
    	Boolean confirmed = false;
    	int userInput;
    	
    	loop:while(!isComplete) {
    		try {
    			if(farmManager.hasAlerts()) {
    				while(!confirmed) {
        				System.out.println("There are still outstanding alerts, fast forwarding will extend crop\n"
                				+ "1. Ignore and continue with fast foward.\n"
                				+ "2. Cancel fast fowarding.");

                		System.out.print("> ");
        				userInput = io.nextInt();
        				
        				switch(userInput) { // Using : case for breaking out of LABELLED loop.
    	        			case 1: 
    	        				// Lets user reset conditions of plot
								// Does NOT clear accured punishments
    	        				confirmed = true;
    	        				break;
    	        			case 2: break loop;
    	        			default: System.out.println("ERROR! Invalid selection, please try again!");
        				}
    				}
    			}
    			
        		System.out.println("Enter the amount of weeks you wish to fast forward by.");
        		
        		System.out.print("> ");
        		userInput = io.nextInt();
        		if(userInput < 1) {
        			System.out.println("ERROR! Invalid input, must be weeks by be more than 0, please try again!");
        			continue;
        		}
        		fastForward(userInput);
        		farmManager.triggerRandomize();
				isComplete = true;

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

    public void fastForward(int weeks) {
    	int currentWeek = time.getCurrentWeek() + weeks;
        time.setCurrentWeek(currentWeek);
        System.out.println("------------- Current Week: " + currentWeek + " -------------");
    }
}
