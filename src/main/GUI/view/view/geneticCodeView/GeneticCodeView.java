package main.GUI.view.view.geneticCodeView;

import main.GUI.view.view.View;
import main.logic.com.teamalfa.blindvirologists.agents.genetic_code.GeneticCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GeneticCodeView extends JButton implements View, ActionListener {
    private GeneticCode geneticCode;

    public GeneticCodeView(GeneticCode geneticCode) {
        this.geneticCode = geneticCode;
    }

    public GeneticCode getGeneticCode() {
        return geneticCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }
    protected abstract void handleIcon();
}
