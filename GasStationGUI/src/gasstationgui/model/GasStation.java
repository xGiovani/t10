package gasstationgui.model;

import java.util.ArrayList;

public class GasStation {
    private String CNPJ;
    private String RazaoSocial;
    private String NomeFantasia;
    private String Bandeira;
    private String Endereco;
    private String Bairro;
    private String CEP;
    private String Imagem;
    private ArrayList<Fuel> Flist = new ArrayList<Fuel>(); // Array da lista de Combustiveis
    private int i=0;                                       // Indice do Array de Combustiveis
    
    public GasStation(){
    }
    
    // Cadastro apenas do Posto
    public GasStation(String CNPJ, String RazaoSocial, String NomeFantasia, String Bandeira, String Endereco, String Bairro, String CEP, String Imagem){
        this.CNPJ = CNPJ;
        this.RazaoSocial = RazaoSocial;
        this.NomeFantasia = NomeFantasia;
        this.Bandeira = Bandeira;
        this.Endereco = Endereco;
        this.Bairro = Bairro;
        this.CEP = CEP;
        this.Imagem = Imagem;
    }
    
    // cadastro do posto e de algum combustivel
    public GasStation(String CNPJ, String RazaoSocial, String NomeFantasia, String Bandeira, String Endereco, String Bairro, String CEP, String Imagem, Fuel Fuel){
        this.CNPJ = CNPJ;
        this.RazaoSocial = RazaoSocial;
        this.NomeFantasia = NomeFantasia;
        this.Bandeira = Bandeira;
        this.Endereco = Endereco;
        this.Bairro = Bairro;
        this.CEP = CEP;
        this.Imagem = Imagem;
        Flist.add(Fuel);
        i++;
    }
    
    // Cadastrar Combustivel em um determinado posto
    public void AddFuel(Fuel Fuel){
        Flist.add(Fuel);
        i++;
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

    // Retorna o combustivel do posto | GetFuel
    public ArrayList<Fuel> getFuel() {
        return Flist;
    }
    // Modifica atributos de Combustivel | Set Fuel
    public void setFuel(Fuel Fuel) {
        Flist.set(i, Fuel);
    }
    
    /*@Override
    public String toString(){
       return "CNPJ: "+CNPJ+", Razao Social: "+RazaoSocial+", Nome Fantasia: "
               +NomeFantasia+", Bandeira: "+Bandeira+", Endereço: "
               +Endereco+", Bairro: "+Bairro+", CEP: "
               +CEP+Flist.toString()+" \n";
   }*/
    
}
