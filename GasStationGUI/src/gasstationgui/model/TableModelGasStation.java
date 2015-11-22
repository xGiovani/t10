package gasstationgui.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Giovani
 */
public class TableModelGasStation extends AbstractTableModel{
    // Nome das colunas para a tabela dos Postos cadastrados
    private static final String[] columnNames = {"CNPJ", "Razao Soc.", "Nome Fant.", "Bandeira", "Endereco", "Bairro", "CEP"};
    
    private ArrayList<GasStation> GStations;
    
    public TableModelGasStation(){
        GStations = new ArrayList<GasStation>();
        GStations.add(new GasStation("00.074.771/0001-23","Comercio de combustiveis Mello LTDA","Ubaldo daS ilva Mello","Ipiranga","Coronel Bicaco","Bairro A","97010-005","ipiranga.jpg"));
        GStations.add(new GasStation("00.054.471/0001-19","Auto Posto Daniel LTDA","Posto da Rotula","Shell","Porto Alegre","Bairro B","97212-001","shell.jpg"));
        GStations.add(new GasStation("00.088.745/0001-54","Auto Posto Mirim LTDA","Posto Mirim","Petrobras Distruibuidora S.A.","Torres","Bairro C","95125-021","petrobras.jpg"));
        GStations.add(new GasStation("00.088.745/0001-54","Posto de Combustiveis Fuzer LTDA","","Charrua","Agudo","Bairro D","92151-059","charrua.jpg"));

    }
    
    // Remove o posto selecionado nat abela
    public void remove(int index){
        GStations.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    // Qual posto foi selecionado na tabela
    public GasStation select(int index){
        return GStations.get(index);
    }
    
    // Adiciona um novo posto a ultima linha da tabela
    public void add(GasStation g){
        GStations.add(g);
        fireTableRowsInserted(GStations.size()-1, GStations.size()-1);
    }
    
    // Atualiza a tabela de postos
    public void update(int index, GasStation g){
        GStations.set(index, g);
        fireTableRowsUpdated(index, index);
    }
    
    // Retorna o numero de colunas da tabela de postos cadastrados
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    // Poe os atributos de postos em cada coluna da tabela de postos cadastrados
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    // Retorna o numero de linhas da tabela, a quantidade de postos cadastrados
    @Override
    public int getRowCount() {
        return GStations.size();
    }
    
    //
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return GStations.get(rowIndex).getCNPJ();
            case 1: return GStations.get(rowIndex).getRazaoSocial();
            case 2: return GStations.get(rowIndex).getNomeFantasia();
            case 3: return GStations.get(rowIndex).getBandeira();
            case 4: return GStations.get(rowIndex).getEndereco();
            case 5: return GStations.get(rowIndex).getBairro();
            case 6: return GStations.get(rowIndex).getCEP();
        }
        return null;
    }
}
