package gasstationgui.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import database.GasStationDAO;
import database.FuelDAO;
import database.InfoLogDAO;

/**
 *
 * @author Giovani
 */
public class TableModelGasStation extends AbstractTableModel{
    private static final String[] columnNames = {"CNPJ", "Razao Soc.", "Nome Fant.", "Bandeira", "Endereco", "Bairro", "CEP"};  
    private final ArrayList<GasStation> GStations;
    private final ArrayList<Fuel> Fuels;
    private final ArrayList<Info> InfoLog;
    private final GasStationDAO gs;
    private final FuelDAO fs;
    private final InfoLogDAO il;
    
    public TableModelGasStation(){
        GStations = new ArrayList<GasStation>();
        Fuels = new ArrayList<Fuel>();
        InfoLog = new ArrayList<Info>();
        
        gs = new GasStationDAO();
        fs = new FuelDAO();
        il = new InfoLogDAO();
        
        //gs.dropTable();
        //fs.dropTable();
        //il.dropTable();
        //gs.createTable();
        //fs.createTable();
        //il.createTable();
        
        GasStation GS = new GasStation(false);
        Fuel FS = new Fuel();
        
        // Leitura do Banco de Dados de postos, combustiveis e info(data de colete preço)
        // Lista de Postos cadastrados
        for(GasStation temp : gs.buscarTodos()){
            GStations.add(temp);  
        }
        // Lista de combustiveis cadsatrados
        for(Fuel temp : fs.buscarTodos()){
            Fuels.add(temp);
        }
        // Lista de histórico de preços cadastrados
        for(Info temp : il.buscarTodos()){
            InfoLog.add(temp);
        }
        
        // Coloca o ArrayList de histórico de preços em seu respectivo combustivel
        for(int indexF=0; indexF < Fuels.size(); indexF++){
            for(int indexI=0; indexI < InfoLog.size(); indexI++){
                if(InfoLog.get(indexI).getIDFuel() == Fuels.get(indexF).getID()){
                    Fuels.get(indexF).setInfo(InfoLog.get(indexI));
                }
            }
            // caso o histórico de combustivel possua mais de um elemento, ele possui um histórico
            // é apagado o primeiro valor(valor atual) que já está presente no array histórico(infolog)
            if(Fuels.get(indexF).getInfoLog().size() > 1){
                Fuels.get(indexF).getInfoLog().remove(0);
            }
        }
        
        // Coloca o ArrayList de combustiveis dentro de seu respectivo posto
        for(int index=0; index < GStations.size(); index++){
            for(int indexF=0; indexF < Fuels.size(); indexF++){
                if(Fuels.get(indexF).getIDGStation() == GStations.get(index).getID())
                    GStations.get(index).AddFuel(Fuels.get(indexF));
            }
        }
        
        // Escreve o ultimo ID do banco de dados na variavel static ContGasStation/ContFuel 
        // para controlar novos ID's criados na interface
        // Caso não há cadastros no banco de dados, os atributos ContGasStation/ConFuel começam em 0
        GS.setContGasStation(gs.GetContGasStation());
        FS.setContFuel(fs.GetContFuel());
    }
    
    // Remove o posto selecionado
    public void remove(int index){
        int IDPosto = GStations.get(index).getID();
        il.excluirALL(IDPosto);                    
        fs.excluirAll(IDPosto);                     
        gs.excluir(IDPosto);                         // Remove no BD todas as informações relacionadas a esse posto
        GStations.remove(index);                      
        fireTableRowsDeleted(index, index);
    }
    
    // Qual posto foi selecionado na tabela
    public GasStation select(int index){
        return GStations.get(index);
    }
    
    // Adiciona um novo posto cadastrado
    public void add(GasStation g){
        GStations.add(g);
        gs.inserir(g);
        fireTableRowsInserted(GStations.size()-1, GStations.size()-1);
    }
    
    // Atualiza o cadastrado de um determinado posto
    public void update(int index, GasStation g, ArrayList<Fuel> Flist){
        g.setID(GStations.get(index).getID());              // ID do posto alterado
        gs.alterar(g); 
        GStations.set(index, g);
        GStations.get(index).setFuel(Flist); // Guarda a lista de combustivel do posto alterado
        fireTableRowsUpdated(index, index);
    }
    
    // Retorna o Array de Postos para ser utilizado nas outras Views
    public ArrayList<GasStation> getGasStationArray(){
        return GStations;
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
            //case 0: return GStations.get(rowIndex).getID();
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
