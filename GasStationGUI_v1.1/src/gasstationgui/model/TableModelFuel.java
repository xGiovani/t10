package gasstationgui.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Giovani
 */
public class TableModelFuel extends AbstractTableModel{
    // Nome das colunas para a tabela dos Combustiveis cadastrados
    private static final String[] columnNames = {"Tipo", "Data de Coleta", "Preço"};
    
    private final ArrayList<GasStation> GStations;
    private final int index;
    
    public TableModelFuel(int index, ArrayList<GasStation> GStations){
        this.GStations = GStations;
        this.index = index;
    }
    
    public ArrayList<Fuel> getFuelArray(){
        return GStations.get(index).getFuel();
    }
    
    // Index = Qual posto foi selecionado, IndexF = Qual combustivel correspondente ao posto(index)
    // Remove o combustivel selecionado na tabela de acordo com o posto selecionado
    public void removeF(int index, int indexF){
        GStations.get(index).getFuel().remove(indexF);
        fireTableRowsDeleted(indexF, indexF);
    }
    
    // Qual Combustivel foi selecionado na tabela
    public Fuel selectF(int index, int indexF){
        return GStations.get(index).getFuel().get(indexF);
    }
    
    // Adiciona um novo combustivel a ultima linha da tabela
    public void addF(int index, Fuel f){
        GStations.get(index).AddFuel(f);
        fireTableRowsInserted(GStations.get(index).getFuel().size()-1, GStations.get(index).getFuel().size()-1);
    }
    
    // Atualiza a tabela de combustiveis
    public void updateF(int index, int indexF, Fuel f){
        GStations.get(index).getFuel().get(indexF).setDataColeta(f.getDataColeta());
        GStations.get(index).getFuel().get(indexF).setPreco(f.getPreco());
        fireTableRowsUpdated(indexF, indexF);
    }
    
    // Retorna o numero de colunas da tabela de combustiveis cadastrados
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    // Poe os atributos de postos em cada coluna da tabela de combustiveis cadastrados
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
    // Retorna o numero de linhas da tabela, a quantidade de postos cadastrados
    @Override
    public int getRowCount() {
        // no lugar do 0 era para ser o posto escolhido
        return GStations.get(index).getFuel().size();
    }
    
    // Escreve na tabela de postos cadastrados os dados de cada Posto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            // no lugar do 0 era para ser o posto escolhido
            case 0: return GStations.get(index).getFuel().get(rowIndex).getTipo();
            case 1: return GStations.get(index).getFuel().get(rowIndex).getDataColeta();
            case 2: return GStations.get(index).getFuel().get(rowIndex).getPreco();
        }
        return null;
    }
}
