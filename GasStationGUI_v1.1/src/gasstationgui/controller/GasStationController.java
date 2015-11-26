package gasstationgui.controller;

import gasstationgui.model.GasStation;
import gasstationgui.model.TableModelGasStation;
import gasstationgui.view.GasStationView;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Giovani
 */
public class GasStationController {
    private final GasStationView view;
    private final TableModelGasStation model;
    private final static int imageX = 217;     // redimensionamento da foto do posto na interface gráfica
    private final static int imageY = 245;
    
    public GasStationController(GasStationView view, TableModelGasStation model){
        this.view = view;
        this.model = model;
    }
    
    // Ação do botao Inserir
    public void insert(){
        GasStation GStation = newFromView();
        if(GStation != null){
            model.add(GStation);
        }
    }
    // Ação do botao Buscar
    /*public void search(){    
        int index = model.search(view.getTextSearch().getText());         // envia o que foi escrito no campo Buscar
        if(index == -1){
            view.showError("Não foi encontrado nenhum posto cadastrado nesse bairro!");
        }
        else{
            view.getTableGasStation().setRowSelectionInterval(index,index); // seleciona na tabela o posto encontrado
            select();
        }
    }*/
    
    // Atualizar o cadastro dos postos
    // Ao atualizar é enviada a listas de combustiveis para ser substituida na lista já que ela é apagada
    // ao usar o comando .set em TableModelGasStation
    public void update(){
        int index = view.getTableGasStation().getSelectedRow();
        if(index == -1){
            return;
        }
        GasStation GStation = newFromView();
        if(GStation != null){
            model.update(index, GStation, model.getGasStationArray().get(index).getFuel());
            // envia qual posto, os dados do posto modificados, e a lista atual dos combustiveis cadastrados desse posto
        }
    }
    
    // Remover um posto cadastrado
    public void remove(){
        int index = view.getTableGasStation().getSelectedRow();
        if(index == -1){
            return;
        }
        model.remove(index);
    }
    
    // Selecionar da tabela um posto
    public void select(){
        int viewRow = view.getTableGasStation().getSelectedRow();
        int modelRow = view.getTableGasStation().convertRowIndexToModel(viewRow);
        //System.out.println( String.format("viewRow: %d\nmodelRow: %d\n",viewRow, modelRow));
        GasStation GStation = model.select(modelRow);
        // Cria uma imagem a partir do nome do arquivo a ser utilizado
        ImageIcon imageIcon = new ImageIcon(GStation.getImagem());
        // Redimenciona a imagem para a interface gráfica
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(imageX, imageY, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        if(viewRow == -1){
            return;
        }
        // mostrar nos JTextFields
        view.getTextCNPJ().setText(GStation.getCNPJ());
        view.getTextRazaoSoc().setText(GStation.getRazaoSocial());
        view.getTextNomeFantasia().setText(GStation.getNomeFantasia());
        view.getTextBandeira().setText(GStation.getBandeira());
        view.getTextEndereco().setText(GStation.getEndereco());
        view.getTextBairro().setText(GStation.getBairro());
        view.getTextCEP().setText(GStation.getCEP());
        view.getTextImagem().setText(GStation.getImagem());
        // mostrar a imagem do posto
        view.getLabelImage().setIcon(imageIcon);
    }
    
    // Ação do botao Limpar, limpa os JTextFields na interface grafica
    public void clear(){
        view.getTextCNPJ().setText("");
        view.getTextRazaoSoc().setText("");
        view.getTextNomeFantasia().setText("");
        view.getTextBandeira().setText("");
        view.getTextEndereco().setText("");
        view.getTextBairro().setText("");
        view.getTextCEP().setText("");
        view.getTextImagem().setText("");
    }
    
    // Pegar os dados da interface
    private GasStation newFromView(){
        try{
            GasStation GStation = new GasStation();        
            GStation.setCNPJ(view.getTextCNPJ().getText());
            GStation.setRazaoSocial(view.getTextRazaoSoc().getText());
            GStation.setNomeFantasia(view.getTextNomeFantasia().getText());
            GStation.setBandeira(view.getTextBandeira().getText());
            GStation.setEndereco(view.getTextEndereco().getText());
            GStation.setBairro(view.getTextBairro().getText());
            GStation.setCEP(view.getTextCEP().getText());
            GStation.setImagem(view.getTextImagem().getText());
            return GStation;
        } catch (NumberFormatException e){
            view.showError("Dado de entrada invalido(s)!");
            return null;
        }
    }
}