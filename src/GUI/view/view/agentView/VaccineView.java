package GUI.view.view.agentView;

import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.genetic_code.*;

import javax.swing.*;

public class VaccineView extends AgentView {
    private Vaccine vaccine;

    /**
     * creates a new view to a vaccine in the model
     * @param vaccine the vaccine belonging to the view
     */
    public VaccineView(Vaccine vaccine) {
        // set the appropriate image
        String str = "valami";
        String imagePath = "resources/vaccines/yellow_vaccine.png";
        if (vaccine.getGeneticCode() instanceof DanceCode) imagePath = "resources/vaccines/yellow_vaccine.png";
        if (vaccine.getGeneticCode() instanceof AmnesiaCode) imagePath = "resources/vaccines/green_vaccine.png";
        if (vaccine.getGeneticCode() instanceof ParalyzeCode) imagePath = "resources/vaccines/blue_vaccine.png";
        if (vaccine.getGeneticCode() instanceof BearCode) imagePath = "resources/vaccines/red_vaccine.png";
        setImageIcon(new ImageIcon(imagePath));

        // set the vaccine
        this.vaccine = vaccine;
    }
}
