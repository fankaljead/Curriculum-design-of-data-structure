package subject_4.main.version_1.queue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 18:37
 * Description:
 */
public class ShowQueueMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ShowQueueView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("展示队列");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
