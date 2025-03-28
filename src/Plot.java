class Plot {
    private int id;
    private String plotType;
    private Crop crop;
    private double temperature;
    private double humidity;
    private double lightExposure;
    private double soilMoisture;

    public Plot(int id, String plotType, Crop crop, double temperature, double humidity, double lightExposure, double soilMoisture) {
        this.id = id;
        this.plotType = plotType;
        this.crop = crop;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightExposure = lightExposure;
        this.soilMoisture = soilMoisture;
    }

    public int getId() {
        return id;
    }

    public String getPlotType() {
        return plotType;
    }

    public Crop getCrop() {
        return crop;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getLightExposure() {
        return lightExposure;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }
}