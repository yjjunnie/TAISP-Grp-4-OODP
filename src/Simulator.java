class Simulator {
    public int dayLastCleared;

    public Simulator() {
        dayLastCleared = 0;
    }

    public String getCondition() {
        // Return some simulated condition
        return "Simulated condition";
    }

    public void setCondition(String condition) {
        System.out.println("Setting condition to: " + condition);
    }

    public void interactWithSensor(Sensor sensor) {
        System.out.println("Simulator interacting with sensor: " + sensor.getClass().getSimpleName());
    }
}