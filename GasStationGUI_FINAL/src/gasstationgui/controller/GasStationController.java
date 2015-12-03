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
        view.showMessage("Posto Adicionado!");
    }
    
    // Ação do botão alterar
    public void update(){
        int viewRow = view.getTableGasStation().getSelectedRow();
        int modelRow = view.getTableGasStation().convertRowIndexToModel(viewRow);
        if(viewRow == -1){                          // nenhum posto selecionado
            return;
        }
        GasStation GStation = newFromViewUpdate();
        if(GStation != null){
            model.update(modelRow, GStation, model.getGasStationArray().get(modelRow).getFuel());
            // envia qual posto, os dados do posto atualizados e a lista atual dos combustiveis cadastrados desse posto
        }
        view.showMessage("Dados do Posto Alterado!");
    }
    
    // Ação do botão remover
    public void remove(){
        int viewRow = view.getTableGasStation().getSelectedRow();
        int modelRow;
        if(viewRow == -1){
            return;
        }
        modelRow = view.getTableGasStation().convertRowIndexToModel(viewRow);
        model.remove(modelRow);
        view.showMessage("Posto Removido!");
    }
    
    // Selecionar da tabela um posto
    public void select(){
        int viewRow = view.getTableGasStation().getSelectedRow();                  // Index do jtable
        int modelRow = view.getTableGasStation().convertRowIndexToModel(viewRow); // Index no modelo
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
    
    // Pegar os dados da interface para inserir um novo posto
    private GasStation newFromView(){
        try{
            GasStation GStation = new GasStation(false);
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
            view.showMessage("Dado de entrada invalido(s)!");
            return null;
        }
    }
     // Pegar os dados da interface para alterar os dados de um posto
    private GasStation newFromViewUpdate(){
        try{
            GasStation GStation = new GasStation(true);
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
            view.showMessage("Dado de entrada invalido(s)!");
            return null;
        }
    }
}