package subject_4.main.version_1.linkedList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 15:30
 * Description:
 */
public class ShowLinkedListMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ShowLinkedListView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("展示LinkedList");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
