package util;

import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Util {

    public static URL resolveResource(String caminho) {
        if (caminho == null || caminho.isBlank()) {
            return null;
        }

        URL resource = Util.class.getResource(caminho);
        if (resource != null) {
            return resource;
        }

        try {
            java.io.File arquivo = new java.io.File("src" + caminho);
            if (arquivo.exists()) {
                return arquivo.toURI().toURL();
            }
        } catch (Exception ignored) {
        }

        try {
            java.io.File arquivo = new java.io.File(caminho);
            if (arquivo.exists()) {
                return arquivo.toURI().toURL();
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    public static void abrirTela(String caminhoTela, Button button, String caminhoMusica) {
        try {
            URL urlTela = resolveResource(caminhoTela);
            if (urlTela == null) {
                throw new IllegalStateException("Tela não encontrada: " + caminhoTela);
            }

            Parent root = FXMLLoader.load(urlTela);
            Stage stage = (Stage) button.getScene().getWindow();

            if (!caminhoMusica.isBlank()) {
                tocarMusica(caminhoMusica);
            }

            stage.getScene().setRoot(root);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setTitle("Farinha e o Último Guerreiro");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tocarMusica(String caminhoMusica) {

        pararMusica();

        Config config = Config.getInstance();

        if (!config.isMusicaLigada()) {
            return;
        }

        if (caminhoMusica == null || caminhoMusica.isBlank()) {
            return;
        }

        URL urlMusica = resolveResource(caminhoMusica);
        if (urlMusica == null) {
            return;
        }

        Media media = new Media(urlMusica.toExternalForm());
        mediaPlayerAtual = new MediaPlayer(media);
        mediaPlayerAtual.setAutoPlay(true);
        mediaPlayerAtual.setVolume(0.2);
    }

    private static MediaPlayer mediaPlayerAtual;

    public static void pararMusica() {
        if (mediaPlayerAtual != null) {
            mediaPlayerAtual.stop();
            mediaPlayerAtual.dispose();
            mediaPlayerAtual = null;
        }
    }

    public static void tocarSom(String caminhoSom) {
        Config config = Config.getInstance();

        if (!config.isSonsLigados()) {
            return;
        }

        if (caminhoSom == null || caminhoSom.isBlank()) {
            return;
        }

        URL urlSom = resolveResource(caminhoSom);
        if (urlSom == null) {
            return;
        }

        Media media = new Media(urlSom.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    public static void mensagemErro(String erro, Window owner) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(owner);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setHeaderText("Erro");
        alert.setContentText(erro);
        alert.showAndWait();
    }

    public static MediaPlayer tocarVideo(String caminhoVideo, StackPane container) {
        return tocarVideo(caminhoVideo, container, null);
    }

    public static MediaPlayer tocarVideo(String caminhoVideo, StackPane container, Runnable aoTerminar) {
        if (caminhoVideo == null || caminhoVideo.isBlank()) {
            throw new IllegalArgumentException("O caminho do vídeo não pode ser vazio.");
        }
        if (container == null) {
            throw new IllegalArgumentException("O container do vídeo não pode ser nulo.");
        }

        URL urlVideo = resolveResource(caminhoVideo);
        if (urlVideo == null) {
            throw new IllegalArgumentException("Vídeo não encontrado: " + caminhoVideo);
        }

        Media media = new Media(urlVideo.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(container.widthProperty());
        mediaView.fitHeightProperty().bind(container.heightProperty());
        container.getChildren().add(0, mediaView);

        mediaPlayer.setOnError(() -> {
            String detalhe = String.valueOf(mediaPlayer.getError());
            System.err.println("Erro ao reproduzir o vídeo " + caminhoVideo + ": " + detalhe);
            Platform.runLater(() -> {
                Window owner = container.getScene() == null ? null : container.getScene().getWindow();
                String mensagem = "O JavaFX não conseguiu decodificar o vídeo "
                        + caminhoVideo + ". Converta-o para MP4 com vídeo H.264 (yuv420p) e áudio AAC.\n\n"
                        + detalhe;
                if (owner != null) {
                    mensagemErro(mensagem, owner);
                } else {
                    System.err.println(mensagem);
                }
            });
        });
        mediaPlayer.setVolume(0.2);
        if (aoTerminar != null) {
            mediaPlayer.setOnEndOfMedia(aoTerminar);
        }
        mediaPlayer.play();
        return mediaPlayer;
    }
}
