public class CLIMenu {
    private Integer currentDay;
    private FarmManager farmManager;  // CLIMenu manages FarmManager

    public CLIMenu() {
        this.currentDay = 0;
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