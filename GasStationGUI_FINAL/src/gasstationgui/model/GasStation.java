package gasstationgui.model;

import java.util.ArrayList;

/**
 *
 * @author Giovani
 */
public class GasStation {
    private static int ContGasStation;      // controla o numero de postos ja criados para evitar ID's iguais
    private int ID;
    private String CNPJ;
    private String RazaoSocial;
    private String NomeFantasia;
    private String Bandeira;
    private String Endereco;
    private String Bairro;
    private String CEP;
    private String Imagem;
    private ArrayList<Fuel> Flist; 
    
    public GasStation(boolean update){
        if(update == false)   // inserir um novo posto
        {
            ID = ContGasStation;
            ContGasStation++;
            Flist = new ArrayList<Fuel>();
        }
        // else: modificar atributos
    }
    
    public void setContGasStation(int ContGasStation){
        GasStation.ContGasStation = ContGasStation;
    }
    
    // Cadastrar Combustivel no posto
    public void AddFuel(Fuel Fuel){
        Flist.add(Fuel);
    }
     
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    public String getBandeira() {
        return Bandeira;
    }

    public void setBandeira(String Bandeira) {
        this.Bandeira = Bandeira;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
    
    public String getImagem(){
        return Imagem;
    }
    
    public void setImagem(String Imagem){
        this.Imagem = Imagem;
    }

    // Retorna a lista de combustiveis do posto
    public ArrayList<Fuel> getFuel() {
        return Flist;
    }
    
    // Modifica os atributos da lista de combustiveis
    public void setFuel(ArrayList<Fuel> Flist){
        this.Flist=Flist;
    }
}
