class TemperatureSensor extends Sensor {
    final int created_day;
    private RealTemp realTemp;

    // Bidirectional association 
    public void setRealTemp(RealTemp realTemp) {
        this.realTemp = realTemp;
        realTemp.setTemperatureSensor(this);
    }
    public RealTemp getRealTemp() {
        return realTemp;
    }

    // Constructor 
    public TemperatureSensor(Crop crop) { 
        created_day = climenu.getCurrentDay(); //climenu is an instance of CLIMenu 
        conditionType = "Temperature"; 
        condition = 25; //NEED LOGIC to be within specific crop's condition range, when first created will be in optimal condition cuz created with plot 
    }


    @Override
    public int getCondition() { // Used to get condition after humidity sensor is created 
        if (climenu.getCurrentDay() - created_day == 0) //checking conditions on day of creation 
            return condition;
        else {
            realTemp.setCondition("Temperature");
        }
    }

}