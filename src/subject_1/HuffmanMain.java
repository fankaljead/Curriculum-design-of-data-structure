package subject_1;

import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 10:49
 * Description: 数据压缩 解压主类
 */
public class HuffmanMain extends Application{

    @FXML
    FileChooser fileChooser = new FileChooser();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HuffmanView.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HuffmanView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("HuffmanStyle.css").toExternalForm());
//
//        fileChooser.setTitle("请选择文件");
//        fileChooser.showOpenDialog(primaryStage);

//        HuffmanController controller = loader.getController();
//        controller.setStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Huffman");
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
