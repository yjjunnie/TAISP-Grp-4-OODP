package common;

public class Time {
    private static Integer currentWeek;
    private static Integer priorFFWeek;
    
    public Time(int initialWeek) {
    	currentWeek = initialWeek;
        priorFFWeek = initialWeek;
    }
    
    public Time() {}

	public void setCurrentWeek(int week) {
        priorFFWeek = currentWeek;
    	currentWeek = week;
    }
    
    public int getCurrentWeek() {
    	return currentWeek;
    }

    public int getPriorFFWeek() {
        return priorFFWeek;
    }

}
