package gasstationgui.model;

import java.util.ArrayList;

public class Fuel {
    private String Tipo;
    private String DataColeta;
    private double Preco;
    private final ArrayList DataLog = new ArrayList();
    private final ArrayList PriceLog = new ArrayList();
    
    public Fuel(){  
    }
    
    public Fuel(String Tipo, String DataColeta, double Preco){
        this.Tipo = Tipo;
        this.DataColeta = DataColeta;
        this.Preco = Preco;
        DataLog.add(DataColeta);
        PriceLog.add(Preco);
    }
    
    // Getters and Setters
    public String getTipo() {
        return Tipo;
    }
    
    public void setTipo(String Tipo){
        this.Tipo = Tipo;
    }

    public String getDataColeta() {
        return DataColeta;
    }

    public void setDataColeta(String DataColeta) {
        this.DataColeta = DataColeta;
        DataLog.add(DataColeta);
    }

    public double getPreco() {
        return Preco;
    }
    
    public void setPreco(double Preco) {
        this.Preco = Preco;
        PriceLog.add(Preco);
    }
    
    public ArrayList getPriceLog(){
        return PriceLog;
    }
    
    public ArrayList getDataLog(){
        return DataLog;
    }
}
