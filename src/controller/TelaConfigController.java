package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import util.Util;
import util.Config;

public class TelaConfigController {
    
    Config config = Config.getInstance();

    @FXML
    private void initialize(){
        
        cbMusica.setSelected(config.isMusicaLigada());
        cbSons.setSelected(config.isSonsLigados());
        cbDados.setSelected(true);
        cbJogar.setSelected(config.isAutoSaveLigado());

        if (config.isMusicaLigada() != true){
            Util.pararMusica();
        }

        cbDados.setOnAction(event -> {
        if (!cbDados.isSelected()) {
            Util.mensagemErro("Você não vai ter essa opção, amigão", cbDados.getScene().getWindow());
            cbDados.setSelected(true);
            }
        });

    }

    @FXML
    private Button btnSalvar;
        
    @FXML
    private Button btnVoltar;

    @FXML
    private CheckBox cbDados;

    @FXML
    private CheckBox cbJogar;

    @FXML
    private CheckBox cbMusica;

    @FXML
    private CheckBox cbSons;

    @FXML
    private HBox hbHeader;

    @FXML
    private Label labelConfig;

    @FXML
    private StackPane root;

    @FXML
    void btnSalvarAction(ActionEvent event) {
        
        config.setMusicaLigada(cbMusica.isSelected());
        config.setSonsLigados(cbSons.isSelected());
        config.setAutoSaveLigado(cbJogar.isSelected());
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnSalvarHover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnVoltarAction(ActionEvent event) {
        Util.abrirTela("/view/TelaInicio.fxml", btnVoltar, "/music/TelaInicio/TelaInicio.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnVoltarHover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }
}
