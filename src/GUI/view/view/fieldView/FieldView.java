package GUI.view.view.fieldView;

import GUI.view.frames.GameFrame;
import GUI.view.view.View;
import GUI.view.view.VirologistView;
import GUI.view.view.equipmentView.AxeView;
import GUI.view.view.equipmentView.EquipmentView;
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
    private ArrayList<EquipmentView> equipmentViews = new ArrayList<>();
    private JLabel textField = new JLabel("Field");
    protected String text = "Field";
    protected Color color;
    protected Image newImage;
    protected Image backGround;
    private static final int hexaDimension = 200;

    public FieldView(){
        color = Color.white;
        newImage = Toolkit.getDefaultToolkit().createImage("resources/field1.png");
        backGround = newImage.getScaledInstance(198,198,Image.SCALE_DEFAULT);
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
        if(field != current.getField() && current != null) {
            current.move(field);
            GameFrame.setHighlightedVirologistView(null);
        }
        else if(field.equals(current.getField())) {
            if(!TurnHandler.getActiveVirologist().getDiscoveredFields().contains(field))
                TurnHandler.getActiveVirologist().search();
        }
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
        // remove all components from field
        removeAll();

        // and update only if its current field
        if(field.equals(TurnHandler.getActiveVirologist().getField())) {
            if(!TurnHandler.getActiveVirologist().getDiscoveredFields().contains(field)) {
                add(new VirologistView(TurnHandler.getActiveVirologist()));
            }
            else {
                for (Virologist virologist : field.getVirologists()) {
                    add(new VirologistView(virologist));
                }
            }
        }
    }

    @Override
    public void onClick() {
        // ez a fuggveny nem tud semmit szentem nem kell
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,hexaDimension/2, hexaDimension/2 ,1,color.getRGB(),true);
        if(TurnHandler.getActiveVirologist().getDiscoveredFields().contains(field))
            g.drawImage(backGround,1,1,null);
        this.repaint();
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
