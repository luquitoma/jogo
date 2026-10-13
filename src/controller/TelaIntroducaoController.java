package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import util.Util;

public class TelaIntroducaoController {

    @FXML
    private void initialize() {

        btnConfirmar.setVisible(false);
        btnConfirmar.setManaged(false);
        tfNome.setVisible(false);
        tfNome.setManaged(false);

        videoPlayer = Util.tocarVideo("/videos/cutscene1-javafx.mp4", root, () -> {
            tfNome.setManaged(true);
            tfNome.setVisible(true);
            btnConfirmar.setManaged(true);
            btnConfirmar.setVisible(true);
        });

    }

    private MediaPlayer videoPlayer;

    @FXML
    private Button btnConfirmar;

    @FXML
    private StackPane root;

    @FXML
    private TextField tfNome;

    public String nomeUsuario;

    @FXML
    void btnConfirmarAction(ActionEvent event) {
        
        Util.tocarSom("/sounds/selecionar.wav");
        
        if(!tfNome.getText().trim().isEmpty()) {

            nomeUsuario = tfNome.getText();
            pararVideo();
            Util.abrirTela(null, btnConfirmar, null);

        }
    }

    @FXML
    void tfNomeOnKeyPressed(KeyEvent event) {

        if(event.getCode() == KeyCode.ENTER) {
            
            Util.tocarSom("/sounds/selecionar.wav");
            
        }

        if (event.getCode() == KeyCode.ENTER && !tfNome.getText().trim().isEmpty()) {

            nomeUsuario = tfNome.getText();
            pararVideo();
            Util.abrirTela(null, btnConfirmar, null);
        }

    }

    private void pararVideo() {
        if (videoPlayer != null) {
            videoPlayer.stop();
            videoPlayer.dispose();
            videoPlayer = null;
        }
    }

}
