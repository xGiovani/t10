package gasstationgui.model;

/**
 *
 * @author Giovani
 */
public class Info {
    private int IDGasStation;
    private int IDFuel;            
    private String DataColeta;
    private double Preco;
    
    public Info(){
    }
    
    public int getIDGStation(){
        return IDGasStation;
    }
    
    public void setIDGasStation(int IDGasStation){
        this.IDGasStation = IDGasStation;
    }
    
    public int getIDFuel() {
        return IDFuel;
    }

    public void setIDFuel(int IDFuel) {
        this.IDFuel = IDFuel;
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

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }
}