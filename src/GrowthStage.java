public enum GrowthStage {
    SEED("seed"),
    SEEDLING("seedling"),
    MATURE("mature");

    private String growthStageName;

    GrowthStage(String growthStageNameName) {
        this.growthStageName = growthStageNameName;
    }

    @Override
    public String toString() {
        return this.growthStageName;
    }
}
