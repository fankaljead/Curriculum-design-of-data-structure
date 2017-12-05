package subject_2.main.version_1;


import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/5 10:17
 * Description:
 */
public class Points1Controller implements Initializable {

    @FXML
    private ImageView card1;

    @FXML
    private JFXButton btStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindBtStartEvents();
    }

    private void bindBtStartEvents() {
        btStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                File file = new File("K://A.txt");
//                Image image = new Image("G:\\数据结构课程设计\\Curriculum-design-of-data-structure\\out\\production\\Curriculum-design-of-data-structure\\subject_2\\cards\\1.jpg");
//                card1.setImage(image);
            }
        });
    }
}
