package main.com.teamalfa.blindvirologists.city;

import main.com.teamalfa.blindvirologists.agents.genetic_code.AmnesiaCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.BearCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.DanceCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.ParalyzeCode;
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

import java.util.ArrayList;
import java.util.Random;

public class City {
    private static final City instance;
    private final ArrayList<Equipment> allEquipment = new ArrayList<>();
    private static final ArrayList<Field> allFields = new ArrayList<>();
    private static final ArrayList<Laboratory> allLaboratories = new ArrayList<>();
    private static final ArrayList<StoreHouse> allStoreHouses = new ArrayList<>();
    private static final ArrayList<SafeHouse> allSafeHouses = new ArrayList<>();

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
    public void GenerateMap() {
        Random random = new Random();
        int numberOfFields = random.nextInt(20-5+1)+5;
        int numberOfLabs = random.nextInt(12-4+1)+4;
        int numberOfStoreH = random.nextInt(15-5+1)+5;
        int numberOfSafeH = random.nextInt(15-2+1)+2;
        //Generating field numbers
        for(int i = 0; i < numberOfFields; i++){
            allFields.add(new Field()); //Field created
        }
        for(int i = 0; i < numberOfLabs; i++){
            //Generating random genetic codes for lab
            Laboratory laboratory = new Laboratory();
            int y = random.nextInt(4)+1;
            switch (y) {
                case 1:
                    ParalyzeCode paralyzeCode = new ParalyzeCode();
                    laboratory.setGeneticCode(paralyzeCode);
                    break;
                case 2:
                    AmnesiaCode amnesiaCode = new AmnesiaCode();
                    laboratory.setGeneticCode(amnesiaCode);
                    break;
                case 3:
                    BearCode bearCode = new BearCode();
                    laboratory.setGeneticCode(bearCode);
                    break;
                case 4:
                    DanceCode danceCode = new DanceCode();
                    laboratory.setGeneticCode(danceCode);
                default:
                    break;
            }
            allLaboratories.add(laboratory); //Laboratory created
        }
        for(int i = 0; i < numberOfStoreH; i++){
            //Generating random elements for storehouse
            StoreHouse storeHouse = new StoreHouse(random.nextInt(20)+1,random.nextInt(20)+1);
            allStoreHouses.add(storeHouse); //Storehouse created
        }
        for(int i = 0; i < numberOfSafeH; i++){
            //Generating random equipments for safehouses
            SafeHouse safeHouse = new SafeHouse();
            int x = random.nextInt(4-1+1)+1;
            switch (x) {
                case 1:
                    safeHouse.add(new Gloves());
                    break;
                case 2:
                    safeHouse.add(new Bag(50));
                case 3:
                    safeHouse.add(new Cloak());
                case 4:
                    safeHouse.add(new Axe());
                default:
                    break;
            }
            allSafeHouses.add(safeHouse); //Safe-house created
        }
        int hexConst = 6;
        //Setting up neighbours for fields first
        for(int i = 0; i < numberOfFields && allFields.get(i).getNeighbours().size() != hexConst; i++){
            //One field gets 1 to 2 normal field neighbours
            int numberOfFieldNeighbours = random.nextInt(2-1+1)+1;
            for(int j = 0; j < numberOfFieldNeighbours; j++) {
                int addIndex = random.nextInt(numberOfFields);
                if(!(allFields.get(i).getNeighbours().contains(allFields.get(addIndex)))
                        && i != addIndex
                        && allFields.get(i).getNeighbours().size() < hexConst
                        && allFields.get(addIndex).getNeighbours().size() < hexConst) {
                    allFields.get(i).setNeighbour(allFields.get(addIndex));
                    allFields.get(addIndex).setNeighbour(allFields.get(i));
                }
            }
            //0 to 2 Laboratories
            int numberOfLabNeighbours = random.nextInt(1+1);
            for(int j = 0; j < numberOfLabNeighbours; j++) {
                int addIndex = random.nextInt(numberOfLabs);
                if(!(allFields.get(i).getNeighbours().contains(allLaboratories.get(addIndex)))
                        && allFields.get(i).getNeighbours().size() < hexConst
                        && allLaboratories.get(addIndex).getNeighbours().size() < hexConst) {
                    allFields.get(i).setNeighbour(allLaboratories.get(addIndex));
                    allLaboratories.get(addIndex).setNeighbour(allFields.get(i));
                }
            }
            //0 to 2 SafeHouses
            int numberOfSafeHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfSafeHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfSafeH);
                if(!(allFields.get(i).getNeighbours().contains(allSafeHouses.get(addIndex)))
                        && allFields.get(i).getNeighbours().size() < hexConst
                        && allSafeHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allFields.get(i).setNeighbour(allSafeHouses.get(addIndex));
                    allSafeHouses.get(addIndex).setNeighbour(allFields.get(i));
                }
            }
            //0 to 2 StoreHouses
            int numberOfStoreHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfStoreHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfStoreH);
                if(!(allFields.get(i).getNeighbours().contains(allStoreHouses.get(addIndex)))
                        && allFields.get(i).getNeighbours().size() < hexConst
                        && allStoreHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allFields.get(i).setNeighbour(allStoreHouses.get(1));
                    allStoreHouses.get(1).setNeighbour(allFields.get(i));
                }
            }
        }
        //Setting up neighbours for laboratories
        for(int i = 0; i < numberOfLabs && allLaboratories.get(i).getNeighbours().size() != hexConst; i++){
            //1 to 2 field neighbours
            int numberOfFieldNeighbours = random.nextInt(2-1+1)+1;
            for(int j = 0; j < numberOfFieldNeighbours; j++) {
                int addIndex = random.nextInt(numberOfFields);
                if(!(allLaboratories.get(i).getNeighbours().contains(allFields.get(addIndex)))
                        && allLaboratories.get(i).getNeighbours().size() < hexConst
                        && allFields.get(addIndex).getNeighbours().size() < hexConst) {
                    allLaboratories.get(i).setNeighbour(allFields.get(addIndex));
                    allFields.get(addIndex).setNeighbour(allLaboratories.get(i));
                }
            }
            //0 to 2 Laboratories
            int numberOfLabNeighbours = random.nextInt(1+1);
            for(int j = 0; j < numberOfLabNeighbours; j++) {
                int addIndex = random.nextInt(numberOfLabs);
                if(!(allLaboratories.get(i).getNeighbours().contains(allLaboratories.get(addIndex)))
                        && i != addIndex
                        && allLaboratories.get(i).getNeighbours().size() < hexConst
                        && allLaboratories.get(addIndex).getNeighbours().size() < hexConst) {
                    allLaboratories.get(i).setNeighbour(allLaboratories.get(addIndex));
                    allLaboratories.get(addIndex).setNeighbour(allLaboratories.get(i));
                }
            }
            //0 to 2 SafeHouses
            int numberOfSafeHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfSafeHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfSafeH);
                if(!(allLaboratories.get(i).getNeighbours().contains(allSafeHouses.get(addIndex)))
                        && allLaboratories.get(i).getNeighbours().size() < hexConst
                        && allSafeHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allLaboratories.get(i).setNeighbour(allSafeHouses.get(addIndex));
                    allSafeHouses.get(addIndex).setNeighbour(allLaboratories.get(i));
                }
            }
            //0 to 3 StoreHouses
            int numberOfStoreHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfStoreHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfStoreH);
                if(!(allLaboratories.get(i).getNeighbours().contains(allStoreHouses.get(addIndex)))
                        && allLaboratories.get(i).getNeighbours().size() < hexConst
                        && allStoreHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allLaboratories.get(i).setNeighbour(allStoreHouses.get(addIndex));
                    allStoreHouses.get(addIndex).setNeighbour(allLaboratories.get(i));
                }
            }
        }
        //Setting up neighbours for safehouses
        for(int i = 0; i < numberOfSafeH && allSafeHouses.get(i).getNeighbours().size() != hexConst; i++){
            //1 to 5 field neighbours
            int numberOfFieldNeighbours = random.nextInt(2-1+1)+1;
            for(int j = 0; j < numberOfFieldNeighbours; j++) {
                int addIndex = random.nextInt(numberOfFields);
                if(!(allSafeHouses.get(i).getNeighbours().contains(allFields.get(addIndex)))
                        && allSafeHouses.get(i).getNeighbours().size() < hexConst
                        && allFields.get(addIndex).getNeighbours().size() < hexConst) {
                    allSafeHouses.get(i).setNeighbour(allFields.get(addIndex));
                    allFields.get(addIndex).setNeighbour(allSafeHouses.get(i));
                }
            }
            //0 to 2 Laboratories
            int numberOfLabNeighbours = random.nextInt(1+1);
            for(int j = 0; j < numberOfLabNeighbours; j++) {
                int addIndex = random.nextInt(numberOfLabs);
                if(!(allSafeHouses.get(i).getNeighbours().contains(allLaboratories.get(addIndex)))
                        && allSafeHouses.get(i).getNeighbours().size() < hexConst
                        && allLaboratories.get(addIndex).getNeighbours().size() < hexConst) {
                    allSafeHouses.get(i).setNeighbour(allLaboratories.get(addIndex));
                    allLaboratories.get(addIndex).setNeighbour(allSafeHouses.get(i));
                }
            }
            //0 to 2 SafeHouses
            int numberOfSafeHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfSafeHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfSafeH);
                if(!(allSafeHouses.get(i).getNeighbours().contains(allSafeHouses.get(addIndex)))
                        && i != addIndex
                        && allSafeHouses.get(i).getNeighbours().size() < hexConst
                        && allSafeHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allSafeHouses.get(i).setNeighbour(allSafeHouses.get(addIndex));
                    allSafeHouses.get(addIndex).setNeighbour(allSafeHouses.get(i));
                }
            }
            //0 to 3 StoreHouses
            int numberOfStoreHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfStoreHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfStoreH);
                if(!(allSafeHouses.get(i).getNeighbours().contains(allStoreHouses.get(addIndex)))
                        && allSafeHouses.get(i).getNeighbours().size() < hexConst
                        && allStoreHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allSafeHouses.get(i).setNeighbour(allStoreHouses.get(addIndex));
                    allStoreHouses.get(addIndex).setNeighbour(allSafeHouses.get(i));
                }
            }
        }
        //Setting up neighbours for storehouses
        for(int i = 0; i < numberOfStoreH && allStoreHouses.get(i).getNeighbours().size() != hexConst; i++){
            //1 to 5 field neighbours
            int numberOfFieldNeighbours = random.nextInt(2-1+1)+1;
            for(int j = 0; j < numberOfFieldNeighbours; j++) {
                int addIndex = random.nextInt(numberOfFields);
                if(!(allStoreHouses.get(i).getNeighbours().contains(allFields.get(addIndex)))
                        && allStoreHouses.get(i).getNeighbours().size() < hexConst
                        && allFields.get(addIndex).getNeighbours().size() < hexConst) {
                    allStoreHouses.get(i).setNeighbour(allFields.get(addIndex));
                    allFields.get(addIndex).setNeighbour(allStoreHouses.get(i));
                }
            }
            //0 to 2 Laboratories
            int numberOfLabNeighbours = random.nextInt(1+1);
            for(int j = 0; j < numberOfLabNeighbours; j++) {
                int addIndex = random.nextInt(numberOfLabs);
                if(!(allStoreHouses.get(i).getNeighbours().contains(allLaboratories.get(addIndex)))
                        && allStoreHouses.get(i).getNeighbours().size() < hexConst
                        && allLaboratories.get(addIndex).getNeighbours().size() < hexConst) {
                    allStoreHouses.get(i).setNeighbour(allLaboratories.get(addIndex));
                    allLaboratories.get(addIndex).setNeighbour(allStoreHouses.get(i));
                }
            }
            //0 to 2 SafeHouses
            int numberOfSafeHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfSafeHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfSafeH);
                if(!(allStoreHouses.get(i).getNeighbours().contains(allSafeHouses.get(addIndex)))
                        && allStoreHouses.get(i).getNeighbours().size() < hexConst
                        && allSafeHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allStoreHouses.get(i).setNeighbour(allSafeHouses.get(addIndex));
                    allSafeHouses.get(addIndex).setNeighbour(allStoreHouses.get(i));
                }
            }
            //0 to 2 StoreHouses
            int numberOfStoreHNeighbours = random.nextInt(2+1);
            for(int j = 0; j < numberOfStoreHNeighbours; j++) {
                int addIndex = random.nextInt(numberOfStoreH);
                if(!(allStoreHouses.get(i).getNeighbours().contains(allStoreHouses.get(addIndex)))
                        && i != addIndex
                        && allStoreHouses.get(i).getNeighbours().size() < hexConst
                        && allStoreHouses.get(addIndex).getNeighbours().size() < hexConst) {
                    allStoreHouses.get(i).setNeighbour(allStoreHouses.get(addIndex));
                    allStoreHouses.get(addIndex).setNeighbour(allStoreHouses.get(i));
                }
            }
        }
        //Let's have a look at the result
        for(int i = 0 ; i < numberOfFields-1 ; i++) {
            System.out.println("------------------------------------");
            System.out.println(allFields.get(i).toString()+"Neighbours:");
            for (int j = 0 ; j < allFields.get(i).getNeighbours().size()-1; j++) {
                System.out.println(allFields.get(i).getNeighbours().get(j).toString());
            }
        }
        for(int i = 0 ; i < numberOfLabs-1 ; i++) {
            System.out.println("------------------------------------");
            System.out.println(allLaboratories.get(i).toString()+"Neighbours:");
            for (int j = 0 ; j < allLaboratories.get(i).getNeighbours().size()-1; j++) {
                System.out.println(allLaboratories.get(i).getNeighbours().get(j).toString());
            }
        }
        for(int i = 0 ; i < numberOfStoreH-1 ; i++) {
            System.out.println("------------------------------------");
            System.out.println(allStoreHouses.get(i).toString()+"Neighbours:");
            for (int j = 0 ; j < allStoreHouses.get(i).getNeighbours().size()-1; j++) {
                System.out.println(allStoreHouses.get(i).getNeighbours().get(j).toString());
            }
        }
        for(int i = 0 ; i < numberOfSafeH-1 ; i++) {
            System.out.println("------------------------------------");
            System.out.println(allSafeHouses.get(i).toString()+"Neighbours:");
            for (int j = 0 ; j < allSafeHouses.get(i).getNeighbours().size()-1; j++) {
                System.out.println(allSafeHouses.get(i).getNeighbours().get(j).toString());
            }
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
