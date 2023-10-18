package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PopulationCellRenderer extends DefaultTableCellRenderer {

    public PopulationCellRenderer() {
        setHorizontalAlignment(JLabel.RIGHT);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (value instanceof Integer && (Integer) value > 20_000_000) {
            c.setForeground(Color.RED);
        } else {
            c.setForeground(Color.BLACK);
        }
        return c;
    }
}

