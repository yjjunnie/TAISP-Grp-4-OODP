class LightSensor extends Sensor {
    final int created_day;
    private RealLight realLight;

    // Bidirectional association 
    public void setRealLight(RealLight realLight) {
        this.realLight = realLight;
        realLight.setLightSensor(this);
    }
    public RealLight getRealLight() {
        return realLight;
    }

    // Constructor 
    public LightSensor(Crop crop) { 
        created_day = climenu.getCurrentDay(); //climenu is an instance of CLIMenu 
        conditionType = "Light Intensity"; 
        condition = 25; //NEED LOGIC to be within specific crop's condition range, when first created will be in optimal condition cuz created with plot 
    }


    @Override
    public int getCondition() { // Used to get condition after light sensor is created 
        if (climenu.getCurrentDay() - created_day == 0) //checking conditions on day of creation 
            return condition;
        else {
            realLight.setCondition("Light Intensity");
        }
    }
}