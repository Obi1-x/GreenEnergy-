package com.greenenergy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GeneratorData {
    private static String fileName = "ListOfGenerators.ser";

    private static void serializeData(ArrayList<Generator> _generatorsList) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(_generatorsList);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("SERI NOT FOUND ERROR");
        } catch (IOException ef) {
            ef.printStackTrace();
            System.out.println("SERI IO ERROR");
        }
    }

    private static ArrayList<Generator> deserializeData() {
        ArrayList<Generator> savedGenList = new ArrayList<Generator>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            savedGenList = (ArrayList<Generator>) inputStream.readObject();
            inputStream.close();
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("New generator list created.");
        } catch (ClassNotFoundException nfe) {
            //nfe.printStackTrace();
            System.out.println("New generator list created.");
        }
        return savedGenList;
    }


    public static void updateGeneratorList(ArrayList<Generator> generatorsList){
        serializeData(generatorsList);
    }

    public static ArrayList<Generator> readGeneratorList(){
        ArrayList<Generator> generatorsList= deserializeData();
        if(generatorsList == null) generatorsList = new ArrayList<Generator>(); // Unlimited generators can be added.
        return generatorsList;
    }
}
