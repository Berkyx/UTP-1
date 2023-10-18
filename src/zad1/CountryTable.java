package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class CountryTable {

    private String file;

    public CountryTable(String file) {
        this.file = file;
    }

    public JTable create() throws Exception {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2:
                        return Integer.class;
                    case 3:
                        return ImageIcon.class;
                    default:
                        return String.class;
                }
            }
        };

        JTable table = new JTable(model);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] colNames = reader.readLine().split("\t");
            for (String colName : colNames) {
                model.addColumn(colName.trim());
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length < colNames.length) {
                    throw new Exception("Data row does not contain enough entries: " + line);
                }
                Vector<Object> dataRow = new Vector<>();
                for (int i = 0; i < data.length; i++) {
                    if (i == 2) {
                        dataRow.add(Integer.parseInt(data[i].trim()) * 1000);
                    } else if (i == 3) {
                        dataRow.add(new ImageIcon(data[i].trim()));
                    } else {
                        dataRow.add(data[i].trim());
                    }
                }
                model.addRow(dataRow);
            }
        } catch (Exception e) {
            throw new Exception("Failed to create table: " + e.getMessage(), e);
        }        	
        table.getColumnModel().getColumn(2).setCellRenderer(new PopulationCellRenderer());
        table.setRowHeight(50);

        return table;
    }
}
