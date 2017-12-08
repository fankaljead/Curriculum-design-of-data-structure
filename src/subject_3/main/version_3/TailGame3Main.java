package subject_3.main.version_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/8 19:29
 * Description:
 */
public class TailGame2Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/TailGame3View.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("硬币问题3.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
