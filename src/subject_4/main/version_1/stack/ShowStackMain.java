package subject_4.main.version_1.stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 19:00
 * Description:
 */
public class ShowStackMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ShowStackView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("展示栈");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
