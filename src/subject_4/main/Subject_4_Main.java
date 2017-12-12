package subject_4.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/12 9:51
 * Description:
 */
public class Subject_4_Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Subject_4_View.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("展示线性表、树、图");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
