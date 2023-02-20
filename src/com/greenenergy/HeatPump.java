package com.greenenergy;

public abstract class HeatPump extends Generator{
    private int COP; //Coefficient of Performance.

    public int getCOP() {
        return COP;
    }

    public void setCOP(int COP) {
        this.COP = COP;
    }
}
