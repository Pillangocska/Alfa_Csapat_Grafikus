package GUI.view.view.fieldView;

import GUI.view.view.geneticCodeView.GeneticCodeView;
import main.com.teamalfa.blindvirologists.agents.genetic_code.GeneticCode;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;

import java.awt.*;

public class LaboratoryView extends FieldView{
    private Laboratory lab;
    private GeneticCodeView code;

    public LaboratoryView(){
        color = Color.PINK;
        this.text = "lab";
        setFieldText("lab");
    }

    public void setLab(Laboratory lab) {
        this.lab = lab;
    }

    @Override
    public Laboratory getField(){
        return lab;
    }
}
