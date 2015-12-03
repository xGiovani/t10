package gasstationgui.controller;

import gasstationgui.model.Fuel;
import gasstationgui.model.Info;
import gasstationgui.model.TableModelFuel;
import gasstationgui.view.FuelView;

/**
 *
 * @author Giovani
 */
public class FuelController {
    private final FuelView viewF;
    private final TableModelFuel modelF;
    
    public FuelController(FuelView viewF, TableModelFuel modelF){
        this.viewF = viewF;
        this.modelF = modelF;
    }
    
    // Ação do botao Inserir
    public void insertF(){
        Fuel Fuels = newFromViewF();
        if(Fuels != null){
            modelF.addF(viewF.getGasStationIndex(), Fuels);
        }
    }
    
    // Ação do botao alterar
    public void updateF(){
        int indexF = viewF.getTableFuel().getSelectedRow();
        if(indexF == -1){
            return;
        }
        Info Info = newFromViewFUpdate();
        if(Info != null){
            // Qual Posto | Qual Lista de combustivel | Lista Modificada
            modelF.updateF(viewF.getGasStationIndex(), indexF, Info);
        }
    }
    
    // Ação do botao remover
    public void removeF(){
        int indexF = viewF.getTableFuel().getSelectedRow();
        if(indexF == -1){
            return;
        }
        modelF.removeF(viewF.getGasStationIndex(), indexF);
    }
    
    // Selecionar da tabela um combustivel e mostrar os dados nos JTextFields
    public void selectF(){
        int index = viewF.getGasStationIndex();
        int indexF = viewF.getTableFuel().getSelectedRow();
        Fuel Fuels = modelF.selectF(index, indexF);
        if(indexF == -1){
            return;
        }
        viewF.getTextTipoComb().setText(Fuels.getTipo());
        viewF.getTextDataColeta().setText(Fuels.getInfo().getDataColeta());
        viewF.getTextPreco().setText(Double.toString(Fuels.getInfo().getPreco())); 
    }
    
    // Limpar os campos de escrita
    public void clear(){
        viewF.getTextTipoComb().setText("");
        viewF.getTextDataColeta().setText("");
        viewF.getTextPreco().setText("");
    }
    
    // Guardar os dados da interface para inserir um novo combustivel
    private Fuel newFromViewF(){
        try{
            Fuel Fuels = new Fuel();
            Info info = new Info();
            Fuels.setTipo(viewF.getTextTipoComb().getText());
            info.setDataColeta(viewF.getTextDataColeta().getText());
            info.setPreco(Double.parseDouble(viewF.getTextPreco().getText()));
            Fuels.setInfo(info);
            return Fuels;
        } catch (NumberFormatException e){
            viewF.showError("Dado de entrada invalido(s)!");
            return null;
        }
    }
    // Guardar os dados da interface para alterar os dados de um combustivel
    private Info newFromViewFUpdate(){
        try{
            Info info = new Info();
            info.setDataColeta(viewF.getTextDataColeta().getText());
            info.setPreco(Double.parseDouble(viewF.getTextPreco().getText()));
            return info;
        } catch (NumberFormatException e){
            viewF.showError("Dado de entrada invalido(s)!");
            return null;
        }
    }
}
