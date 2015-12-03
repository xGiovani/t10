package gasstationgui.model;

import java.util.ArrayList;

public class Fuel{
    private static int ContFuel;   // Numero de combustiveis cadastrados
    private int ID;                // ID do combustivel
    private int IDGStation;        // ID do posto a qual esse combustivel pertence
    private String Tipo;
    private Info Info;              // informações do combustivel: Data de Coleta e Preço
    private ArrayList<Info> InfoLog; // guardar o histórico dos dados alterados(data coleta e preço)
    
    public Fuel(){
        ID = ContFuel;
        ContFuel++;
        InfoLog = new ArrayList<Info>();    
    }
    
     // Getters and Setters
    public int getContFuel(){
        return ContFuel;
    }
    
    public void setContFuel(int ContFuel){
        Fuel.ContFuel= ContFuel;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getIDGStation(){
        return IDGStation;
    }
    
    public void setIDGStation(int IDGStation){
        this.IDGStation = IDGStation;
    }
    public String getTipo() {
        return Tipo;
    }
    
    public void setTipo(String Tipo){
        this.Tipo = Tipo;
    }
    
    public Info getInfo(){
        return Info;
    }
    
    public void setInfo(Info Info){
        this.Info = Info;
        InfoLog.add(Info);
    }
    
    public void AddInfo(Info Info){
        InfoLog.add(Info);
    }
            
     // getters and setters do historico de data de coleta e precos
    public ArrayList<Info> getInfoLog(){
        return InfoLog;
    }
    
    public void setLogInfo(ArrayList<Info> InfoLog){
        this.InfoLog = InfoLog;
    }
}
