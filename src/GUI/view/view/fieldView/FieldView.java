package GUI.view.view.fieldView;

import GUI.view.view.View;
import GUI.view.view.VirologistView;
import main.com.teamalfa.blindvirologists.city.fields.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FieldView extends JPanel implements View, MouseListener {
    private Field field;
    private ArrayList<VirologistView> virologistOnField = new ArrayList<>();

    public FieldView(){
        this.setBackground(Color.GREEN);
        this.setOpaque(false);
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        this.setVisible(true);
    }
    public void setField(Field f){
        this.field = f;
    }
    public Field getField(){
        return field;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }
}
