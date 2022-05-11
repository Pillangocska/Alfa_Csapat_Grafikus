package GUI.view.view.fieldView;

import GUI.view.view.geneticCodeView.GeneticCodeView;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;

import java.awt.*;

public class LaboratoryView extends FieldView{
    private GeneticCodeView code;

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
}
