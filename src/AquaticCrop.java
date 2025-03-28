public class AquaticCrop extends Crop {
    private int seedlingDays;
    private int matureDays;

    public AquaticCrop(String name, int seedlingDays, int matureDays) {
        super(name);
        this.seedlingDays = seedlingDays;
        this.matureDays = matureDays;
    }

    // Optionally add getters, setters, and other methods.
}
