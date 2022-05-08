package GUI.view.panels;

import GUI.view.view.ElementView;
import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;

public class ElementPanel extends JPanel implements View {
    // NAGYON FONTOS, HOGY EZ NE A BaseBagPanelből SZÁRMAZZON LE!!
    private ElementView view;
    // the label signaling the quantity of the amino acids in the virologist's backpack
    private JLabel aminoText;
    // the label signaling the quantity of ht nucleotides in the virologist's backpack
    private JLabel nucleoText;

    /**
     * constructs a new element panel with 0 nucleotids and amino acids displayed
     */
    public ElementPanel() {
        // setting up the panel
        setOpaque(false);

        // creating text for the quantity of amino acids
        aminoText = new JLabel();
        aminoText.setBorder(BorderFactory.createEmptyBorder(0,0,0, 30));
        aminoText.setText(": 0");
        aminoText.setOpaque(false);
        aminoText.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        aminoText.setForeground(Color.RED);
        // creating the image for amino acids
        ImageIcon aminoImageIcon = new ImageIcon("resources/amino.png");
        aminoImageIcon.setImage(aminoImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel aminoIcon = new JLabel(aminoImageIcon);

        // creating text for the quantity of nucleotides
        nucleoText = new JLabel();
        nucleoText.setText(": 0");
        nucleoText.setOpaque(false);
        nucleoText.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        nucleoText.setForeground(Color.RED);
        //creating the image for nucleotides
        ImageIcon nucleoImageIcon = new ImageIcon("resources/nucleotide.png");
        nucleoImageIcon.setImage(nucleoImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel nucleoIcon = new JLabel(nucleoImageIcon);

        // Adding elements to the panel
        add(aminoIcon);
        add(aminoText);
        add(nucleoIcon);
        add(nucleoText);
    }

    // ide lehetne csinálni settereket a labelekhez!

    @Override
    public void update() {
        //todo
    }

    @Override
    public void onClick() {
        //todo
    }
}
