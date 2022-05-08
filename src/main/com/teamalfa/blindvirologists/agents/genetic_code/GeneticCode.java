package main.com.teamalfa.blindvirologists.agents.genetic_code;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.virus.Virus;
import main.com.teamalfa.blindvirologists.virologist.Virologist;
import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

/**
 *  abstract class for all the genetic codes
 *  further viruses and vaccines should implement all these functions
 */
abstract public class GeneticCode {
    protected String type;
    abstract public Virus createVirus(ElementBank elementBank);
    abstract public Vaccine createVaccine(ElementBank elementBank);
    public void autoInfect(Virologist virologist){  }
    public String getType() { return type; }
    @Override
    public boolean equals(Object obj) {
        // if compared to self true
        if(obj == this) return true;
        // if not genetic code false
        if(!(obj instanceof GeneticCode)) return false;

        // now it's safe to cast
        GeneticCode code = (GeneticCode) obj;
        return code.getType() == type;
    }
}
