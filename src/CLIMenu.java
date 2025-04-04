import java.util.*;

public class CLIMenu {
    private FarmManagerUI farmManagerUI; // CLIMenu passes UI handling to FarmManagerUI.
    private Scanner io;

    public CLIMenu() {
        this.farmManagerUI = new FarmManagerUI();
        this.io = new Scanner(System.in);
    }
    
    public void handleUserActions() {
    	Boolean isComplete = false;
    	int selection;
    	
    	while(!isComplete) {
    		try {
        		System.out.println("Farm Management CLI Commands (Week "+ (new Time()).getCurrentWeek() +"):\n"
        				+ "1. View .......... View crop/environment data\n"
        				+ "2. Manage ........ Manage farm plots\n"
        				+ "3. Update ........ Modify farm conditions\n"
        				+ "4. Alerts ........ View critical alerts\n"
        				+ "5. Fast Forward .. Move foward in weeks\n"
        				+ "6. Logout ........ Exit Session");
        		
        		System.out.print("> ");
        		selection = io.nextInt();
        		
        		switch(selection) {
        			case 1 -> farmManagerUI.viewMenu((new Time()).getCurrentWeek());
        			case 2 -> farmManagerUI.manageMenu((new Time()).getCurrentWeek());
        			case 3 -> farmManagerUI.updateMenu((new Time()).getCurrentWeek());
        			case 4 -> farmManagerUI.alertMenu();
        			case 5 -> farmManagerUI.handleFastFoward();
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
}