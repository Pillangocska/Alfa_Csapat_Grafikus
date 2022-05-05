package main.com.teamalfa.blindvirologists;

import jdk.jshell.spi.ExecutionControl;
import main.com.teamalfa.blindvirologists.agents.Agent;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.genetic_code.*;
import main.com.teamalfa.blindvirologists.agents.virus.*;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.ActiveEquipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;
import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    private HashMap<String, Object> objectNameDict;
    private HashMap<String, Integer> IDcounters;

    public Controller() {
        init();
    }

    private void init() {
        objectNameDict = new HashMap<String, Object>();
        IDcounters = new HashMap<String, Integer>();
        IDcounters.put("L", 0);
        IDcounters.put("ST", 0);
        IDcounters.put("SA", 0);
        IDcounters.put("F", 0);
        IDcounters.put("EB", 0);
        IDcounters.put("GC", 0);
        IDcounters.put("A", 0);
        IDcounters.put("V", 0);
        IDcounters.put("E", 0);
    }

    /**
     * Add an object to the object registry with the letter and return it's assigned ID.
     * @param obj The object to be registered.
     * @param IDtext The text part of the ID.
     * @return The ID the object has received. If the Text part was not found in the registry, it is null.
     */
    public String registerObject(Object obj, String IDtext) {
        // get the next ID from the registry, and then increment the type's counter
        Integer idnum = IDcounters.get(IDtext);
        if (idnum == null)
            throw new IllegalArgumentException(); // if the letter is not found in the registry, throw an exception
        idnum++;
        IDcounters.replace(IDtext, idnum);

        // assign ID to the new field and add it to the object hashmap
        String ID = IDtext + idnum;
        objectNameDict.put(ID, obj);
        return ID;
    }

    /**
     * Searches for an object based on an ID in the registry and returns it.
     * @param ID ID of the object to be searched.
     * @return The object, if it is in the registry.
     * @throws IllegalArgumentException Throws an exception if the object is not in the registry.
     */
    private Object checkGetFromRegistry(String ID) throws IllegalArgumentException {
        Object obj = objectNameDict.get(ID);
        // if the get operation returned null, the object doesn't exist. print error and return
        if (obj == null)
            throw new IllegalArgumentException();
        return obj;
    }

    private String checkGetID(Object obj) throws IllegalArgumentException {
        for(var entry : objectNameDict.entrySet())
            if (entry.getValue().equals(obj))
                return entry.getKey();
        // if the object is not in the map
        throw new IllegalArgumentException();
    }

    private void printErrorMessage() {
        System.out.println("Input error! Command is incorect!");
    }

    private String capitalizeString(String inp) {
        return inp.substring(0, 1).toUpperCase() + inp.substring(1);
    }

    /**
     * Creates a new field with the given neighbours and type.
     * @param neighbourIDs The IDs of the fields neighbouring the field to be created. If empty, the field won't have any neighbours.
     * @param type The type of the new field. Possible types: laboratory, storehouse, safehouse. If the parameter neither, the field is going to be a simple field.
     */
    public void createfield(String[] neighbourIDs, String type) {
        try {
            Field newField = null;
            String ID = "";
            String IDtext = "";

            if (type.equals("laboratory")) {
                newField = new Laboratory();
                IDtext = "L";
            }
            if (type.equals("storehouse")) {
                newField = new StoreHouse();
                IDtext = "ST";
            }
            if (type.equals("safehouse")) {
                newField = new SafeHouse();
                IDtext = "SA";
            }
            if (type.equals("")) {
                newField = new Field();
                IDtext = "F";
            }

            // if the type is invalid, print an error message and return
            if (newField == null)
                throw new IllegalArgumentException();

            ArrayList<Field> neighbourFields = new ArrayList<Field>();
            for (var n : neighbourIDs)
                neighbourFields.add((Field) checkGetFromRegistry(n));

            // if the whole operation succeeded without errors, set neighbours
            for (var f: neighbourFields) {
                f.setNeighbour(newField);
                newField.setNeighbour(f);
            }

            ID = registerObject(newField, IDtext);

            // print output
            System.out.println("Field created:");
            System.out.println("ID: " + ID);
            System.out.println("Type: " + (type.equals("") ? "Field" : capitalizeString(type)));
            System.out.println("Neighbours: " + String.join(", ", neighbourIDs));
        }
        catch (Exception e) {
            printErrorMessage();
        }

    }

    /**
     * Creates a new virologist without any equipment, vaccines etc.
     * @param fieldID The field, the virologist is going to be created on.
     */
    public void createvirologist(String fieldID) {
        // TODO backpacket rendezni
        try {
            Virologist v = new Virologist();
            Field f = (Field) checkGetFromRegistry(fieldID);
            // set relations
            f.accept(v);
            v.setField(f);

            String ID = registerObject(v, "V");

            // print output:
            System.out.println("Virologist created:");
            System.out.println("ID: " + ID);
            System.out.println("Field: " + fieldID);
            System.out.println("Backpack: "); //??????????????????????????????????????
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    /**
     * Creates a custom ElementBank and places it in the given storehouse, or in the given virologist's backpack.
     * @param nucleoQuantity The amount of nucleotides in the elementbank.
     * @param aminoQuantity The amount of aminoacids in the elementbank.
     * @param nucleoSize The max amount of nucleotides in the elementbank.
     * @param aminoSize The max amount of amino acids in the elementbank
     * @param virologistID The target virologist's ID. Either this, or storehouseID must be null.
     * @param storehouseID The target storehouse's ID. Either this, or virologistID must be null.
     */
    public void createelements(int nucleoQuantity, int aminoQuantity, int nucleoSize, int aminoSize, String virologistID, String storehouseID) {
        try {
            // creating the elements
            ElementBank e = new ElementBank(nucleoQuantity, aminoQuantity, nucleoSize, aminoSize);

            // setting relations
            // if neither or both a virologist and a storehouse is given, the input is incorrect
            if ((virologistID == null && storehouseID == null) || (virologistID != null && storehouseID != null)) {
                throw new IllegalArgumentException();
            }

            if (virologistID != null) {
                Virologist v = (Virologist) checkGetFromRegistry(virologistID);
                v.getBackpack().add(e);
            }

            if (storehouseID != null) {
                StoreHouse st = (StoreHouse) checkGetFromRegistry(storehouseID);
                st.setElements(e);
            }

            // registering elements
            String ID = registerObject(e, "EB");

            // print output
            System.out.println("Elements created:");
            System.out.println("ID: " + ID);
            System.out.println("Nucleotide:  " + nucleoQuantity);
            System.out.println("AminoAcid: " + aminoQuantity);
            System.out.println("NucleotideSize: " + nucleoSize);
            System.out.println("AminoAcidSize: " + aminoSize);
            System.out.println("Destination: " + (virologistID == null ? storehouseID : virologistID));
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void createequipment(String type, String safehouseID, String virologistID) {
        try {
            Equipment eq = null;
            boolean result = true;
            // create a new piece of equipment
            if (type.equals("cloak")) eq = new Cloak();
            if (type.equals("bag")) eq = new Bag();
            if (type.equals("gloves")) eq = new Gloves();
            if (type.equals("axe")) eq = new Axe();
            // if non of the above:
            if (eq == null) throw new IllegalArgumentException();

            // setting relations
            // if neither or both a virologist and a safehouse is given, the input is incorrect
            if ((virologistID == null && safehouseID == null) || (virologistID != null && safehouseID != null)) {
                throw new IllegalArgumentException();
            }

            if (virologistID != null) {
                Virologist v = (Virologist) checkGetFromRegistry(virologistID);
                result = v.getBackpack().getEquipmentPocket().add(eq);
            }

            if (safehouseID != null) {
                SafeHouse sa = (SafeHouse) checkGetFromRegistry(safehouseID);
                sa.add(eq);
            }

            // registering elements
            String ID = registerObject(eq, "E");

            // printing output
            System.out.println("Equipment created: ");
            System.out.println("ID: " + ID);
            System.out.println("Type: " + type.substring(0, 1).toUpperCase() + type.substring(1));
            System.out.println("Destination: " + (virologistID == null ? safehouseID : virologistID));
            System.out.println("Result: " + (result ? "Successful" : "Failed"));
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void creategeneticcode(String type, String labID, String virologistID) {
        try {
            // create new genetic code
            GeneticCode gc = null;

            if (type.equals("paralyze")) gc = new ParalyzeCode();
            if (type.equals("amnesia")) gc = new AmnesiaCode();
            if (type.equals("dance")) gc = new DanceCode();
            if (type.equals("bear")) gc = new BearCode();
            // if neither
            if (gc == null) throw new IllegalArgumentException();

            // setting relations
            // if neither or both a virologist and a safehouse is given, the input is incorrect
            if ((virologistID == null && labID == null) || (virologistID != null && labID != null)) {
                throw new IllegalArgumentException();
            }

            if (virologistID != null) {
                Virologist v = (Virologist) checkGetFromRegistry(virologistID);
                v.learn(gc);
            }

            if (labID != null) {
                Laboratory lab = (Laboratory) checkGetFromRegistry(labID);
                lab.setGeneticCode(gc);
            }

            // add to registry
            String ID = registerObject(gc, "GC");

            // print output
            System.out.println("GeneticCode created:");
            System.out.println("ID: " + ID);
            System.out.println("Type: " + type.substring(0, 1).toUpperCase() + type.substring(1));
            System.out.println("Destination: " + (virologistID == null ? labID : virologistID));
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void createagent(String type, String geneticCodeType, String virologistID) {
        try {
            // create new agent
            Agent agent = null;

            if (type.equals("vaccine")) {
                if (geneticCodeType.equals("paralyze")) agent = new Vaccine(new ParalyzeCode());
                if (geneticCodeType.equals("amnesia")) agent = new Vaccine(new AmnesiaCode());
                if (geneticCodeType.equals("dance")) agent = new Vaccine(new DanceCode());
                if (geneticCodeType.equals("bear")) agent = new Vaccine(new BearCode());
                if (agent == null) throw new IllegalArgumentException();
            }

            if (type.equals("virus")) {
                if (geneticCodeType.equals("paralyze")) agent = new ParalyzeVirus();
                if (geneticCodeType.equals("amnesia")) agent = new AmnesiaVirus();
                if (geneticCodeType.equals("dance")) agent = new DanceVirus();
                if (geneticCodeType.equals("bear")) agent = new BearVirus();
                if (agent == null) throw new IllegalArgumentException();
            }
            if (agent == null) throw new IllegalArgumentException();

            // setting relations
            Virologist v = (Virologist) checkGetFromRegistry(virologistID);
            boolean result = v.getBackpack().getAgentPocket().addAgent(agent);

            // add to registry
            String ID = registerObject(agent, "A");

            // print output
            System.out.println("Agent created:");
            System.out.println("ID: " + ID);
            System.out.println("Type: " + type);
            System.out.println("GeneticCode: " + capitalizeString(geneticCodeType));
            System.out.println("Virologist: " + virologistID);
            System.out.println("Result: " + (result ? "Successful" : "Failed"));
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void move(String fieldID) {
        try {
            Virologist currentVirologist = TurnHandler.getInstance().getActiveVirologist();
            String virologistID = checkGetID(currentVirologist);
            if (currentVirologist == null) throw new IllegalStateException();
            Field target = (Field) checkGetFromRegistry(fieldID);
            currentVirologist.move(target);

            System.out.println("Virologist moved:");
            System.out.println("Virologist: " + virologistID);
            System.out.println("Destination: " + fieldID);
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void pickupequipment(String equipmentID) {
        try {
            Equipment eq = (Equipment) checkGetFromRegistry(equipmentID);
            Virologist currentVirologist = TurnHandler.getInstance().getActiveVirologist();
            String virologistID = checkGetID(currentVirologist);
            String fieldID = checkGetID(currentVirologist.getField());

            //check if the equipment is on the field the virologist stands on
            if(!((SafeHouse)(currentVirologist.getField())).getEquipments().contains(eq)) throw new IllegalArgumentException();

            boolean result = currentVirologist.getBackpack().add(eq);

            // print output
            System.out.println("Equipment added to inventory:");
            System.out.println("Virologist: " + virologistID);
            System.out.println("Equipment: " + equipmentID);
            System.out.println("Field: " + fieldID);
            System.out.println("Result: " + (result ? "Successful" : "Failed"));
        }
        catch (Exception e) {
            printErrorMessage();
        }
    }

    public void dropequipment(String equipmentID) {
        try {
            Equipment eq = (Equipment) checkGetFromRegistry(equipmentID);
            Virologist currentVirologist = TurnHandler.getInstance().getActiveVirologist();
            String virologistID = checkGetID(currentVirologist);
            String fieldID = checkGetID(currentVirologist.getField());
            boolean result = currentVirologist.toss(eq);

            // print output
            System.out.println("Equipment dropped:");
            System.out.println("Virologist: " + virologistID);
            System.out.println("Equipment: " + equipmentID);
            System.out.println("Field: " + fieldID);

            if (!result) {
                System.out.println("\nDrop was unsuccessful!");
            }
        }
        catch (Exception e) {
            printErrorMessage();
        }
    }

    public void learngeneticcode() {
        try {
            Virologist currentVirologist = TurnHandler.getInstance().getActiveVirologist();
            String virologistID = checkGetID(currentVirologist);
            String labID = checkGetID(currentVirologist.getField());
            String GCID = checkGetID(((Laboratory)currentVirologist.getField()).getGeneticCode());
            boolean result = currentVirologist.learn(((Laboratory)currentVirologist.getField()).getGeneticCode());

            // print output
            System.out.println("Genetic code learned:");
            System.out.println("Virologist: " + virologistID);
            System.out.println("Laboratory: " + labID);
            System.out.println("GeneticCode: " + GCID);
            System.out.println("Result: " + (result ? "Successful" : "Failed"));
        } catch (Exception e) {
            printErrorMessage();
        }
    }

    public void useequipment(String equipmentID, String targetID, String virusID) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Ez még nincs kész!");
        // HOGY MŰKÖDIK A USE????
        /*try {
            ActiveEquipment activeEquipment = (ActiveEquipment) checkGetFromRegistry(equipmentID);
            Virologist target = (Virologist) checkGetFromRegistry(targetID);

            if (activeEquipment instanceof Gloves) {
                Gloves gloves = (Gloves) activeEquipment;
                Virus virus = (Virus) checkGetFromRegistry(virusID);
            }
        } catch (Exception e) {
            printErrorMessage();
        }*/
    }

    public void craftagent(String agentType, String geneticCodeType) {
        System.out.println(agentType);
        System.out.println(geneticCodeType);
    }

    public void useagent(String agentID, String virologistID) {
        System.out.println(agentID);
        System.out.println(virologistID);
    }

    public void pickupmaterial() {
        System.out.println();
    }

    public void rob(String virologistID, String[] equipmentIDs, int nucleoQ, int aminoQ) {
        System.out.println(virologistID);
        for (var v : equipmentIDs) System.out.println(v);
        System.out.println(nucleoQ);
        System.out.println(aminoQ);
    }

    public void startturn(String virologistID) {
        try {
            TurnHandler.getInstance().setActiveVirologist((Virologist) checkGetFromRegistry(virologistID));
            System.out.println(virologistID + "'s turn started.");
        }
        catch (Exception e) {
            printErrorMessage();
        }
    }

    public void status(String[] IDs) {
        Object[] objects = objectNameDict.values().toArray();
        for(var entry : objectNameDict.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Field) {
                Field field = (Field) value;
                System.out.println("Field:");
                System.out.println("ID: " + key);

                ArrayList<String> neighbourIDs = new ArrayList<String>();
                for (var n : field.getNeighbours()) {
                    neighbourIDs.add(checkGetID(n));
                }
                System.out.println("Neighbours: " + String.join(", ", (String[]) neighbourIDs.toArray()));

                ArrayList<String> virologistIDs = new ArrayList<String>();
                for (var v : field.getVirologists())
                    virologistIDs.add(checkGetID(v));
                System.out.println("Virologists: " + String.join(", ", (String[]) virologistIDs.toArray()));

                System.out.println("GeneticCodes: " + (field instanceof Laboratory ? checkGetID(((Laboratory) field).getGeneticCode()) : "null"));
                //System.out.println("Equipments: " + (field instanceof SafeHouse ? (((SafeHouse) field).getEquipments()).forEach(eq -> {checkGetID(eq);}) : "null"));


            }
        }
    }

    public void toggle(String equipmentID) {
        System.out.println(equipmentID);
    }

    /**
     * Read a set of commands (a testscript) from a file, run it and print the results to the console.
     * @param path The relative path from the testscript from the project's root folder.
     * */
    public void runscript(String path) {
        String script = "";
        String fullPath = System.getProperty("user.dir") + "\\" + path;
        try {
            script = new String(Files.readAllBytes(Paths.get(fullPath)));
        } catch (IOException e) {
            System.out.println("An error occurred while reading a testscript from " + fullPath + "!");
        }

        String[] lines = script.split("\n");
        for(var l : lines)
            if(!l.equals(""))
                runCommand(l);
    }

    public void search() {
        System.out.println();
    }

    // EZT NEM ÉRTEM, HOGY HOGY KÉNE MŰKÖDNIE! todo
    public void setrandom(String outcome, int choiceType) {
        System.out.println(outcome);
        System.out.println(choiceType);
    }

    /**
     * Run a command, if it is syntactically correct and print an error to the console it is not.
     * @param command The command to be run.
     * */
    public void runCommand(String command) {
        try {
            command = command.toLowerCase();
            String[] parts = command.split(" ");
            int len = parts.length;


            if (parts[0].equals("createfield")) {
                String fieldType = "";
                String[] neighbours = {};

                if (len >= 2) {
                    if (parts[len - 2].equals("-t")) {
                        fieldType = parts[len - 1];
                        neighbours = Arrays.copyOfRange(parts, 1, len - 2);
                    } else {
                        neighbours = Arrays.copyOfRange(parts, 1, len);
                    }
                }

                for (int i = 0; i < neighbours.length; i++)
                    neighbours[i] = neighbours[i].toUpperCase();
                createfield(neighbours, fieldType);
                return;
            }

            if (parts[0].equals("createvirologist")) {
                createvirologist(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("createelements")) {
                int nucleoQ = 0, aminoQ = 0, nucleoS = 0, aminoS = 0;
                String virologistID = null;
                String storehouseID = null;

                // first parameter
                if (parts[1].charAt(0) != 'n' || !parts[1].contains("a"))
                    throw new IllegalArgumentException();
                String[] firstParamNumbers = parts[1].substring(1).split("a");
                nucleoQ = Integer.parseInt(firstParamNumbers[0]);
                aminoQ = Integer.parseInt(firstParamNumbers[1]);

                // second parameter
                if (!parts[2].startsWith("ns") || !parts[2].contains("as"))
                    throw new IllegalArgumentException();
                String[] secondParamNumbers = parts[2].substring(2).split("as");
                nucleoS = Integer.parseInt(secondParamNumbers[0]);
                aminoS = Integer.parseInt(secondParamNumbers[1]);

                // third and fourth parameters
                if (parts[3].equals("-s")) {
                    storehouseID = parts[4].toUpperCase();
                } else {
                    if (parts[3].equals("-v"))
                        virologistID = parts[4].toUpperCase();
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                createelements(nucleoQ, aminoQ, nucleoS, aminoS, virologistID, storehouseID);
                return;
            }

            if (parts[0].equals("createequipment")) {
                String type = parts[1];
                String safehouseID = null;
                String virologistID = null;

                // second and third parameters
                if (parts[2].equals("-s")) {
                    safehouseID = parts[3].toUpperCase();
                } else {
                    if (parts[2].equals("-v"))
                        virologistID = parts[3].toUpperCase();
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                createequipment(type, safehouseID, virologistID);

                return;
            }

            if (parts[0].equals("creategeneticcode")) {
                String labID = null;
                String virologistID = null;

                // second and third parameters
                if (parts[2].equals("-l")) {
                    labID = parts[3].toUpperCase();
                } else {
                    if (parts[2].equals("-v"))
                        virologistID = parts[3].toUpperCase();
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                creategeneticcode(parts[1], labID, virologistID);

                return;
            }

            if (parts[0].equals("createagent")) {
                createagent(parts[1], parts[2], parts[3].toUpperCase());
                return;
            }

            if (parts[0].equals("move")) {
                move(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("pickupequipment")) {
                pickupequipment(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("dropequipment")) {
                dropequipment(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("learngeneticcode")) {
                learngeneticcode();
                return;
            }

            if (parts[0].equals("useequipment")) {
                if (len == 3)
                    useequipment(parts[1].toUpperCase(), parts[2].toUpperCase(), null);
                else
                    useequipment(parts[1].toUpperCase(), parts[2].toUpperCase(), parts[3].toUpperCase());
                return;
            }

            if (parts[0].equals("craftagent")) {
                craftagent(parts[1], parts[2]);
                return;
            }

            if (parts[0].equals("useagent")) {
                useagent(parts[1].toUpperCase(), parts[2].toUpperCase());
                return;
            }

            if (parts[0].equals("pickupmaterial")) {
                pickupmaterial();
                return;
            }

            if (parts[0].equals("rob")) {
                int nucleoQ = 0, aminoQ = 0;
                ArrayList<String> equipmentIDs = new ArrayList<String>();
                int ptr = 2;
                if (parts[2].equals("-eq")) {
                    ptr++;
                    while (!parts[ptr].equals("-el")) {
                        equipmentIDs.add(parts[ptr].toUpperCase());
                        if (++ptr >= len) {
                            rob(parts[1].toUpperCase(), (String[]) equipmentIDs.toArray(), nucleoQ, aminoQ);
                            return;
                        }
                    }
                }
                ptr++;
                if (parts[ptr].charAt(0) != 'n' || !parts[ptr].contains("a"))
                    throw new IllegalArgumentException();
                String[] elementNumbers = parts[ptr].substring(1).split("a");
                nucleoQ = Integer.parseInt(elementNumbers[0]);
                aminoQ = Integer.parseInt(elementNumbers[1]);

                rob(parts[1].toUpperCase(), (String[]) equipmentIDs.toArray(), nucleoQ, aminoQ);
                return;
            }

            if (parts[0].equals("startturn")) {
                startturn(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("status")) {
                if (len == 1)
                    status(new String[] {});
                else {
                    String[] IDs = Arrays.copyOfRange(parts, 1, len);
                    for (var id : IDs)
                        id = id.toUpperCase();
                    status(IDs);
                }
                return;
            }

            if (parts[0].equals("toggle")) {
                toggle(parts[1].toUpperCase());
                return;
            }

            if (parts[0].equals("runscript")) {
                runscript(parts[1]);
                return;
            }

            if (parts[0].equals("search")) {
                search();
                return;
            }

            if (parts[0].equals("setrandom")) {
                if (!(parts[1].equals("yes") || parts[1].equals("no") || parts[1].equals("random")))
                    throw new IllegalArgumentException();
                setrandom(parts[1], Integer.parseInt(parts[2]));
                return;
            }

            if (parts[0].equals("init"))
                init();

            // if we have gotten here without matching any command, then the input is invalid!
            throw new IllegalArgumentException();
        } catch(Exception e) {
            // if an exception occurred during handling input, print an error message
            printErrorMessage();
        }
    }

    /**
     * This method is used to run predefined tests.
     * */
    public void runTest() {
        System.out.println("Choose a test to run! Enter a number between 0 and 38!");
        System.out.println(
                "0: Exit\n" +
                        "1: Virologist steps on a new field, and searches for another virologist and finds one\n" +
                        "2: Virologist steps on a new field, and searches for other virologist but can’t find any\n" +
                        "3: Virologist searches in a laboratory that contains dance genetic code\n" +
                        "4: Virologist searches in a laboratory that contains an already learnt dance genetic code\n" +
                        "5: Virologist searches in a laboratory that doesn’t contain genetic code\n" +
                        "6: Virologist steps into infected laboratory and gets infected by BearVirus\n" +
                        "7: Virologist steps into infected laboratory but wears a cloak and it doesn’t block the infection\n" +
                        "8: Virologist steps into infected laboratory but they are vaccinated against BearVirus\n" +
                        "9: Virologist searches in a storehouse and collects elements\n" +
                        "10: Virologist searches in an empty storehouse\n" +
                        "11: Virologist with a full bag searches in a storehouse\n" +
                        "12: Virologist searches a safehouse that contains an axe and picks it up\n" +
                        "13: Virologist searches an empty safehouse\n" +
                        "14: Virologist moves\n" +
                        "15: Virologist moves while affected by DanceVirus\n" +
                        "16: Virologist moves while affected by ParalyzeVirus\n" +
                        "17: Virologist moves while affected by AmnesiaVirus\n" +
                        "18: Virologist uses ParalyzeVirus on another Virologist, who’s not vaccinated and without equipment\n" +
                        "19: Virologist uses AmnesiaVirus on another Virologist who’s not vaccinated but wears cape, the cape blocks the virus\n" +
                        "20: Virologist uses AmnesiaVirus on another Virologist who’s not vaccinated but wears cape, the cape doesn’t block the virus\n" +
                        "21: VirologistA uses DanceVirus on VirologistB who’s not vaccinated but wears Gloves. Virologist B applies DanceVirus with gloves\n" +
                        "22: Virologist wants to create a ParalyzeVirus, but doesn't have enough elements\n" +
                        "23: Virologist creates an AmnesiaVaccine and uses it on itself\n" +
                        "24: Virologist starts to wear a bag\n" +
                        "25: Virologist takes off a bag\n" +
                        "26: Virologist can’t take off bag\n" +
                        "27: Virologist can’t wear an axe, because worn equipments are full\n" +
                        "28: Virologist uses glove for the third time\n" +
                        "29: Virologist uses a sharp axe on another virologist\n" +
                        "30: Virologist uses blunt axe on another virologist\n" +
                        "31: Virologist wants to toggle bag, but the Virologist isn't in a Safehouse\n" +
                        "32: Virologist tosses a cloak from the backpack to a Safehouse\n" +
                        "33: Virologist tosses a cloak from the backpack to a Field\n" +
                        "34: Virologist robs another Virologist\n" +
                        "35: Virologist can’t rob an enemy because they are not paralyzed\n" +
                        "36: VirologistA tries to use DanceVirus on VirologistB but VirologistA is under the effect of Paralyze Virus\n" +
                        "37: VirologistA infects VirologistB with BearVirus. The VirologistB is not vaccinated, and doesn’t wear any equipment. VirologistB turns to bear\n" +
                        "38: VirologistA infects VirologistB with BearVirus. The VirologistB doesn’t wear any equipment, but is vaccinated against bearvirus.");

        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = inputScanner.nextLine();
                int choice = Integer.parseInt(userInput);

                if (choice == 0)
                    return;

                if (choice >= 1 && choice <= 38) {
                    // if the user's choice is valid read the test script from the corresponding file
                    runscript("rcs\\testscripts\\test" + userInput + ".txt");
                }
                else {
                    // if the user's choice is invalid start the read process all over
                    throw new NumberFormatException("Invalid input! Enter a number between 0 and 38!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect number format! Enter a number between 0 and 38!");
            }
        }
    }

    /**
     * This method reads commands one at the time from the console and executes them.
     * */
    public void playGame() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String userInput = inputScanner.nextLine();
            userInput = userInput.toLowerCase();
            if(userInput.equals("quit") || userInput.equals("q"))
                return;
            else
                runCommand(userInput);
        }
    }
}
