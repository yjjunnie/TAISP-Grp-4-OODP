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
        			System.out.println(++index + ". " + crop.name);
        		}
        		System.out.print(">: ");
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
    }
    
    public void updateMenu() {
    	
    }
    
    public void alertMenu() {
    	
    }
    
    public void harvestPlot(int week) {
    	
    }
    
    public void listPlots(int week) {
    	farmManager.displayAllPlots(week);
    }
    
    public void managePlot() {
    	
    }

    public void displayAlerts() {
    	
    }
    
    public void initCrops() {
    	// Enhancement with CVS reading
    	cropsList.add(new LandCrop("Wheat", 20, 30));
    	cropsList.add(new LandCrop("Corn", 20, 40));
    	cropsList.add(new LandCrop("Tomatoes", 15, 10));
    	cropsList.add(new AquaticCrop("Lettuce", 20, 30));
    	cropsList.add(new AquaticCrop("Spinach", 20, 40));
    	cropsList.add(new AquaticCrop("Peppers", 15, 10));
    }
}
