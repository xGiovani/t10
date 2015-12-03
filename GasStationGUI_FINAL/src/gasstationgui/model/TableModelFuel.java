package gasstationgui.model;

import database.FuelDAO;
import database.InfoLogDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Giovani
 */
public class TableModelFuel extends AbstractTableModel{
    private static final String[] columnNames = {"Tipo", "Data de Coleta", "Preço"};
    
    private final ArrayList<GasStation> GStations;
    private final int index;
    private final FuelDAO fs;
    private final InfoLogDAO il;
    
    // Recebe o index do posto selecionado e o ArrayList de Postos
    public TableModelFuel(int index, ArrayList<GasStation> GStations){
        this.GStations = GStations;
        this.index = index; 
        
        fs = new FuelDAO();
        il = new InfoLogDAO();
    }
    
    // Index = Qual posto foi selecionado, IndexF = Qual combustivel
    // Remove o combustivel selecionado na tabela de acordo com o posto selecionado
    public void removeF(int index, int indexF){
        int IDFuel = GStations.get(index).getFuel().get(indexF).getID();
        fs.excluir(IDFuel);
        il.excluirALL2(IDFuel);                        // Exclui o histórico de preços associados a esse combustivel
        GStations.get(index).getFuel().remove(indexF);
        fireTableRowsDeleted(indexF, indexF);                  
    }
    
    // Qual Combustivel foi selecionado na tabela
    public Fuel selectF(int index, int indexF){
        return GStations.get(index).getFuel().get(indexF);
    }
    
    // Adiciona um novo combustivel
    public void addF(int index, Fuel f){
        f.setIDGStation(GStations.get(index).getID());              // Salva ID de posto
        f.setID(f.getContFuel());                                   // Seta novo ID para o combustivel criado
        f.getInfo().setIDGasStation(GStations.get(index).getID());
        f.getInfo().setIDFuel(f.getContFuel());                   
        GStations.get(index).AddFuel(f);
        fs.inserir(f);
        il.inserir(f.getInfo());
        fireTableRowsInserted(GStations.get(index).getFuel().size()-1, GStations.get(index).getFuel().size()-1);
    }
    
    // Modifica a data de coleta e o preco do combustivel
    public void updateF(int index, int indexF, Info i){
        i.setIDFuel(GStations.get(index).getFuel().get(indexF).getID()); // ID do combustivel a ser alterado
        GStations.get(index).getFuel().get(indexF).setInfo(i);           // Atualiza no array de combustiveis
        fs.alterar(i);
        
        i.setIDGasStation(GStations.get(index).getID());
        il.inserir(i);
        fireTableRowsUpdated(indexF, indexF);
    }
    
    // Retorna o ArrayList de combustiveis
    public ArrayList<Fuel> getFuelArray(){
        return GStations.get(index).getFuel();
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
        return GStations.get(index).getFuel().size();
    }
    
    // Escreve na tabela de postos cadastrados os dados de cada Posto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            //case 0: return GStations.get(index).getFuel().get(rowIndex).getID();
            //case 0: return GStations.get(index).getFuel().get(rowIndex).getIDGStation();
            case 0: return GStations.get(index).getFuel().get(rowIndex).getTipo();
            case 1: return GStations.get(index).getFuel().get(rowIndex).getInfo().getDataColeta();
            case 2: return GStations.get(index).getFuel().get(rowIndex).getInfo().getPreco();
        }
        return null;
    }
}
