package GUI.view.view.fieldView;

import GUI.view.view.geneticCodeView.GeneticCodeView;
import main.com.teamalfa.blindvirologists.agents.genetic_code.GeneticCode;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;

import java.awt.*;

public class LaboratoryView extends FieldView{
    private GeneticCodeView code;

    public LaboratoryView(){
        color = Color.PINK;
        this.text = "lab";
        setFieldText("lab");
    }

    public void setField(Laboratory lab) {
        field = lab;
    }
}
