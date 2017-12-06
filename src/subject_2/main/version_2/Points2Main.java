package subject_2.main.version_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/6 18:34
 * Description:
 */
public class Points2Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Points2View.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("24点游戏V2.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
