package main.com.teamalfa.blindvirologists.agents.genetic_code;

import main.com.teamalfa.blindvirologists.Controller;
import main.com.teamalfa.blindvirologists.ControllerHelper;
import main.com.teamalfa.blindvirologists.ControllerRefactor;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.com.teamalfa.blindvirologists.virologist.Virologist;
import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

public class BearCode extends GeneticCode {

    public BearCode(){
        this.type = "bear";
    }

    /**
     * This method creates a Virus with the bear geneticcode.
     *
     * @return The BearVirus that has been created.
     */
    @Override
    public BearVirus createVirus(ElementBank elementBank) {
        // create bear virus
        BearVirus bv = new BearVirus();
        // handle cost
        ElementBank cost = bv.getCost();
        if(elementBank.remove(cost)) {
            return bv;
        }
        // if the virologist didn't have enough material
        return null;
    }

    /**
     * This method create a Vaccine with the bear geneticcode.
     *
     * @return The Vaccine that has been created.
     */
    @Override
    public Vaccine createVaccine(ElementBank elementBank) {
        // create bear vaccine
        Vaccine bv = new Vaccine(this);
        // handle cost
        ElementBank cost = bv.getCost();
        if(elementBank.remove(cost)) {
            return bv;
        }
        // if the virologist didn't have enough material
        return null;
    }
    /**
     * This method creates a BearVirus and infects the virologist
     * given as parameter
     */
    @Override
    public void autoInfect(Virologist virologist) {
        // create bear virus
        BearVirus bearVirus = new BearVirus();
        // infect the virologist
        bearVirus.apply(virologist);
    }
}
