import java.util.*;

public class CLIMenu {
    private Integer currentWeek;
    private FarmManager farmManager;  // CLIMenu manages FarmManager
    private Scanner io;

    public CLIMenu() {
        this.currentWeek = 0;
//        this.farmManager = new FarmManager();
        this.io = new Scanner(System.in);
    }
    
    public void handleUserActions() {
    	Boolean isComplete = false;
    	int selection;
    	
    	while(!isComplete) {
    		try {
        		System.out.println("Farm Management CLI Commands (Week "+ currentWeek +"):\n"
        				+ "1. View .......... View crop/envrionment data\n"
        				+ "2. Manage ........ Manage farm plots\n"
        				+ "3. Update ........ Modify farm conditions\n"
        				+ "4. Alerts ........ View critical alerts\n"
        				+ "5. Fast Forward .. Move foward in weeks\n"
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
        		io.nextLine(); // Clearing buffer only when error 
    		}catch(NumberFormatException e) {
    			System.out.println("ERROR! Invalid selection, please try again!");
        		io.nextLine(); // Clearing buffer only when error 
    		}catch(Exception e) {
    			System.out.println("ERROR! Unexpected error has occured, please try again!");
        		io.nextLine(); // Clearing buffer only when error 
    		}
    	}
    	
    	System.out.println("Terminating Session. Thank you for using COF System!");
    }
    
    public void handleView() {
    	// farmManager.listPlots(currentWeek);
    	// Possible further enhancements
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
        			//	case 1 -> farmManager.createPlot(currentWeek); 
        			//  case 2 -> farmManager.harvestPlot(currentWeek);
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
    
    public void handleUpdate() {
//    	farmManger.managePlot();
//    	Possible further enhancements
    }
    
    public void handleAlerts() {
//    	farmManger.displayAlerts();
//    	Possible further enhancements
    }
    
    public void handleFastFoward() {
    	Boolean isComplete = false;
    	Boolean confirmed = false;
    	int userInput;
    	
    	loop:while(!isComplete) {
    		try {
//    			if(farmManager.hasAlerts()) {
//    				while(!confirmed) {
//        				System.out.println("There are still pending alerts, fast forwarding will extend crop\n"
//                				+ "1. Ignore and continue with fast foward.\n"
//                				+ "2. Cancel fast fowarding.");
//
//                		System.out.print("Your Selection: ");
//        				userInput = io.nextInt();
//        				
//        				switch(userInput) { // Using : case for breaking out of LABELLED loop.
//    	        			case 1: 
//    	        				confirmed = true;
//    	        				break;
//    	        			case 2: break loop;
//    	        			default: System.out.println("ERROR! Invalid selection, please try again!");
//        				}
//    				}
//    			}
    			
        		System.out.println("Enter the amount of weeks you wish to fast forward by.");
        		
        		System.out.print("Fast Foward (weeks): ");
        		userInput = io.nextInt();
        		
        		fastForward(userInput);
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
        currentWeek += weeks;
//        <TBC>.ffRandomize(currentWeek);
        System.out.println("------------- Current Week: " + currentWeek + " -------------");
    }

    public int getcurrentWeek() {
        return currentWeek;
    }
}