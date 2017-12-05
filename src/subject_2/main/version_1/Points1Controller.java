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

    private static final String path = "    private static final String path = https://github.com/fankaljead/Curriculum-design-of-data-structure/tree/master/images/cards";
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

            }
        });
    }
}
