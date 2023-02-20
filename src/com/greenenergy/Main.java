package com.greenenergy;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Generator> generators;
    private static ArrayList<Generator> sortedGen;

    public static void main(String[] args) {
        generators = GeneratorData.readGeneratorList(); //Load list of generators file.
        sortedGen = new ArrayList<Generator>();

        while(true){
            displayMessage(UIStringTemplate.MAIN_MENU);

            Scanner actionScn = new Scanner(System.in);
            int action = 0;
            while (action == 0){
                try { //For error handling
                    while(action == 0 || action > 3){
                        action = actionScn.nextInt();
                        if(action == 0 || action > 3) System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT);
                        //Breaks the loop once a valid input is entered.
                    }

                    switch (action){
                        case 1:
                            //Create generator.
                            selectGenType();
                            break;
                        case 2:
                            //Get generators.
                            if(generators.isEmpty()){ //When no generator has been created.
                                System.out.println(UIStringTemplate.GEN_NOT_FOUND_PROMPT);
                            }else{
                                selectSearchCriteria();
                            }
                            break;
                        case 3:
                            //Get a generator.
                            getGenerator();
                            break;
                    }
                }catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT);
                    actionScn.nextLine(); //To prevent error that occur when reading the scanner at .nextInt() above.
                    action = 0;
                }
            }
        }
    }

    private static void addGenerator(Generator createdGenerator) {
        System.out.println("You are creating a new generator.");

        System.out.println("Enter it's make:");
        Scanner scanMake = new Scanner(System.in);
        String newGenMake = scanMake.nextLine();
        createdGenerator.setMake(newGenMake);

        System.out.println("Enter it's model:");
        Scanner scanModel = new Scanner(System.in);
        String newGenModel = scanModel.nextLine();
        createdGenerator.setModel(newGenModel);

        if(!(createdGenerator instanceof SolarPvArray)){ //When user is not creating a SolarPv Array.
            System.out.println("Enter it's price (pounds):");
            Scanner scanPrice = new Scanner(System.in);
            String newGenPrice = scanPrice.nextLine();
            createdGenerator.setPrice(Integer.parseInt(newGenPrice)); // Converts the string to integer before setting the price.
        }

        if (createdGenerator instanceof SolarPvModule) { //Replaced the switch statement.

            //Solar PV module
            System.out.println("Enter it's MINIMUM power output:");
            Scanner scanMinPowerOutput = new Scanner(System.in);
            String minPowerOutput = scanMinPowerOutput.nextLine();
            ((SolarPvModule)createdGenerator).setMinPowerOutput(Integer.parseInt(minPowerOutput));

            System.out.println("Enter it's MAXIMUM power output:");
            Scanner scanMaxPowerOutput = new Scanner(System.in);
            String maxPowerOutput = scanMaxPowerOutput.nextLine();
            ((SolarPvModule)createdGenerator).setMaxPowerOutput(Integer.parseInt(maxPowerOutput));

        } else if (createdGenerator instanceof SolarPvArray) {

            //Solar PV array.
            System.out.println("A solar PV array comprises several similar solar PV modules.");
            System.out.println("Enter the number of solar PV modules to add:");
            Scanner scanModuleNum = new Scanner(System.in);
            String moduleNum = scanModuleNum.nextLine();

            System.out.println("Make of solar PV modules to add:");
            Scanner scanModuleMake = new Scanner(System.in);
            String moduleMake = scanModuleMake.nextLine();

            System.out.println("Model of solar PV modules to add:");
            Scanner scanModuleModel = new Scanner(System.in);
            String moduleModel = scanModuleModel.nextLine();

            System.out.println("Price of solar PV modules to add:");
            Scanner scanModulePrice = new Scanner(System.in);
            String modulePrice = scanModulePrice.nextLine();

            System.out.println("MINIMUM power output of solar PV modules to add:");
            Scanner scanModuleMinPowerOutput = new Scanner(System.in);
            String moduleMinPowerOutput = scanModuleMinPowerOutput.nextLine();

            System.out.println("MAXIMUM power output of solar PV modules to add:");
            Scanner scanModuleMaxPowerOutput = new Scanner(System.in);
            String moduleMaxPowerOutput = scanModuleMaxPowerOutput.nextLine();

            int solarModulesInArray = 0;
            while (solarModulesInArray < Integer.parseInt(moduleNum)){
                SolarPvModule moduleInArray = new SolarPvModule();

                moduleInArray.setMake(moduleMake);
                moduleInArray.setModel(moduleModel);
                moduleInArray.setPrice(Integer.parseInt(modulePrice));
                moduleInArray.setMinPowerOutput(Integer.parseInt(moduleMinPowerOutput));
                moduleInArray.setMaxPowerOutput(Integer.parseInt(moduleMaxPowerOutput));

                ((SolarPvArray)createdGenerator).addSolarPvModule(moduleInArray);
                solarModulesInArray++;
            }

        } else if (createdGenerator instanceof AirSourcedHeatPump){

            //Air sourced heat pump
            System.out.println("Enter it's coefficient of performance:");
            Scanner scanAirCop = new Scanner(System.in);
            String airCop = scanAirCop.nextLine();
            ((AirSourcedHeatPump)createdGenerator).setCOP(Integer.parseInt(airCop));

            System.out.println("Enter it's MINIMUM operating temperature:");
            Scanner scanMinOpTemp = new Scanner(System.in);
            String minOpTemp = scanMinOpTemp.nextLine();
            ((AirSourcedHeatPump)createdGenerator).setMinOperatingTemp(Integer.parseInt(minOpTemp));

        } else if (createdGenerator instanceof GroundSourceHeatPump){

            //Ground sourced heat pump"
            System.out.println("Enter it's coefficient of performance:");
            Scanner scanGroundCop = new Scanner(System.in);
            String groundCop = scanGroundCop.nextLine();
            ((GroundSourceHeatPump)createdGenerator).setCOP(Integer.parseInt(groundCop));

            System.out.println("Enter it's MINIMUM land area:");
            Scanner scanMinLandArea = new Scanner(System.in);
            String minLandArea = scanMinLandArea.nextLine();
            ((GroundSourceHeatPump)createdGenerator).setMinLandArea(Integer.parseInt(minLandArea));

        }
        generators.add(createdGenerator);
        System.out.println("Generator created! \n");

        GeneratorData.updateGeneratorList(generators);
    }

    private static void getGenerator() {
        if(generators.isEmpty()){     //When no generator has been created.
            System.out.println(UIStringTemplate.GEN_NOT_FOUND_PROMPT);
        }else{
            System.out.println("Enter the make of the generator");
            Scanner scanMake = new Scanner(System.in);
            String searchMake = scanMake.nextLine();
            int counter = 0; int found = 0;
            while(counter < generators.size()){   //Search list of generators
                Generator aGeneratorToFind = generators.get(counter);
                if(searchMake.equals(aGeneratorToFind.getMake())){ //If there is match
                    displayGenerator(aGeneratorToFind, counter);
                    found++; //Represents the number of positive matches.
                }
                counter++;
            }
            if(found == 0) System.out.println(UIStringTemplate.GEN_NOT_FOUND_PROMPT);
        }
    }

    private static void getGenerator_s(int criteria) {
        int counter = 0;
        while(counter < generators.size()){
            //Display the gen property.
            Generator aGenerator = generators.get(counter);

            if(criteria == 1) { //Get all generators.
                displayGenerator(aGenerator, counter);
            }
            else if(criteria == 2){ //Get all generators sorted by price.
                sortedGen.add(aGenerator);
            }
            else if(criteria == 3){ //Get all solar PV modules.
                if(aGenerator instanceof SolarPvModule) displayGenerator(aGenerator, counter);
            }
            else if (criteria == 4){ //Get all heat pumps.
                if(aGenerator instanceof HeatPump) displayGenerator(aGenerator, counter);
            }
            counter++;
        }
    }

    private static void selectSearchCriteria() {
        displayMessage(UIStringTemplate.GEN_FILTER_MENU);

        Scanner displayCriteriaScn = new Scanner(System.in);
        int displayCriteria = 0;
        while (displayCriteria == 0){
            try { //For error handling
                while(displayCriteria == 0 || displayCriteria > 4){
                    displayCriteria = displayCriteriaScn.nextInt();
                    if(displayCriteria == 0 || displayCriteria > 4) System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT_2);
                    //Breaks the loop once a valid input is entered.
                }
                getGenerator_s(displayCriteria);
                if(displayCriteria == 2){
                    //Sort the array list and display it.
                    sortedGen.sort((currentGen, nextGen) -> currentGen.getPrice() - nextGen.getPrice());
                    for(int c = 0; c < sortedGen.size(); c++) {
                        displayGenerator(sortedGen.get(c), c);
                    }
                    sortedGen.clear();
                }
            }catch (Exception p){
                //e.printStackTrace();
                System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT_2);
                displayCriteriaScn.nextLine(); //To prevent error that occur when reading the scanner at.nextInt() above.
                displayCriteria = 0;
            }
        }
    }

    private static void selectGenType(){ //Move into switch statement above
        displayMessage(UIStringTemplate.GEN_SELECT_MENU);
        Scanner genTypeScn = new Scanner(System.in);
        int genType = 0;
        while (genType == 0){
            try { //For error handling
                while(genType == 0 || genType > 4){
                    genType = genTypeScn.nextInt();
                    if(genType == 0 || genType > 4) System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT_2);
                    //Breaks the loop once a valid input is entered.
                }

                switch (genType){
                    case 1: addGenerator(new SolarPvModule());
                        break;
                    case 2: addGenerator(new SolarPvArray());
                        break;
                    case 3: addGenerator(new AirSourcedHeatPump());
                        break;
                    case 4: addGenerator(new GroundSourceHeatPump());
                        break;
                }
            }catch (Exception p){
                //e.printStackTrace();
                System.out.println(UIStringTemplate.INPUT_ERROR_PROMPT_2);
                genTypeScn.nextLine(); //To prevent error that occur when reading the scanner at.nextInt() above.
                genType = 0;
            }
        }
    }

    private static void displayGenerator(Generator theGen, int pos){
        System.out.println("\nGenerator " + (pos+1) + ": " + theGen.getClass().getSimpleName());
        System.out.println("Make: " + theGen.getMake());
        System.out.println("Model: " + theGen.getModel());
        System.out.println("Price: " + theGen.getPrice());

        if(theGen instanceof SolarPvModule){
            System.out.println("Minimum power output (W/hr): " + ((SolarPvModule)theGen).getMinPowerOutput());
            System.out.println("Maximum power output (W/hr): " + ((SolarPvModule)theGen).getMaxPowerOutput());
        }else if(theGen instanceof HeatPump){
            System.out.println("Coefficient of Performance (CoP): " + ((HeatPump)theGen).getCOP());

            if(theGen instanceof AirSourcedHeatPump){
                System.out.println("Minimum operating temperature (C): " + ((AirSourcedHeatPump)theGen).getMinOperatingTemp());
            }else if(theGen instanceof GroundSourceHeatPump){
                System.out.println("Minimum land area (meter squared): " + ((GroundSourceHeatPump)theGen).getMinLandArea());
            }

        }else if(theGen instanceof SolarPvArray){
            System.out.println("Number of Solar Pv modules: " + ((SolarPvArray)theGen).getNumberOfSolarPvModules());
            System.out.println("Total MINIMUM power output (W/hr): " + ((SolarPvArray)theGen).getTotalMinPowerOutput());
            System.out.println("Total MAXIMUM power output (W/hr): " + ((SolarPvArray)theGen).getTotalMaxPowerOutput());
            System.out.println("Summation of total min & max power output (W/hr): " + ((SolarPvArray)theGen).getSumOfMinMaxPowOutput());
        }
    }

    public static void displayMessage(String[] displayedText){
        for(int cur=0; cur < displayedText.length; cur++){
            System.out.println(displayedText[cur]);
        }
    }
}
