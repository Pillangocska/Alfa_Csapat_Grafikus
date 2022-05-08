package GUI.view.panels;

import javax.swing.*;
import java.awt.*;

/**
 * A JLabel used to display the quantity of different elements on the inventory panel.
 */
public class ElementQuantityLabel extends JLabel {
    private int qunatity; //stores the qunatity displayed by the label

    /**
     * Constructs a new label displaying quantity zero.
     */
    public ElementQuantityLabel() {
        setText(": 0");
        qunatity = 0;
        setOpaque(false);
        setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        setForeground(Color.RED);
    }

    /**
     * Set the qunatity of elements displayed on the label.
     * @param q - the quantity displayed on the label
     */
    public void setQuantity(int q) {
        setText(": " + q);
        qunatity = q;
    }
}
