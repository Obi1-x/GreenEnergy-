package com.greenenergy;

public class SolarPvModule extends Generator{
    private int minPowerOutput;
    private int maxPowerOutput;

    public int getMinPowerOutput() {
        return minPowerOutput;
    }

    public void setMinPowerOutput(int minPowerOutput) {
        this.minPowerOutput = minPowerOutput;
    }

    public int getMaxPowerOutput() {
        return maxPowerOutput;
    }

    public void setMaxPowerOutput(int maxPowerOutput) {
        this.maxPowerOutput = maxPowerOutput;
    }
}
