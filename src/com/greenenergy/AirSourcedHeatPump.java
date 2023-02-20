package com.greenenergy;

public class AirSourcedHeatPump extends HeatPump{
    private int minOperatingTemp;

    public int getMinOperatingTemp() {
        return minOperatingTemp;
    }

    public void setMinOperatingTemp(int minOperatingTemp) {
        this.minOperatingTemp = minOperatingTemp;
    }
}
