package gasstationgui.controller;

import gasstationgui.model.GasStation;
//import gasstationgui.model.Fuel;
import gasstationgui.model.TableModelGasStation;
import gasstationgui.view.GasStationView;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Giovani
 */
public class GasStationController {
    private GasStationView view;
    private TableModelGasStation model;
    
    public GasStationController(GasStationView view, TableModelGasStation model){
        this.view=view;
        this.model=model;
    }
    
    // Ação do botao Inserir
    public void insert(){
        GasStation GStation = newFromView();
        if(GStation != null){
            model.add(GStation);
        }
    }
    
    // Atualizar o cadastro dos postos
    public void update(){
        int index = view.getTableGasStation().getSelectedRow();
        if(index == -1){
            return;
        }
        GasStation GStation = newFromView();
        if(GStation != null){
            model.update(index, GStation);
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
    
    // Selecionar da tabela um posto e mostrar nos JTextFields
    public void select(){
        int index = view.getTableGasStation().getSelectedRow();
        GasStation GStation = model.select(index);
        // Cria uma imagem a partir do nome do arquivo a ser utilizado
        ImageIcon imageIcon = new ImageIcon(GStation.getImagem());
        // Redimenciona a imagem para a interface gráfica
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(217, 245, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        
        if(index == -1){
            return;
        }
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
    
    // Escreve nos atributos da classe GasStation o que foi escrito nos JTextFields
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
