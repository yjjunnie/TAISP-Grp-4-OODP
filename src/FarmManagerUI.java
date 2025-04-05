import java.security.KeyException;
import java.util.*;
import java.util.stream.Collectors;

public class FarmManagerUI {
    private FarmManager farmManager; // CLIMenu passes UI handling to FarmManagerUI.
    private Scanner io;
    private Time time;

    public FarmManagerUI() {
        this.time = new Time(0);
        this.farmManager = new FarmManager();
        this.io = new Scanner(System.in);
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
    		List<? extends Crop> filteredCropsList;
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
    
    public void harvestPlotMenu(int week) {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				ArrayList<Integer> plotIds = farmManager.displayAllHarvestable(week);
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
    
    public void updateMenu(int week) {
    	boolean isComplete = false;
    	int selection;

		while(!isComplete) {
			try{
				farmManager.displayAllPlotsConditions(week);
				List<Integer> plotIds = farmManager.getPlotIds();
				
				if(plotIds == null || plotIds.isEmpty()) return; // No plots created
				System.out.println("Enter the id of the plot you wish to adjust, or -1 to cancel.");
				
				System.out.print("> ");
				selection = io.nextInt();

				if(selection == -1) return;
				else if(plotIds.contains(selection)) {
					farmManager.editPlotConditions(selection);
					isComplete = true;
				} else {
					System.out.println("ERROR! Invalid selection, please try again!");
					continue;
				}

			}catch(KeyException e) {
				System.out.println("ERROR! Unexpected KeyError has occurred!");
				return;
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
					farmManager.editPlotConditions(selection);
					isComplete = true;
				}else {
					System.out.println("ERROR! Invalid selection, please try again!");
					continue;
				}
				
			}catch(KeyException e) {
				System.out.println("ERROR! Unexpected KeyError has occurred!");
				return;
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

	public void handleFastFoward() {
		Boolean isComplete = false;
    	Boolean confirmed = false;
    	int userInput;
    	
    	loop:while(!isComplete) {
    		try {
    			if(farmManager.hasAlerts()) {
    				while(!confirmed) {
        				System.out.println("There are still pending alerts, fast forwarding will extend crop\n"
                				+ "1. Ignore and continue with fast foward.\n"
                				+ "2. Cancel fast fowarding.");

                		System.out.print("> ");
        				userInput = io.nextInt();
        				
        				switch(userInput) { // Using : case for breaking out of LABELLED loop.
    	        			case 1: 
    	        				// Append Punishment
    	        				confirmed = true;
    	        				break;
    	        			case 2: break loop;
    	        			default: System.out.println("ERROR! Invalid selection, please try again!");
        				}
    				}
    			}
    			
        		System.out.println("Enter the amount of weeks you wish to fast forward by.");
        		
        		System.out.print(">: ");
        		userInput = io.nextInt();
        		
        		fastForward(userInput);
        		isComplete = true;
        		
    		}catch(KeyException e) {
				System.out.println("ERROR! Unexpected KeyError has occurred!");
				return;
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
