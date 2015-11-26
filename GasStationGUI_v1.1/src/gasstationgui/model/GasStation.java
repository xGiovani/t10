package gasstationgui.model;

import java.util.ArrayList;

/**
 *
 * @author Giovani
 */
public class GasStation {
    private String CNPJ;
    private String RazaoSocial;
    private String NomeFantasia;
    private String Bandeira;
    private String Endereco;
    private String Bairro;
    private String CEP;
    private String Imagem;
    private ArrayList<Fuel> Flist; 
    
    public GasStation(){
        Flist = new ArrayList<Fuel>();
    }
    
    // Cadastro do Posto
    public GasStation(String CNPJ, String RazaoSocial, String NomeFantasia, String Bandeira, String Endereco, String Bairro, String CEP, String Imagem){
        this.Flist = new ArrayList<Fuel>();
        this.CNPJ = CNPJ;
        this.RazaoSocial = RazaoSocial;
        this.NomeFantasia = NomeFantasia;
        this.Bandeira = Bandeira;
        this.Endereco = Endereco;
        this.Bairro = Bairro;
        this.CEP = CEP;
        this.Imagem = Imagem;
    }
    
    // Cadastrar Combustivel no posto
    public void AddFuel(Fuel Fuel){
        Flist.add(Fuel);
    }
    
    // Getters and Setters
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

    // Retorna o combustivel do posto
    public ArrayList<Fuel> getFuel() {
        return Flist;
    }
    
    // Modifica os atributos da lista de combustiveis
    public void setFuel(ArrayList<Fuel> Flist){
        this.Flist=Flist;
    }
}
