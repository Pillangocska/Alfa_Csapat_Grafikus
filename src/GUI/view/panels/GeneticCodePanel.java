package GUI.view.panels;

import main.com.teamalfa.blindvirologists.virologist.backpack.pockets.GeneticCodePocket;

import javax.swing.*;
import java.awt.*;

public class GeneticCodePanel extends BaseBagPanel {
    GeneticCodePocket geneticCodePocket;

    public GeneticCodePanel(GeneticCodePocket geneticCodePocket) {
        super(geneticCodePocket.getMaxSize());
        this.geneticCodePocket = geneticCodePocket;
        setLayout(new GridBagLayout());

        // creating the layout of the panel. creating constraints that will later be manipulated at each element
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(3, 0, 3, 0), 0, 0);

        // creating the title of the panel
        JLabel title = new JLabel();
        title.setText("Genetic codes"); // the correct name would be "Pieces of Equipment"
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 12));
        title.setForeground(Color.RED);

        add(title, constraints);
        update();
    }
}
