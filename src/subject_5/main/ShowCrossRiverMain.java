package subject_5.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 10:07
 * Description:
 */
public class ShowCrossRiverMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ShowCrossRiverView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("展示农夫过河问题");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
