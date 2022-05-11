package GUI.view.view.fieldView;

import GUI.view.view.View;
import GUI.view.view.VirologistView;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FieldView extends JPanel implements View, MouseListener {
    protected Field field;
    private ArrayList<VirologistView> virologistOnField = new ArrayList<>();
    private JLabel textField = new JLabel("Field");
    protected String text = "Field";
    protected Color color;
    private static final int hexaDimension = 150;

    public FieldView(){
        color = Color.white;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(100,100));
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        this.add(textField);
        this.setVisible(true);
        this.addMouseListener(this);
    }
    public static int getHexaDimension(){
        return hexaDimension;
    }
    public void setField(Field f){
        this.field = f;
    }
    public void setFieldText(String text) {
        textField.setText(text);
    }
    public Field getField(){
        return field;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Virologist current = TurnHandler.getActiveVirologist();
        if(field != current.getField() && current != null)
            current.move(field);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String name = "null";
        if(field != null) {
            String[] parts = field.toString().split("\\.");
            name = parts[parts.length-1];
        }
        textField.setText(name);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        textField.setText(text);
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {
        Virologist current = TurnHandler.getActiveVirologist();
        if(field != current.getField())
            current.move(field);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,hexaDimension/2, hexaDimension/2 ,1,color.getRGB(),true);
    }

    public void drawPolygon(Graphics2D g, int xcenter, int ycenter, int lineThickness, int colorValue, boolean filled) {
        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        int[] xpoints = new int[6];
        int[] ypoints = new int[6];
        for (int p = 0; p < 6; p++) {
            double fraction = (double) p / 6;
            double angle = fraction * Math.PI * 2 + Math.toRadians((90 + 180) % 360);
            int x = (int) (xcenter + Math.cos(angle) * hexaDimension/2); //1000/2 -> center
            int y = (int) (ycenter + Math.sin(angle) * hexaDimension/2); //120 -> radius
            Point point = new Point(x,y);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
        }
        if (filled)
            g.fillPolygon(xpoints, ypoints, 6);
        else
            g.drawPolygon(xpoints, ypoints, 6);
    }

}
