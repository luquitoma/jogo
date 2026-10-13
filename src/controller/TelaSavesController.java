package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.Util;

public class TelaSavesController {

    private static final double DESIGN_WIDTH = 1920.0;
    private static final double DESIGN_HEIGHT = 1080.0;

    @FXML
    private VBox content;

    @FXML
    private Button btnExcluirSave1;

    @FXML
    private Button btnExcluirSave2;

    @FXML
    private Button btnExcluirSave4;

    @FXML
    private Button btnExlcuirSave3;

    @FXML
    private Button btnJogarSave1;

    @FXML
    private Button btnJogarSave2;

    @FXML
    private Button btnJogarSave3;

    @FXML
    private Button btnJogarSave4;

    @FXML
    private Button btnVoltar;

    @FXML
    private HBox hbHeader;

    @FXML
    private HBox hbSave1;

    @FXML
    private HBox hbSave2;

    @FXML
    private HBox hbSave3;

    @FXML
    private HBox hbSave4;

    @FXML
    private Label lbHeader;

    @FXML
    private Label nomeSave1;

    @FXML
    private Label nomeSave2;

    @FXML
    private Label nomeSave3;

    @FXML
    private Label nomeSave4;

    @FXML
    private StackPane root;

    @FXML
    private void initialize() {
        root.widthProperty().addListener((observable, oldWidth, newWidth) -> atualizarEscala());
        root.heightProperty().addListener((observable, oldHeight, newHeight) -> atualizarEscala());
    }

    private void atualizarEscala() {
        double largura = root.getWidth();
        double altura = root.getHeight();
        if (largura <= 0 || altura <= 0) {
            return;
        }

        double escala = Math.min(largura / DESIGN_WIDTH, altura / DESIGN_HEIGHT);
        content.setScaleX(escala);
        content.setScaleY(escala);
    }

    @FXML
    private VBox save1;

    @FXML
    private VBox save2;

    @FXML
    private VBox save3;

    @FXML
    private VBox save4;

    @FXML
    private Label tempoSave1;

    @FXML
    private Label tempoSave2;

    @FXML
    private Label tempoSave3;

    @FXML
    private Label tempoSave4;

    @FXML
    void btnExcluirSave1Action(ActionEvent event) {

    }

    @FXML
    void btnExcluirSave1Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnExcluirSave2Action(ActionEvent event) {

    }

    @FXML
    void btnExcluirSave2Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnExlcuirSave3Action(ActionEvent event) {

    }

    @FXML
    void btnExlcuirSave3Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnJogarSave1Action(ActionEvent event) {
        Util.abrirTela("/view/TelaIntroducao.fxml", btnJogarSave1, "/music/TelaIntroducao/TelaIntroducao.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnJogarSave1Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnJogarSave2Action(ActionEvent event) {
        Util.abrirTela("/view/TelaIntroducao.fxml", btnJogarSave2, "/music/TelaIntroducao/TelaIntroducao.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnJogarSave2Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnJogarSave3Action(ActionEvent event) {
        Util.abrirTela("/view/TelaIntroducao.fxml", btnJogarSave3, "/music/TelaIntroducao/TelaIntroducao.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnJogarSave3Hover(MouseEvent event) {
        Util.tocarSom("/sounds/hover.wav");
    }

    @FXML
    void btnJogarSave4Action(ActionEvent event) {
        Util.abrirTela("/view/TelaIntroducao.fxml", btnJogarSave4, "/music/TelaIntroducao/TelaIntroducao.mp3");
        Util.tocarSom("/sounds/selecionar.wav");
    }

    @FXML
    void btnJogarSave4Hover(MouseEvent event) {
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

