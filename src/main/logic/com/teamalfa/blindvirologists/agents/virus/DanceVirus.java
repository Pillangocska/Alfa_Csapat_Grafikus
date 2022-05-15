package main.logic.com.teamalfa.blindvirologists.agents.virus;

import main.logic.com.teamalfa.blindvirologists.agents.genetic_code.DanceCode;
import main.logic.com.teamalfa.blindvirologists.city.fields.Field;
import main.logic.com.teamalfa.blindvirologists.random.MyRandom;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import java.util.ArrayList;
import java.util.Random;

public class DanceVirus extends Virus {

    public MyRandom random;

    public DanceVirus(){
        geneticCode = new DanceCode();
        name = "dance virus";
        priority = 3;
        expiry = duration = 5;

        cost.setNucleotide(15);
        cost.setAminoAcid(15);

        TurnHandler.getInstance().accept(this);
    }

    /**
     * This method makes the Virologist move to a random Field instead of the chosen one.
     * There's a slight chance the chosen Field and the random Field will be the same.
     * @param current The Field the Virologist is standing on.
     * @return The chosen Field.
     */
    @Override
    public Field affectMovement(Field current) {
        return pickRandom(current.getNeighbours());
    }

    private Field pickRandom(ArrayList<Field> neighbours){
        int size = neighbours.size() - 1;
        int idx = size != 1 ? new Random().nextInt(size) : 0;
        return neighbours.get(idx);
    }
}