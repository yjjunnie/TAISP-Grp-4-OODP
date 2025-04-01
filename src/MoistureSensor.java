class MoistureSensor extends Sensor {
    final int created_day;
    private RealMoisture realMoisture;

    // Bidirectional association 
    public void setRealMoisture(RealMoisture realMoisture) {
        this.realMoisture = realMoisture;
        realMoisture.setMoistureSensor(this);
    }
    public RealMoisture getRealMoisture() {
        return realMoisture;
    }

    // Constructor 
    public MoistureSensor(Crop crop) { 
        created_day = climenu.getCurrentDay(); //climenu is an instance of CLIMenu 
        conditionType = "Moisture Level"; 
        condition = 25; //NEED LOGIC to be within specific crop's condition range, when first created will be in optimal condition cuz created with plot 
    }


    @Override
    public int getCondition() { // Used to get condition after moisture sensor is created 
        if (climenu.getCurrentDay() - created_day == 0) //checking conditions on day of creation 
            return condition;
        else {
            realMoisture.setCondition("Moisture Level");
        }
    }
}