class PlotManager {
    private List<Plot> plots;

    public PlotManager() {
        this.plots = new ArrayList<>();
    }

    public void addPlot(Plot plot) {
        plots.add(plot);
    }

    public void checkAll() {
        for (Plot plot : plots) {
            plot.checkConditions();
        }
    }

    public void checkConditions() {
        System.out.println("Checking conditions for all plots...");
        for (Plot plot : plots) {
            plot.checkConditions();
        }
    }

    public void clearPlot() {
        System.out.println("Clearing plots...");
        for (Plot plot : plots) {
            plot.clear();
        }
    }
}
