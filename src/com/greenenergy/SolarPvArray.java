package com.greenenergy;

import java.util.ArrayList;

public class SolarPvArray extends Generator{
    private final ArrayList<SolarPvModule> solarPvModules;

    public SolarPvArray() {
        solarPvModules = new ArrayList<SolarPvModule>();
    }

    public void addSolarPvModule(SolarPvModule _solarPvModule){
        //Adds the new Solar PV module to the Solar PV array
        solarPvModules.add(_solarPvModule);

        //Updates the price of the Solar PV array to the sum of added modules.
        this.setPrice(this.getPrice() + _solarPvModule.getPrice());
    }

    public int getNumberOfSolarPvModules(){
        return solarPvModules.size();
    }

    public int getSumOfMinMaxPowOutput(){
        return getTotalMinPowerOutput() + getTotalMaxPowerOutput();
    }

    public int getTotalMinPowerOutput(){
        int totalMinPowerOutput = 0;
        if(!solarPvModules.isEmpty()){
            int counter = 0;
            while (counter < solarPvModules.size()){
                totalMinPowerOutput += solarPvModules.get(counter).getMinPowerOutput(); //Does the summation.
                counter++;
            }
        } else System.out.println("No solar PV module has been added");
        return totalMinPowerOutput;
    }

    public int getTotalMaxPowerOutput(){
        int totalMaxPowerOutput = 0;
        if(!solarPvModules.isEmpty()){
            int counter = 0;
            while (counter < solarPvModules.size()){
                totalMaxPowerOutput += solarPvModules.get(counter).getMaxPowerOutput(); //Does the summation.
                counter++;
            }
        } else System.out.println("No solar PV module has been added");
        return totalMaxPowerOutput;
    }
}
