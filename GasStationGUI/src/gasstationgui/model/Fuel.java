package gasstationgui.model;

import java.util.ArrayList;

public class Fuel {
    private String Tipo;
    private String DataColeta;
    private double Preco;
    private int h=0;                                     // indice do array Historico
    private final ArrayList Historico = new ArrayList(); // array para guardar a mudança de preços em Fuel
    
    public Fuel(){
    }
    
    public Fuel(String Tipo, String DataColeta, double Preco){
        this.Tipo = Tipo;
        this.DataColeta = DataColeta;
        this.Preco = Preco;
        Historico.add(Preco);
        h++;
        
    }
    
    // Getters and Setters
    public void setPreco(double Preco) {
        this.Preco = Preco;
        Historico.add(Preco);
        h++;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getDataColeta() {
        return DataColeta;
    }

    public void setDataColeta(String DataColeta) {
        this.DataColeta = DataColeta;
    }

    public double getPreco() {
        return Preco;
    }
    
    /*@Override
    public String toString(){
       return "\nTipo de Combustivem: "+Tipo+", Data de Coleta de Preço: "
               +DataColeta+", Preco: "+Preco+",\nHistorico Precos: "+String.join(",", Historico)+"\n";
   }*/
}
