package GUI.view.view.fieldView;

import GUI.view.view.VirologistView;
import GUI.view.view.geneticCodeView.*;
import main.com.teamalfa.blindvirologists.agents.genetic_code.AmnesiaCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.BearCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.DanceCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.ParalyzeCode;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import java.awt.*;

public class LaboratoryView extends FieldView{
    private GeneticCodeView code;
    private Laboratory laboratory;

    public LaboratoryView(){
        color = new Color(3, 18, 9);
        newImage = Toolkit.getDefaultToolkit().createImage("resources/lab.png");
        backGround = newImage.getScaledInstance(315,315,Image.SCALE_DEFAULT);
        this.text = "lab";
        setFieldText("lab");
    }

    public void setField(Laboratory lab) {
        field = lab;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,200/2, 200/2 ,1,color.getRGB(),true);
        g.drawImage(backGround,-45,-8,null);
        this.repaint();
    }
    @Override
    public void update() {
        // remove all components from field
        removeAll();

        // and update only if its current field
        if(field == TurnHandler.getActiveVirologist().getField()) {
            for (Virologist virologist : field.getVirologists()) {
                add(new VirologistView(virologist));
            }
            laboratory = (Laboratory) TurnHandler.getActiveVirologist().getField();
            if(laboratory.getGeneticCode().getName().equals("amnesia code"))
                add(new AmnesiaCodeView((AmnesiaCode) laboratory.getGeneticCode()));
            else if(laboratory.getGeneticCode().getName().equals("bear code"))
                add(new BearCodeView((BearCode) laboratory.getGeneticCode()));
            else if(laboratory.getGeneticCode().getName().equals("dance code"))
                add(new DanceCodeView((DanceCode) laboratory.getGeneticCode()));
            else if(laboratory.getGeneticCode().getName().equals("genetic code"))
                add(new ParalyzeCodeView((ParalyzeCode) laboratory.getGeneticCode()));
        }
    }
}
