class HumiditySensor extends Sensor {
    final int created_day;
    private RealHumidity realHumidity;

    // Bidirectional association 
    public void setRealHumidity(RealHumidity realHumidity) {
        this.realHumidity = realHumidity;
        realHumidity.setHumiditySensor(this);
    }
    public RealHumidity getRealHumidity() {
        return realHumidity;
    }

    // Constructor 
    public HumiditySensor(Crop crop) { 
        created_day = (new Time()).getCurrentWeek();
        conditionType = "Humidity"; 
        condition = 25; //NEED LOGIC to be within specific crop's condition range, when first created will be in optimal condition cuz created with plot 
    }


    @Override
    public int getCondition() { // Used to get condition after humidity sensor is created 
        if ((new Time()).getCurrentWeek() - created_day == 0) //checking conditions on day of creation 
            return condition;
        else {
            realHumidity.setCondition("Humidity");
        }
    }
}