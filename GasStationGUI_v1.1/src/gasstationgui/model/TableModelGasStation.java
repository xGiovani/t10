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
    
    private final ArrayList<GasStation> GStations;
    
    public TableModelGasStation(){
        GStations = new ArrayList<GasStation>();
        
        // Exemplos
        GStations.add(new GasStation("00.074.771/0001-23","Comercio de combustiveis Mello LTDA","Ubaldo da Silva Mello","Ipiranga","Coronel Bicaco","Bairro Centro","97010-005","ipiranga.jpg"));
        GStations.add(new GasStation("00.054.471/0001-19","Auto Posto Daniel LTDA","Posto da Rotula","Shell","Porto Alegre","Bairro Uglione ","97212-001","shell.jpg"));
        GStations.add(new GasStation("00.088.745/0001-54","Auto Posto Mirim LTDA","Posto Mirim","Petrobras Distruibuidora S.A.","Torres","Bairro Camobi","95125-021","petrobras.jpg"));
        GStations.add(new GasStation("00.088.745/0001-54","Posto de Combustiveis Fuzer LTDA","","Charrua","Agudo","Bairro Noal","92151-059","charrua.jpg"));
        GStations.get(0).AddFuel(new Fuel("Gasolina","21/08/2015",19.50));
        GStations.get(0).AddFuel(new Fuel("Diesel","05/15/1990",29.90));
        GStations.get(1).AddFuel(new Fuel("Alcool","15/02/2002",10.50));
        GStations.get(2).AddFuel(new Fuel("Etanol","12/20/2010",15.49));
        GStations.get(3).AddFuel(new Fuel("Diesel","05/15/1990",29.90));
    }
    
    // Retorna o array de postos que foi criado
    public ArrayList<GasStation> getGasStationArray(){
        return GStations;
    }
    
    // Remove o posto selecionado nat abela
    public void remove(int index){
        GStations.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    // Funcao para comparar a string do campo bairro da lista e a string do campo Buscar
    /*public int search(String bairro){
        int i=0, index = -1;
        for(; i<GStations.size() ; i++){
            if(bairro.equalsIgnoreCase(GStations.get(i).getBairro())) {
                index = i;
            }
        }
        return index;
    }*/
    
    // Qual posto foi selecionado na tabela
    public GasStation select(int index){
        return GStations.get(index);
    }
    
    // Adiciona um novo posto a ultima linha da tabela
    public void add(GasStation g){
        GStations.add(g);
        fireTableRowsInserted(GStations.size()-1, GStations.size()-1);
    }
    
    // Atualiza a tabela de postos, .set das listas cria um array vazio
    public void update(int index, GasStation g, ArrayList<Fuel> Flist){
        GStations.set(index, g);
        GStations.get(index).setFuel(Flist);
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
    
    // Escreve na tabela de postos cadastrados os dados de cada Posto
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
