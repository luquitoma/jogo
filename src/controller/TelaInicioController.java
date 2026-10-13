package controller;

import java.util.Random;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import util.Util;

public class TelaInicioController {

    @FXML
    private Button btnConfig;

    @FXML
    private Button btnJogar;

    @FXML
    private Button btnSair;

    @FXML
    private Label labelTitulo;

    @FXML
    private StackPane root;

    @FXML
    void btnConfigAction(ActionEvent event) {
        Random random = new Random();
        int musicaTocada = random.nextInt(3) + 1;
        Util.abrirTela("/view/TelaConfig.fxml", btnConfig, "/music/TelaConfig/TelaConfig" + musicaTocada + ".mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnJogarAction(ActionEvent event) {
        
        Util.abrirTela("/view/TelaSaves.fxml", btnJogar, "/music/TelaSaves/TelaSaves1.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnSairAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnJogarHover (MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }
    
    @FXML
    void btnConfigHover (MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnSairHover (MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }
}
