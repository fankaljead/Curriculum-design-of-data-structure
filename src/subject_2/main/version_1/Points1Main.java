package subject_2.main.version_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/5 10:17
 * Description:
 */
public class Points1Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Points1View.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("24点游戏");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
