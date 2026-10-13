import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Util;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = Util.resolveResource("/view/TelaInicio.fxml");
        if (url == null) {
            throw new IllegalStateException("Arquivo FXML não encontrado: /view/TelaInicio.fxml");
        }

        Parent root = FXMLLoader.load(url);
        Util.tocarMusica("/music/TelaInicio/TelaInicio.mp3");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Farinha e o Último Guerreiro");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}
