package main.com.teamalfa.blindvirologists.agents.virus;

import main.com.teamalfa.blindvirologists.agents.genetic_code.ParalyzeCode;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

public class ParalyzeVirus extends Virus {

    public ParalyzeVirus() {
        priority = 2;
        expiry = duration = 5;
        geneticCode = new ParalyzeCode();
        name = "paralyze virus";

        TurnHandler.getInstance().accept(this);
    }

    /**
     * This method doesnt let the Virologist leave their current Field while being affected by the Virus.
     * @param current The Field the Virologist is standing on.
     * @return The current Field.
     */
    @Override
    public Field affectMovement(Field current) {
        return current;
    }

    /**
     * Tells the Virologist that they can't do any actions, because they are paralyzed.
     * @return true
     */
    @Override
    public boolean affectUsage() {
        return true;
    }

    /**
     * Tells other Virologist that the Virologist is paralyzed so they can be robbed.
     * @return true
     */
    @Override
    public boolean affectRobbability() {
        return true;
    }
}