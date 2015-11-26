package gasstationgui.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Giovani
 */
public class TableModelLog extends AbstractTableModel{
     private static final String[] columnNames = {"Data de Coleta", "Preços"};
     
     private final ArrayList<Fuel> Fuels;
     private final int index;
     
     public TableModelLog(int index, ArrayList<Fuel> Fuels){
         this.index = index;
         this.Fuels = Fuels;
     }
     
    @Override
    public int getColumnCount() {
        return columnNames.length;    
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return Fuels.get(index).getPriceLog().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0: return Fuels.get(index).getDataLog().get(rowIndex);
                case 1: return Fuels.get(index).getPriceLog().get(rowIndex);
        }
        return null;
    }
}
