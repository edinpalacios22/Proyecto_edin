
package Modelo;


import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


public class Gestion_Encabezado implements TableCellRenderer {

    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent encabezado = null;
        encabezado = new JLabel((String)value);
        ((JLabel)encabezado).setHorizontalAlignment(SwingConstants.CENTER);
        Object cellValue = value;
        encabezado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 0, 204)));
        encabezado.setForeground(new java.awt.Color(204, 0, 204));
        encabezado.setFont(new java.awt.Font("Yu Gothic UI", 1, 16));
        
        return encabezado;
        }
    
}
    
 
    
    

