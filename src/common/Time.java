package common;

public class Time {
    private static Integer currentWeek;
    
    public Time(int initialWeek) {
    		currentWeek = initialWeek;
    }
    
    public Time() {}

	public void setCurrentWeek(int week) {
    	currentWeek = week;
    }
    
    public int getCurrentWeek() {
    	return currentWeek;
    }

}
