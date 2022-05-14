package main.com.teamalfa.blindvirologists.city;

import main.com.teamalfa.blindvirologists.agents.GeneticCodeBank;
import main.com.teamalfa.blindvirologists.agents.genetic_code.*;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class City {
    private static final City instance;
    private final ArrayList<Equipment> allEquipment = new ArrayList<>();
    private static final ArrayList<Field> allFields = new ArrayList<>();
    private static final ArrayList<Laboratory> allLaboratories = new ArrayList<>();
    private static final ArrayList<StoreHouse> allStoreHouses = new ArrayList<>();
    private static final ArrayList<SafeHouse> allSafeHouses = new ArrayList<>();
    private static final Random ran = new Random();
    private static final int neighbourCount = 6;

    //Creates one object in the beginning of the game.
    static {
        instance = new City();
    }

    //getter
    public static City getInstance() {
        return instance;
    }


    /**
     * ctr
     */
    private City() {
        //this.GenerateMap();
        allEquipment.add(new Gloves());
        allEquipment.add(new Axe());
        allEquipment.add(new Cloak());
        allEquipment.add(new Bag(50));
    }

    /**
     * Generates the HEXAGON map the players can play on.
     * With random generating random number of fields between 5 & 20
     */
    public void GenerateMap(){
        // create labs based on code number
        int numberOfCodes = GeneticCodeBank.getInstance().getCodes().size();

        for(int i = 0; i < numberOfCodes; i++) {
            GeneticCode currentCode = GeneticCodeBank.getInstance().getCodes().get(i);

            boolean createAgain = true;
            while(createAgain && allLaboratories.size() < numberOfCodes) {
                Laboratory lab = new Laboratory();
                lab.setGeneticCode(currentCode);
                allLaboratories.add(lab);
                createAgain = ran.nextBoolean();
            }

            int numberOfLabs = allLaboratories.size();
            for(int j = 0; j < numberOfLabs; j++)
                allLaboratories.add(new Laboratory());
        }

        // create safe houses based on equipment numbers
        int numberOfEquipments = allEquipment.size();

        for (Equipment equipment : allEquipment) {

            boolean createAgain = true;
            while (createAgain && allSafeHouses.size() < numberOfEquipments) {
                SafeHouse safeHouse = new SafeHouse();
                safeHouse.add(equipment);
                allSafeHouses.add(safeHouse);
                createAgain = ran.nextBoolean();
            }

            int numberOfSafe = allSafeHouses.size();
            for (int j = 0; j < numberOfSafe; j++)
                allSafeHouses.add(new SafeHouse());
        }

        // create store houses with random element numbers
        boolean createAgain = true;
        while(createAgain && allStoreHouses.size() < allLaboratories.size()) {
            ElementBank elements = new ElementBank(ran.nextInt(50), ran.nextInt(50));
            StoreHouse storeHouse = new StoreHouse();
            storeHouse.setElements(elements);
            allStoreHouses.add(storeHouse);
            createAgain = ran.nextBoolean();
        }

        // create fields
        int numberOfFields = allLaboratories.size() + allSafeHouses.size() + allStoreHouses.size();
        for(int i = 0; i < numberOfFields; i++)
            allFields.add(new Field());

        connectFields();
    }

    private static void connectFields(){
        // make a shallow copies of lists
        ArrayList<Field> tmpFields = new ArrayList<>(allFields);
        ArrayList<Field> tmpLabs = new ArrayList<>(allLaboratories);
        ArrayList<Field> tmpStore = new ArrayList<>(allStoreHouses);
        ArrayList<Field> tmpSafe = new ArrayList<>(allSafeHouses);

        for(Field field : allFields) {
            int remaining = neighbourCount;
            int numberOfFields = ran.nextInt(remaining);
            remaining-= numberOfFields;
            int numberOfLabs = ran.nextInt(remaining);
            remaining -= numberOfLabs;
            int numberOfStore = ran.nextInt(remaining);
            remaining -= numberOfStore;
            int numberOfSafe = ran.nextInt(remaining);

            ArrayList<Field> neighbours = new ArrayList<>();

            if(!tmpFields.isEmpty() && numberOfFields != 0)
                tmpFields.remove(0);
            for(int i = 0; i < numberOfFields; i++) {
                if(tmpFields.isEmpty())
                    break;
                neighbours.add(tmpFields.remove(0));
            }

            if(!tmpLabs.isEmpty() && numberOfLabs != 0)
                tmpLabs.remove(0);
            for(int i = 0; i < numberOfLabs; i++) {
                if(tmpLabs.isEmpty())
                    break;
                neighbours.add(tmpLabs.remove(0));
            }

            if(!tmpSafe.isEmpty() && numberOfSafe != 0)
                tmpSafe.remove(0);
            for(int i = 0; i < numberOfSafe; i++) {
                if(tmpSafe.isEmpty())
                    break;
                neighbours.add(tmpSafe.remove(0));
            }

            if(!tmpStore.isEmpty() && numberOfStore != 0)
                tmpStore.remove(0);
            for(int i = 0; i < numberOfStore; i++) {
                if(tmpStore.isEmpty())
                    break;
                neighbours.add(tmpStore.remove(0));
            }

            field.setNeighbours(neighbours);
        }
    }

    public static ArrayList<Laboratory> getAllLaboratories(){
        return allLaboratories;
    }

    public static ArrayList<SafeHouse> getAllSafeHouses(){
        return allSafeHouses;
    }

    public static ArrayList<StoreHouse> getAllStoreHouses(){
        return allStoreHouses;
    }
    public static ArrayList<Field> getAllFields(){ return allFields; }
}
