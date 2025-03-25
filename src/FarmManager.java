class FarmManager {
    public ArrayList<Plot> plotList;
    public ArrayList<PlotManager> plotManagers;  // FarmManager manages one or more PlotManagers

    public FarmManager() {
        this.plotList = new ArrayList<>();
        this.plotManagers = new ArrayList<>();
    }

    public Plot findPlot(Map<String, Object> parameters) {
        // Dummy implementation: return the first plot that meets criteria.
        // In a full implementation, you would check plot attributes against parameters.
        for (Plot plot : plotList) {
            return plot;
        }
        return null;
    }

    public void addPlot(Plot plot) {
        plotList.add(plot);
    }

    public void addPlotManager(PlotManager plotManager) {
        plotManagers.add(plotManager);
    }
}