package subject_4.main.version_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 22:17
 * Description: 展示加权图的主类
 */
public class ShowWeightedGraphMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ShowWeightedGraphView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("展示加权图");
        primaryStage.show();
    }
}