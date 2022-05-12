package GUI.view.panels;

import GUI.view.view.InventorySlot;
import GUI.view.view.agentView.*;
import main.com.teamalfa.blindvirologists.agents.Agent;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.virus.AmnesiaVirus;
import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.com.teamalfa.blindvirologists.agents.virus.DanceVirus;
import main.com.teamalfa.blindvirologists.agents.virus.ParalyzeVirus;
import main.com.teamalfa.blindvirologists.virologist.backpack.pockets.AgentPocket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AgentPanel extends BaseBagPanel {
    private ArrayList<AgentView> views;
    private ArrayList<InventorySlot> slots;
    private AgentPocket agentPocket;

    public AgentPanel(AgentPocket agentPocket) {
        // initializing
        super(agentPocket.getMaxSize());
        this.agentPocket = agentPocket;
        setLayout(new GridBagLayout());
        slots = new ArrayList<>();
        update();

        // creating inventory slots
        for (int i = 0; i < agentPocket.getMaxSize(); i++) {
            slots.add(new InventorySlot(null));
        }

        // creating the layout of the panel
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0);

        // creating the title of the panel
        JLabel title = new JLabel("Agents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 12));
        title.setForeground(Color.RED);

        add(title, constraints);

        // adding slots
        constraints.anchor = GridBagConstraints.CENTER;
        for(var s: slots) {
            constraints.gridy++;
            add(s, constraints);
        }
    }

    @Override
    /**
     * updates the view of the agent panel: displays the agents of the current virologist. this must only be called if the composition of the
     */
    public void update() {
        removeAll();

        // set the number of slots this panel is going to have
        setViewCount(agentPocket.getMaxSize());
        // create the slots and fill them up according to the agent pocket
        views = new ArrayList<AgentView>();
        ArrayList<Agent> agents = agentPocket.getAgentHolder();
        for (var a : agents) {
            if (a instanceof Vaccine) views.add(new VaccineView((Vaccine) a));
            if (a instanceof DanceVirus) views.add(new DanceVirusView((DanceVirus) a));
            if (a instanceof ParalyzeVirus) views.add(new ParalyzeVirusView((ParalyzeVirus) a));
            if (a instanceof BearVirus) views.add(new BearVirusView((BearVirus) a));
            if (a instanceof AmnesiaVirus) views.add(new AmnesiaVirusView((AmnesiaVirus) a));
        }



    }

    public void setAgentPocket(AgentPocket agentPocket) {
        this.agentPocket = agentPocket;
        update();
    }
}
