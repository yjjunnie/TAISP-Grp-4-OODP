class Plot {
    public Crop crop;
    public Date estSeedlingDate;
    public Date estMatureDate;
    public ArrayList<Sensor> sensors;
    public int punishment;

    public Plot(Crop crop, Date estSeedlingDate, Date estMatureDate) {
        this.crop = crop;
        this.estSeedlingDate = estSeedlingDate;
        this.estMatureDate = estMatureDate;
        this.sensors = new ArrayList<>();
        this.punishment = 0;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void checkConditions() {
        System.out.println("Checking conditions for plot with crop: " + crop.name);
        // Here you could add logic to iterate through sensors and evaluate crop conditions
    }

    public void clear() {
        System.out.println("Clearing plot with crop: " + crop.name);
        punishment = 0;
        // Reset or update other plot attributes as needed
    }
}