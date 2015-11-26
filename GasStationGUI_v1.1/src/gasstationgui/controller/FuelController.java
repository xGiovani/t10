package gasstationgui.controller;

import gasstationgui.model.Fuel;
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
    
    // Ação do botao Inserir combustivel
    public void insertF(){
        Fuel Fuels = newFromViewF();
        if(Fuels != null){
            modelF.addF(viewF.getGasStationIndex(), Fuels);
        }
    }
    
    // Ação do botao update combustivel
    public void updateF(){
        int indexF = viewF.getTableFuel().getSelectedRow();
        if(indexF == -1){
            return;
        }
        Fuel Fuels = newFromViewF();
        if(Fuels != null){
            // Qual Posto | Qual Lista de combustivel | Lista Modificada
            modelF.updateF(viewF.getGasStationIndex(), indexF, Fuels);
        }
    }
    
    // Ação do botao remover combustivel
    public void removeF(){
        int indexF = viewF.getTableFuel().getSelectedRow();
        if(indexF == -1){
            return;
        }
        modelF.removeF(viewF.getGasStationIndex(), indexF);
    }
    
    // Selecionar da tabela um combustivel e mostrar nos JTextFields
    public void selectF(){
        int index = viewF.getGasStationIndex();
        int indexF = viewF.getTableFuel().getSelectedRow();
        Fuel Fuels = modelF.selectF(index, indexF);
        if(indexF == -1){
            return;
        }
        viewF.getTextTipoComb().setText(Fuels.getTipo());
        viewF.getTextDataColeta().setText(Fuels.getDataColeta());
        viewF.getTextPreco().setText(Double.toString(Fuels.getPreco())); 
    }
    
    // Limpar os campos de escrita
    public void clear(){
        viewF.getTextTipoComb().setText("");
        viewF.getTextDataColeta().setText("");
        viewF.getTextPreco().setText("");
    }
    
    // Retorna o que foi escrito na interface
    private Fuel newFromViewF(){
        try{
            Fuel Fuels = new Fuel();
            Fuels.setTipo(viewF.getTextTipoComb().getText());
            Fuels.setDataColeta(viewF.getTextDataColeta().getText());
            Fuels.setPreco(Double.parseDouble(viewF.getTextPreco().getText()));
            return Fuels;
        } catch (NumberFormatException e){
            viewF.showError("Dado de entrada invalido(s)!");
            return null;
        }
    }
}
