class Crop {
    public String name;
    public int seedlingTime;
    public int matureTime;
//    public List<String> conditionList;

    public Crop(String name, int seedlingTime, int matureTime) {
        this.name = name;
        this.seedlingTime = seedlingTime;
        this.matureTime = matureTime;
//        this.conditionList = new ArrayList<>();
    }
}