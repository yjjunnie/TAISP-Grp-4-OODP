import java.util.*;

public class CLIMenu {
    private Integer currentDay;
    private FarmManager farmManager;  // CLIMenu manages FarmManager
    private Scanner io;

    public CLIMenu() {
        this.currentDay = 0;
//        this.farmManager = new FarmManager();
        this.io = new Scanner(System.in);
    }
    
    public void handleUserActions() {
    	Boolean isComplete = false;
    	int selection;
    	
    	while(!isComplete) {
    		try {
        		System.out.println("Farm Management CLI Commands:\n"
        				+ "1. View .......... View crop/envrionment data\n"
        				+ "2. Manage ........ Manage farm plots\n"
        				+ "3. Update ........ Modify farm conditions\n"
        				+ "4. Alerts ........ View critical alerts\n"
        				+ "5. Fast Forward .. Move foward in days\n"
        				+ "6. Logout ........ Exit Session");
        		
        		System.out.print("Your Selection: ");
        		selection = io.nextInt();
        		
        		switch(selection) {
        			case 1 -> handleView();
        			case 2 -> handleManage();
        			case 3 -> handleUpdate();
        			case 4 -> handleAlerts();
        			case 5 -> handleFastFoward();
        			case 6 -> isComplete = true;
        			default -> System.out.println("ERROR! Invalid selection, please try again!");
        		}
        		
    		}catch(InputMismatchException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
    		}catch(NumberFormatException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
    		}catch(Exception e) {
    			System.out.println("ERROR! Unexpected error has occured, please try again!");
    		}finally {
        		io.nextLine(); // Clearing buffer
    		}
    	}
    	
    	System.out.println("Terminating Session. Thank you for using COF System!");
    }
    
    public void handleView() {
    	System.out.println("handleView called");
    }
    
    public void handleManage() {
    	Boolean isComplete = false;
    	int selection;
    	
    	while(!isComplete) {
    		try {
        		System.out.println("Manage Plots:\n"
        				+ "1. Create new plot\n"
        				+ "2. Harvest plot\n"
        				+ "3. Back to main menu\n");
        		
        		System.out.print("Your Selection: ");
        		selection = io.nextInt();
        		
        		switch(selection) {
//        			case 1 -> farmManager.createPlot(); 
//        			case 2 -> farmManager.harvestPlot();
        			case 3 -> isComplete = true;
        			default -> System.out.println("ERROR! Invalid selection, please try again!");
        		}
        		
    		}catch(NumberFormatException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
    		}catch(Exception e) {
    			System.out.println("ERROR! Unexpected error has occured, please try again!");
    		}
    	}
    }
    
    public void handleUpdate() {
    	System.out.println("handleUpdate called");
    }
    
    public void handleAlerts() {
    	System.out.println("handleAlerts called");
    }
    
    public void handleFastFoward() {
    	System.out.println("handleFastFoward called");
    }

    public void setFarmManager(FarmManager farmManager) {
        this.farmManager = farmManager;
    }

    public void fastForward(int days) {
        currentDay += days;
        System.out.println("Fast forwarded " + days + " days. Current day is now: " + currentDay);
    }

    // Optional: getter for currentDay
    public int getCurrentDay() {
        return currentDay;
    }
}